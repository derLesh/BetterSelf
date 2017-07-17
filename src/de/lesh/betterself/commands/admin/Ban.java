package de.lesh.betterself.commands.admin;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Ban extends ListenerAdapter {
	
	Permission permission = Permission.BAN_MEMBERS;
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		User banned = null;
		String[] reasons = {"Spamming", "Racist", "Test"};
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "ban") && msg.getAuthor().equals(Main.USER)){
			String[] split = e.getMessage().getRawContent().split("\\s+", 3);
			EmbedBuilder eB = new EmbedBuilder();
			banned = e.getMessage().getMentionedUsers().get(0);
			e.getGuild().getController().ban(banned, 7, reasons[Integer.parseInt(split[2])]).queue();
			eB.addField("Banned User", "" + banned.getName(), true);
			eB.addField("Reason", reasons[2], false);
    		e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(30, TimeUnit.SECONDS));
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
