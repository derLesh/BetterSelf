package de.lesh.betterself.commands.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Lmgtfy extends ListenerAdapter{

	String url = "https://lmgtfy.com/?q=";
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "lmgtfy") && msg.getAuthor().equals(Main.USER)){
			String[] link = e.getMessage().getRawContent().split("\\s+", 2);
			try {
				String encoded = URLEncoder.encode(link[1], "UTF-8");
				e.getChannel().sendMessage(url + encoded).queue();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}			
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
