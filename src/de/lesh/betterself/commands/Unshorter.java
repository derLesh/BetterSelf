package de.lesh.betterself.commands;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.util.Config;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Unshorter extends ListenerAdapter {
	
	public static Config CONFIG = new Config();

	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		
		if (msg.getRawContent().startsWith("+unshort")) {
			String[] split = e.getMessage().getRawContent().split("\\s+", 2);
			String unshorting = "";
			unshorting = split[1];
			String unshortener = "https://unshorten.me/s/" + unshorting;
			e.getChannel().sendMessage("Unshorted link: " + unshortener).queue();
			
			System.out.println("Succesful shorted");
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}	
	}
}
