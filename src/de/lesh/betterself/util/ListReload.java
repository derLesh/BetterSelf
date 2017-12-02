package de.lesh.betterself.util;

import de.lesh.betterself.Main;
import de.lesh.betterself.commands.fun.EmoteLib;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ListReload extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "cfgreload") && !e.getAuthor().isBot() && msg.getAuthor().equals(Main.USER)) {
			lib.removeMessage(e, 1);
			reloadAllLists();
			lib.betterLog(e, "Reloaded config");
		}		
	}
	
	public static void reloadAllLists() {
		EmoteLib.setEmotes();
	}
}
