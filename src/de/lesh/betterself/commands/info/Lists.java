/**
 * 		This work is licensed under a Creative Commons 
 *			 Attribution-NonCommercial-ShareAlike 
 *				4.0 International License.
 */
package de.lesh.betterself.commands.info;

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
public class Lists extends ListenerAdapter {
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if (msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "list ") && msg.getAuthor().equals(Main.USER)&& !lib.getServerSecure(e)) {
			String[] input = msg.getRawContent().split("\\s+", 2);
			// Calls all Guild Roles - This is split into System.out (for sneaky beacky like) and EmbedMessage
			if (input[1].equals("groups")) {
				List<Role> roles = e.getGuild().getRoles();
				if(Main.CONFIG.getPrivat()) { System.out.println(roles); }
				else {
					eB.setAuthor("GroupList", null, null);
					eB.addField("Avaible roles", "" + roles, true);
					e.getChannel().sendMessage(eB.build()).queue(b -> b.delete().queueAfter(30, TimeUnit.SECONDS));
				}
				e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
			}
		}
	}
}
