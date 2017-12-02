package de.lesh.betterself.commands.fun;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.lib;
import de.lesh.betterself.util.rss.Feed;
import de.lesh.betterself.util.rss.FeedMessage;
import de.lesh.betterself.util.rss.RSSFeedParser;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Bild extends ListenerAdapter{
	public String BildImage = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Logo_BILD.svg/2000px-Logo_BILD.svg.png";
	List<String> tickers = new ArrayList<>();
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "bild") && msg.getAuthor().equals(Main.USER)&& !lib.getServerSecure(e)){
			RSSFeedParser parser = new RSSFeedParser("http://www.bild.de/rssfeeds/vw-home/vw-home-16725562,dzbildplus=true,sort=1,teaserbildmobil=false,view=rss2.bild.xml");

			
			Random random = new Random();
			Feed feed = parser.readFeed();
			eB.setThumbnail(BildImage);
			eB.setColor(Color.RED);
			eB.addField("Inhalt", feed.getDescription(random.nextInt(100)), false);
			eB.setFooter(feed.getDescription(random.nextInt(100)), BildImage);
			e.getChannel().sendMessage(eB.build()).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
