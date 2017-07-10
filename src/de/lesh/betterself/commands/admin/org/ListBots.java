package de.lesh.betterself.commands.admin.org;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ListBots extends ListenerAdapter{

	public List<String> bots = new ArrayList<>();
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "lb") && msg.getAuthor().equals(Main.USER)){
			System.out.println("Check");
			bots.clear();
			e.getGuild().getMembers().stream().filter(u->u.getUser().isBot()).forEach(u->bots.add(u.getUser().getAsMention()));
			e.getChannel().sendMessage("[Bot List] >> " + bots).queue();
			System.out.println("Bot Name: " + bots);
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
