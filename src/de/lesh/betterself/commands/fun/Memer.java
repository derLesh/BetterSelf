package de.lesh.betterself.commands.fun;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Memer extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(!msg.getRawContent().endsWith("okay") || e.getAuthor().isBot() || !msg.getAuthor().equals(Main.USER)) {
			return;
		}
		
		e.getChannel().sendMessage("https://youtu.be/Obgnr9pc820?t=4").queue();
	}
}
