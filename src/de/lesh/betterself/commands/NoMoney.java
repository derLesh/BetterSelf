package de.lesh.betterself.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class NoMoney extends ListenerAdapter {

	public static String output;
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "nm") && msg.getAuthor().equals(Main.USER)){
			String[] split = e.getMessage().getRawContent().split("\\s+", 2);
//			if(!(split[1] == "http")){
//				System.out.println("Only works with HTTP at the beginning");
//				return;
//			}
			String unshorting = split[1];
			String unshortener = "http://thor.johanpaul.net/lengthen/result?url=" + unshorting;
			
			try {
				URL shorten = new URL(unshortener);
				HttpURLConnection connect = (HttpURLConnection)shorten.openConnection();
				connect.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
				connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
				connect.setRequestProperty("Referer", "http://thor.johanpaul.net/lengthen");
				connect.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				Matcher m = Pattern.compile("value=\"(.*?)\"").matcher(unshortener);
						if (m.find()) {
						    output = m.group(1);
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
