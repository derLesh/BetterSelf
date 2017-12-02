/**
 * 		This work is licensed under a Creative Commons 
 *			 Attribution-NonCommercial-ShareAlike 
 *				4.0 International License.
 */
package de.lesh.betterself.commands.personal;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * 
 * @author Lukas
 */
public class ChangeGame extends ListenerAdapter{

	public static JDA jda;
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "game") && msg.getAuthor().equals(Main.USER)&& !lib.getServerSecure(e)){
			String[] input = msg.getRawContent().split("\\s+",3);
			if(input.length < 1) {
				e.getJDA().getPresence().setPresence(Game.of(null), false);
			}
			if(input[1] == "playing") { e.getJDA().getPresence().setPresence(Game.playing(input[2]), false); }
			if(input[1] == "streaming") { e.getJDA().getPresence().setPresence(Game.streaming(input[2], null), false); }
			if(input[1] == "listening") { e.getJDA().getPresence().setPresence(Game.listening(input[2]), false); }
			if(input[1] == "watching") { e.getJDA().getPresence().setPresence(Game.watching(input[2]), false); }
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}	
	
}
