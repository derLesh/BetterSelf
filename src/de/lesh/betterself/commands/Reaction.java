package de.lesh.betterself.commands;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Reaction extends ListenerAdapter{
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "react") && msg.getAuthor().equals(Main.USER)){
			String[] split = e.getMessage().getRawContent().split("\\s+", 3);
			Message msgID = e.getChannel().getMessageById(split[1]).complete();
			String zweiterTeil;
			String react = String.join(" ", "");
			
			//eB.addField("Wortbau", "" + wortbau, true);
			System.out.println(msgID);
		}
	}
}
