package de.lesh.betterself.commands.admin.survey;

import java.util.List;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Survey extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		EmbedBuilder eB = new EmbedBuilder();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "survey ") && msg.getAuthor().equals(Main.USER)){
			String[] command = e.getMessage().getRawContent().split("\\s+", 3);
			if(command[1].equals("close")){
				System.out.println("CLOSE");
				//String[] closeID = e.getMessage().getRawContent().split("\\s+", 3);
				final MessageHistory id = e.getChannel().getHistoryAround(command[2], 2).complete();
				final Message m = id.getMessageById(command[2]);
				List<MessageReaction> reactions = m.getReactions();
				eB.addField("Closed Survey", "```" + m.getRawContent() + "```", true);
				eB.addField("Result", ":white_check_mark:: " + (reactions.get(0).getCount() - 1)  + "       -      :x:: " + (reactions.get(1).getCount() - 1), false);
				System.out.println(reactions.get(1).getCount());
				e.getChannel().sendMessage(eB.build()).complete();
				e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
				try {
					Thread.sleep(100L);
					m.delete().complete();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				return;
			} else {
				System.out.println("CREATE");
				String[] surveyStr = e.getMessage().getRawContent().split("\\s+", 2);
				eB.setAuthor(msg.getAuthor().getName() + " started a survey", null, msg.getAuthor().getEffectiveAvatarUrl());
				eB.addField("Survey", "```" + surveyStr[1] + "```", true);
				e.getChannel().sendMessage(eB.build()).queue(reaction -> {reaction.addReaction("\u2705").queue(); reaction.addReaction("\u274C").queue();});
			}
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
			
		}
	}
}
