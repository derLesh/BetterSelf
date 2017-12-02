/**
 * 		This work is licensed under a Creative Commons 
 *			 Attribution-NonCommercial-ShareAlike 
 *				4.0 International License.
 */
package de.lesh.betterself.commands.admin;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * 
 * @author Lukas
 */
public class ChangelogCreator extends ListenerAdapter{

	String everyone = " - @everyone";
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if (msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "changelog ") && msg.getAuthor().equals(Main.USER)&& !lib.getServerSecure(e)) {
			String[] input = msg.getRawContent().split("\\s+",3);
			if(input[1].equals("0")) everyone = "";
			if(input[1].equals("1")) { e.getChannel().sendMessage(LocalDateTime.now().format(lib.formatter) + everyone +"\n" + "- " + input[2]).queue(); }
			else { };
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
