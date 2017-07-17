package de.lesh.betterself.commands.info;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ServerInfo extends ListenerAdapter{
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "si") && msg.getAuthor().equals(Main.USER)){
			eB.setTitle("Server Info for " + e.getGuild().getName());
			eB.addField("Name", e.getGuild().getName(), true);
			eB.addField("Region", e.getGuild().getRegion().getName(), true);
			eB.addField("Members", "" + e.getGuild().getMembers().size(), true);
			eB.addField("Real Members", "" + e.getGuild().getMembers().stream().filter(es -> !es.getUser().isBot()).count(), true);
			eB.addField("Owner", e.getGuild().getOwner().getUser().getName(), true);
			eB.addField("Creation","" + e.getGuild().getCreationTime(), true);
			eB.addField("Roles", "" + e.getGuild().getRoles().size(), true);
			e.getChannel().sendMessage(eB.build()).queue();
			System.out.println("[SUCCESSFUL] >> ServerInfo: " + e.getGuild().getName());
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
