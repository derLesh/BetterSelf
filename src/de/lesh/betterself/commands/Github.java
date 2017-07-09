package de.lesh.betterself.commands;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Github extends ListenerAdapter{
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "github") && msg.getAuthor().equals(Main.USER)){
			e.getChannel().sendMessage("Hey! I'm on GITHUB. My complete code is online: https://github.com/LeshDev/BetterSelf").queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
