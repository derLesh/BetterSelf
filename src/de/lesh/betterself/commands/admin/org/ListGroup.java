package de.lesh.betterself.commands.admin.org;

import java.util.List;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ListGroup extends ListenerAdapter{
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "group") && msg.getAuthor().equals(Main.USER)){
			List<Role> roles = e.getGuild().getRoles();
			eB.setAuthor("GroupList", null, null);
			eB.addField("Avaible roles", "" + roles, true);
			
			System.out.println(roles);
			//e.getChannel().sendMessage(eB.build()).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
