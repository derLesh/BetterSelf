package de.lesh.betterself.commands;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Reaction extends ListenerAdapter{
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "react") && msg.getAuthor().equals(Main.USER)){
			String[] split = e.getMessage().getRawContent().split("\\s+", 3);
			final MessageHistory id = e.getChannel().getHistoryAround(split[1], 2).complete();
			final Message m = id.getMessageById(split[1]);			
			String react = String.join(" ", split[2]);
			
			eB.addField("Message from " + m.getAuthor().getName() + ":", "```" + m.getRawContent() + "```", false);
			eB.addField("Reaction from " + Main.USER.getName() + ":", "```" + react + "```", false);
			e.getChannel().sendMessage(eB.build()).queue();
			System.out.println("[SUCCESSFUL] >> React > From: " + m.getAuthor() + " - Reaction: " + react);
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
