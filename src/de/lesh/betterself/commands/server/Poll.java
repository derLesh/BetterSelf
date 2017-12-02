/**
 * 		This work is licensed under a Creative Commons 
 *			 Attribution-NonCommercial-ShareAlike 
 *				4.0 International License.
 */
package de.lesh.betterself.commands.server;

import java.awt.Color;
import java.util.Random;
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
public class Poll extends ListenerAdapter {

	Long pollID;

	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		Random rnd = new Random();
		if (msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "poll ") && msg.getAuthor().equals(Main.USER) && !lib.getServerSecure(e)) {
			String input = msg.getRawContent().split("\\s+", 2)[1];
			eB.addField("Question", input + "?", true);
			eB.setColor(Color.YELLOW);
			eB.setFooter("Unique Message ID: #" +  rnd.nextInt(1000) + 4 * 2 / 5, null);
			e.getChannel().sendMessage(eB.build()).queue(react -> {
				react.addReaction("\u2705").queue();
				react.addReaction("\u274C").queue();
				pollID = react.getIdLong();
			});
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
