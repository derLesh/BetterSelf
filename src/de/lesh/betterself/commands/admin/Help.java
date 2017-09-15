package de.lesh.betterself.commands.admin;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Help extends ListenerAdapter{
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "help") && msg.getAuthor().equals(Main.USER)){
			eB.addField(Main.CONFIG.getPrefix() + "unshort", "Unshort links like bit.ly and get the full url", false);
			eB.addField(Main.CONFIG.getPrefix() + "nm", "Unshort adf.ly (and more) links", false);
			eB.addField(Main.CONFIG.getPrefix() + "lmgtfy", "Creates a lmgtfy link with your text", false);
			eB.addField(Main.CONFIG.getPrefix() + "duden", "", false);
			eB.addField(Main.CONFIG.getPrefix() + "si", "All infos about the current server in one message", false);
			eB.addField(Main.CONFIG.getPrefix() + "in", "", false);
			eB.addField(Main.CONFIG.getPrefix() + "meme", "Write a random meme into the chat", false);
			eB.addField(Main.CONFIG.getPrefix() + "kick", "Kick any member from your server - **ONLY WORKS WITH KICK_MEMBER PERMISSION**", false);
			eB.addField(Main.CONFIG.getPrefix() + "ban", "Ban any member from your server - **ONLY WORKS WITH BAN_MEMBER PERMISSION**", false);
			eB.addField(Main.CONFIG.getPrefix() + "delete", "Delete mutiple messages from a channel - **Currently you arent able to delet others messages**", false);
			eB.addField(Main.CONFIG.getPrefix() + "postillon", "Sends a random newsticker message from Postillon24", false);
			eB.addField(Main.CONFIG.getPrefix() + "survey", "Open a survey with reactions", false);
			eB.addField(Main.CONFIG.getPrefix() + "react", "React to a message in discord. You will need the MessageID.", false);
			eB.addField(Main.CONFIG.getPrefix() + "tr", "Translate text from your language to english (Currently in Work in Progress)", false);
			eB.addField(Main.CONFIG.getPrefix() + "uc", "", false);
			eB.addField(Main.CONFIG.getPrefix() + "person", "", false);
			eB.addField(Main.CONFIG.getPrefix() + "lb", "Get all bots who joined the current server", false);
			
			
			e.getChannel().sendMessage(eB.build()).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
