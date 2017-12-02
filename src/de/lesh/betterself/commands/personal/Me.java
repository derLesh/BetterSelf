/**
 * 		This work is licensed under a Creative Commons 
 *			 Attribution-NonCommercial-ShareAlike 
 *				4.0 International License.
 */
package de.lesh.betterself.commands.personal;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * 
 * @author Lukas
 */
public class Me extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if (msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "me ") && msg.getAuthor().equals(Main.USER) && !lib.getServerSecure(e)) {
			String[] input = msg.getRawContent().split("\\s+", 2);
			eB.setDescription(input[1]);
			eB.setColor(Color.MAGENTA);
			e.getChannel().sendMessage(eB.build()).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
