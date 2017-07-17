package de.lesh.betterself.commands.url;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Duden extends ListenerAdapter{
	
	public String url = "https://www.duden.de/rechtschreibung/";
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "duden") && msg.getAuthor().equals(Main.USER)){
			String[] dudenURL = e.getMessage().getRawContent().split("\\s+", 2);
			if(dudenURL[1].contains(" ")){
				e.getChannel().sendMessage("[DUDEN] >> Remove the Space").queue();
				return;
			}
			
			e.getChannel().sendMessage(url + dudenURL[1]).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
