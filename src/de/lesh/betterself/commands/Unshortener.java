package de.lesh.betterself.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.Config;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Unshortener extends ListenerAdapter {
	
	public static Config CONFIG = new Config();
	public static String output;

	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		
		if (msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "unshort")) {
			String[] split = e.getMessage().getRawContent().split("\\s+", 2);
			String unshorting = "";
			unshorting = split[1];
			String unshortener = "https://unshorten.me/s/" + unshorting;
			
			try {
				URL shorten = new URL(unshortener);
				HttpURLConnection connect = (HttpURLConnection)shorten.openConnection();
				connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				connect.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				String result;
				while((result = in.readLine()) != null){
					output = result;
				}
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.getChannel().sendMessage("[UNSHORTENER] >> Original link: " + output).queue();
			
			System.out.println("[SUCCESSFUL] >> UNSHORTED - Original: " + unshorting + " - Unshorted: " + output);
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}	
	}
}
