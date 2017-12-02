package de.lesh.betterself.commands.fun;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.lib;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class WhatAPleb extends ListenerAdapter{
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "in") && msg.getAuthor().equals(Main.USER)&& !lib.getServerSecure(e)){
			String[] reaction = e.getMessage().getRawContent().split("\\s+", 2);
			e.getChannel().sendMessage(reaction[1] + " in 2017 LUL What a Pleb").queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
