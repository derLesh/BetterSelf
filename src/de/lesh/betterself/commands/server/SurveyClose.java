package de.lesh.betterself.commands.server;

import java.util.List;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SurveyClose extends ListenerAdapter{
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "survey close") && msg.getAuthor().equals(Main.USER)){
			String[] closeID = e.getMessage().getRawContent().split("\\s+", 3);
			final MessageHistory id = e.getChannel().getHistoryAround(closeID[2], 2).complete();
			final Message m = id.getMessageById(closeID[2]);
			List<MessageReaction> reactions = m.getReactions();
			eB.addField("Closed Survey", "```" + m.getRawContent() + "```", true);
			eB.addField("Result", ":white_check_mark:: " + (reactions.get(0).getCount() - 1)  + "       -      :x:: " + (reactions.get(1).getCount() - 1), false);
			System.out.println(reactions.get(1).getCount());
			e.getChannel().sendMessage(eB.build()).queue();
			m.delete().queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
