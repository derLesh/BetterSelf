package de.lesh.betterself.commands.info;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GroupMember extends ListenerAdapter{

	
	//Rewrite
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(!msg.getRawContent().startsWith(Main.CONFIG.getPrefix()+"gm ") || e.getAuthor().isBot() || !msg.getAuthor().equals(Main.USER)) {
			return;
		}
		String[] split = msg.getRawContent().split("\\s+", 2);
		List<Role> roles = e.getGuild().getRolesByName(split[1], true);
		List<Member> member = e.getGuild().getMembersWithRoles(roles);
		List<String> memberList = new ArrayList<>();
		for(Member memberName : member){
			memberList.add(memberName.getEffectiveName());
		}
		
		eB.setTitle("Group Members of " + roles.toString());
		eB.addField("Avaible Member", "" + memberList.toString(), true);
		eB.setColor(Color.CYAN);
		eB.setFooter("BetterSelf - SelfBot by Lesh", null);
		e.getChannel().sendMessage(eB.build()).queue();
		msg.delete().queueAfter(1, TimeUnit.MILLISECONDS);
	}
	
}
