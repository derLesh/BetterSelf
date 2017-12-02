/**
 * 		This work is licensed under a Creative Commons 
 *			 Attribution-NonCommercial-ShareAlike 
 *				4.0 International License.
 */
package de.lesh.betterself.commands.fun;

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
public class EightBall extends ListenerAdapter{

	private final String[] answers = { 
			"Ja",
			"Nein",
			"100 Facebook Nutzer sagen JA",
			"Tu dir das nicht an",
			"Oh guck ein Vogel",
			"Ist es ein Vogel, ein Flugzeug, Nein es ist ein NEIN",
			"Nie im Leben",
			"Such dir was besseres",
			"Ist doch klar",
			"Der Computer sagt NEIN",
			"Ja ja ja"
	};
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		Random rnd = new Random();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "8ball ") && msg.getAuthor().equals(Main.USER)&& !lib.getServerSecure(e)){
			String[] input = msg.getRawContent().split("\\s+",2);
			eB.addField("Your Question", msg.getRawContent().replace("+8ball", ""), true);
			eB.addField("Your Answer", ":crystal_ball: " + answers[(int) (Math.random() * answers.length)], false);
			eB.setFooter("Unique Message ID: #" +  rnd.nextInt(1000) + 4 * 2 / 5, null);
			eB.setColor(Color.MAGENTA);
			e.getChannel().sendMessage(eB.build()).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
	
}
