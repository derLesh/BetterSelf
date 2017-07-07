package de.lesh.betterself.commands;

import de.lesh.betterself.util.Conf;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Unshorter extends ListenerAdapter {
	
	public static Conf CONFIG = new Conf();
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		String unshorting = "";
		String unshorter = "https://unshorten.me/s/" + unshorting;
		
		if (msg.getRawContent().startsWith("+unshort ")) {
			String[] split = e.getMessage().getRawContent().split("\\s+", 2);
			unshorting = split[1];
			e.getChannel().sendMessage("Unshorted link: " + unshorter).queue();
		}
		
		System.out.println("Succesful shorted");
	}
}
