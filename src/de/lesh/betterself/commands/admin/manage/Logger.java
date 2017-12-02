package de.lesh.betterself.commands.admin.manage;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Logger extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "runlog") && msg.getAuthor().equals(Main.USER)){
			long channelID = msg.getChannel().getIdLong();
			
		}
		
	}
	
}
