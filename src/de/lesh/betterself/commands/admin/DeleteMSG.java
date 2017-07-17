package de.lesh.betterself.commands.admin;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DeleteMSG extends ListenerAdapter{
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		int def = 1;
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "delete") && msg.getAuthor().equals(Main.USER)){
			String[] delNumber = e.getMessage().getRawContent().split("\\s+", 2); 
			try{
				if(!delNumber[1].isEmpty() || delNumber[1] == null){
					def = Integer.parseInt(delNumber[1]);
				}
			} catch(Exception exception) {
				e.getChannel().sendMessage("[ERROR] >> This is not a number").queueAfter(30, TimeUnit.SECONDS);
				e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
				exception.printStackTrace();
				return;
			}
			if(def < 1 || def > 100){
				e.getChannel().sendMessage("[ERROR] >> Your number (" + def+ ") isnt between 1 or 100").queueAfter(30, TimeUnit.SECONDS);
				e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
				return;
			}
			
			e.getChannel().getHistory().retrievePast(def == 100 ? 100 : def+1).queue(delete -> {
				List<Message> delMSG = delete.stream().filter(m -> Main.USER.equals(m.getAuthor()) && !e.getMessage().equals(m)).collect(Collectors.toList());
				delMSG.forEach(m -> m.delete().queue());
				e.getChannel().sendMessage("[DELETE] >> Removed Messages: " + delMSG.size()).queueAfter(30, TimeUnit.SECONDS);
			});
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
