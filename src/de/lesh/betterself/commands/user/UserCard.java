package de.lesh.betterself.commands.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.UserCardSetup;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.utils.SimpleLog;

public class UserCard extends ListenerAdapter{

	public static String dir = System.getProperty("user.dir") + "/";
	public static UserCardSetup SETUP = new UserCardSetup();
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    public static final SimpleLog LOG = SimpleLog.getLog("Application");
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "uc") && msg.getAuthor().equals(Main.USER)){
			loading();
			if(!(SETUP.getTwitter() == null || SETUP.getTwitter().isEmpty())){ eB.addField("Twitter", SETUP.getTwitter(), false); }
			if(!(SETUP.getYoutube() == null || SETUP.getYoutube().isEmpty())){ eB.addField("YouTube", SETUP.getYoutube(), false); }
			if(!(SETUP.getFacebook() == null || SETUP.getFacebook().isEmpty())){ eB.addField("Facebook", SETUP.getFacebook(), false); }
			if(!(SETUP.getTwitch() == null || SETUP.getTwitch().isEmpty())){ eB.addField("Twitch", SETUP.getTwitch(), false); }
			eB.setFooter(Main.USER.getName() + "'s Social Media Contacts", Main.USER.getEffectiveAvatarUrl());
			e.getChannel().sendMessage(eB.build()).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
	
	public static void loading(){
		try{
			File userCard = new File(dir + "usercard.json");
			if(!userCard.exists()){
				if(userCard.createNewFile()){
					BufferedWriter write = new BufferedWriter(new FileWriter(userCard));
					write.write(GSON.toJson(SETUP));
					write.close();
					LOG.info("Successful created file at " + dir);
				} else {
					LOG.fatal("Something went wrong.");
				}
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(userCard));
				SETUP = GSON.fromJson(reader, UserCardSetup.class);
				
				LOG.info("Your personal informations has been loaded");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
