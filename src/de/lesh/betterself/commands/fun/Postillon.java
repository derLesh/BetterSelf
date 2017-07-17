package de.lesh.betterself.commands.fun;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Postillon extends ListenerAdapter{

	public String Postillon24Image = "https://yt3.ggpht.com/-64MplBR6RbY/AAAAAAAAAAI/AAAAAAAAAAA/8LjgNjuAJWk/s900-c-k-no-mo-rj-c0xffffff/photo.jpg";
	List<String> tickers = new ArrayList<>();
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "postillon") && msg.getAuthor().equals(Main.USER)){
			try {
				String[] content = getWebContent(new URL("http://www.der-postillon.com/search/label/Newsticker")).split("\\+\\+\\+");
				for (int i = 1; i < content.length; i += 2){
				    tickers.add(content[i]);
				}
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Random rng = new Random();
			String tracker = tickers.get(rng.nextInt(tickers.size()));
			
			eB.addField("Postillon24 berichtet: ", "+++ " + tracker + " +++", true);
			eB.setFooter("Postillon Liveticker - Stolen by roman", Postillon24Image);
			e.getChannel().sendMessage(eB.build()).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
	
	public static String getWebContent(URL url) {
        try (Scanner s = new Scanner(url.openStream())) {
            s.useDelimiter("\\A");
            return s.next();
        } catch (IOException ignored) {
        }
        return null;
    }
}
