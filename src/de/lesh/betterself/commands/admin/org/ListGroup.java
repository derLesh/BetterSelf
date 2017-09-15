package de.lesh.betterself.commands.admin.org;

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

public class ListGroup extends ListenerAdapter{
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "group") && msg.getAuthor().equals(Main.USER)){
			String groupID = e.getMessage().getRawContent().split("\\s+", 2)[1];
			//String memberName = null;
			List<String> memberName = new ArrayList<>();
			List<Role> roles = e.getGuild().getRolesByName(groupID, true);
			List<Member> groupList = e.getGuild().getMembersWithRoles(roles);
			for(Member memName : groupList){
				memberName.add(memName.getEffectiveName());
			}
			int groupSize = e.getGuild().getMembersWithRoles(roles).size();
			e.getGuild().getRolesByName(groupID, true);
			eB.setAuthor("Group name: " + groupID, null, null);
			eB.addField("Group Size", ""+groupSize, true);
			eB.addField("Members of this Group", memberName +"\n", false);

			e.getChannel().sendMessage(eB.build()).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
