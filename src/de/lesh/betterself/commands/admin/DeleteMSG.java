package de.lesh.betterself.commands.admin;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.lib;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DeleteMSG extends ListenerAdapter{
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		int def = 1;
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "delete") && msg.getAuthor().equals(Main.USER)&& !lib.getServerSecure(e)){
			String[] delNumber = e.getMessage().getRawContent().split("\\s+", 3); 
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
				if(delNumber[2].equals("self")){
					List<Message> delMSG = delete.stream().filter(m -> Main.USER.equals(e.getAuthor()) && !e.getMessage().equals(m)).collect(Collectors.toList());
					delMSG.forEach(m -> m.delete().queue());
					e.getChannel().sendMessage("[DELETE] >> Removed Messages: " + delMSG.size()).queueAfter(30, TimeUnit.SECONDS);
				}else{
					
				}
			});
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
