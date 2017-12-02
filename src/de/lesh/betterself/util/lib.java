package de.lesh.betterself.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class lib {

	public static JDA jda;
	
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm");
	List<String> getDescription = new ArrayList<>();

	public static String getUserAndID(MessageReceivedEvent e) {
		return e.getAuthor().getName() + " / " + e.getAuthor().getId();
	}

	public static void removeMessage(MessageReceivedEvent e, int time) {
		e.getMessage().delete().queueAfter(time, TimeUnit.SECONDS);
	}
	
	public static void betterLog(MessageReceivedEvent e, String LogMSG) {
		System.out.println("[" + LocalDateTime.now().format(formatter) + "]" + " [U:" + e.getAuthor().getName() + " / C:#" + e.getChannel().getName() + "]: " + LogMSG);
	}
	
	public static boolean getServerSecure(MessageReceivedEvent e) {
		Long discordHypeSquad = 200661830648070145L;
		Long discordFeedback = 268811439588900865L;
		Long NoahDiscord = 247722479173238784L;
		
		
		if(e.getMessage().getGuild().getIdLong() == discordHypeSquad) { return true; }
		if(e.getMessage().getGuild().getIdLong() == discordFeedback) { return true; } 
		//if(e.getMessage().getGuild().getIdLong() == NoahDiscord) return true;
		return false;
	}
}
