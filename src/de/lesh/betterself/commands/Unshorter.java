package de.lesh.betterself.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.util.Config;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Unshorter extends ListenerAdapter {
	
	public static Config CONFIG = new Config();
	public static String output;
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		
		if (msg.getRawContent().startsWith("+unshort")) {
			String[] split = e.getMessage().getRawContent().split("\\s+", 2);
			String unshorting = "";
			unshorting = split[1];
			String unshortener = "https://unshorten.me/s/" + unshorting;
			
			try {
				URL shorten = new URL(unshortener);
				URLConnection connect = shorten.openConnection();
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
			e.getChannel().sendMessage("Unshorted link: " + output).queue();
			
			System.out.println("Succesful shorted");
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}	
	}
}
