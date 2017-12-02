/**
 * 		This work is licensed under a Creative Commons 
 *			 Attribution-NonCommercial-ShareAlike 
 *				4.0 International License.
 */
package de.lesh.betterself.commands.info;

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
public class Discriminator extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		String discrim;
		StringBuilder builder = new StringBuilder();
		if (msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "discrim ") && msg.getAuthor().equals(Main.USER) && !lib.getServerSecure(e)) {
			String[] input = msg.getRawContent().split("\\s+", 2);
			if(input[1].isEmpty()) discrim = e.getAuthor().getDiscriminator();
			else if(!input[1].matches("\\d{4}")) e.getChannel().sendMessage(eB.addField("Discrim Error", "Invalid discrim - Only 4 numbers", true).build()).queue();
			else e.getJDA().getUsers().stream().filter(u -> u.getDiscriminator().equals(input[1])).forEach(u -> { if(builder.length()<1940) builder.append("`").append(u.getName()).append("` "); });
			eB.addField("Get Discriminators for #" + input[1], builder.toString(), true);
			e.getChannel().sendMessage(eB.build()).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
	
}
