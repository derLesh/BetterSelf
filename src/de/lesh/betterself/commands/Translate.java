package de.lesh.betterself.commands;

import com.gtranslate.Language;
import com.gtranslate.Translator;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.lib;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Translate extends ListenerAdapter{

	// Tranlate from German to English - NOT in work currently
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "tr") && msg.getAuthor().equals(Main.USER)&& !lib.getServerSecure(e)){
			String[] transText = e.getMessage().getRawContent().split("\\s+", 2);
			Translator transProgram = Translator.getInstance();
			String translation = transProgram.translate(transText[1], Language.ENGLISH, Language.GERMAN);
			e.getChannel().sendMessage("Original: " + transText[1] + " - Translation: " + translation).queue();
		}
	}
}
