package de.lesh.betterself.commands.user;

import java.io.File;
import java.io.IOException;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class UserCard extends ListenerAdapter{

	public static String dir = System.getProperty("user.dir") + "/";
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "uc") && msg.getAuthor().equals(Main.USER)){
			
		}
	}
	
	public void loading() throws IOException{
		File userCard = new File(dir + "usercard.json");
		if(!userCard.exists()){
			if(userCard.createNewFile()){
				
			}
		}
	}
}
