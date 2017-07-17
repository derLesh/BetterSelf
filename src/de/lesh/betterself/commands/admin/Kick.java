package de.lesh.betterself.commands.admin;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Kick extends ListenerAdapter{
	Permission permission = Permission.BAN_MEMBERS;
	
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		Member kicked = null;
		String[] reasons = {"Spamming", "Racist", "Test"};
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "kick") && msg.getAuthor().equals(Main.USER)){
			String[] split = e.getMessage().getRawContent().split("\\s+", 3);
			EmbedBuilder eB = new EmbedBuilder();
			kicked = e.getGuild().getMember((e.getMessage().getMentionedUsers().get(0)));
			e.getGuild().getController().kick(kicked, "Reason: " + reasons[Integer.parseInt(split[3])] + " - Invite: https://discord.gg/889PF8Q").queue();
			eB.addField("Banned User", "" + kicked.getEffectiveName(), true);
			eB.addField("Reason", reasons[2], false);
    		e.getChannel().sendMessage(eB.build()).queue(msge -> msge.delete().queueAfter(30, TimeUnit.SECONDS));
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
