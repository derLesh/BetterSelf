package de.lesh.betterself.commands.info;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.lib;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class UserInfo extends ListenerAdapter{

	static DateTimeFormatter time = DateTimeFormatter.ofPattern("d.MMMM.yyyy - hh:mm a");
	
	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		String g = e.getGuild().getId();
		if(!msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "user") || !msg.getAuthor().equals(Main.USER) || lib.getServerSecure(e)) {
			return;
		}
		
		if(e.getMessage().getMentionedUsers().size()>0){
			for(User u:e.getMessage().getMentionedUsers()){
				sendInfo(e.getGuild().getMember(u),e.getTextChannel());
			}
		}else{
			sendInfo(e.getMember(), (TextChannel) e.getChannel());
		}
		e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
	}
	
	private static final int MESSAGE_LOOKBACK_COUNT = 100;
    public static void sendInfo(Member m, TextChannel channel){
        EmbedBuilder eB = new EmbedBuilder();
        User u = m.getUser();
		String g = m.getGuild().getId();
        eB.setAuthor("Infocard >> " + u.getName(), null, u.getEffectiveAvatarUrl());
        eB.addField("**User**:", u.getAsMention(), true);
        eB.addField("**ID**:", "" + u.getIdLong(), true);
        eB.addField("**Created**:", "" + u.getCreationTime().format(time), true);
        eB.addField("Joined", ""+m.getJoinDate().format(time), true);
        eB.addField("**Message Frequency**:", "" + channel.getHistory().retrievePast(MESSAGE_LOOKBACK_COUNT).complete().stream().filter(e->e.getAuthor().equals(u)).count()/((double)MESSAGE_LOOKBACK_COUNT), true);
        if(m.getRoles().size() != 0) { eB.addField("**Roles**:", ""+m.getRoles().stream().map(Role::getName).collect(Collectors.joining(" - ")), true); }
        else { eB.addField("**Roles**:", "None", true); }
		eB.addField("Online Status", m.getOnlineStatus().name(), true);
		if(m.getColor() != null) { eB.addField("User Color", ""+m.getColor().getRed() + ","+m.getColor().getGreen() + ","+m.getColor().getBlue(), true); }
		if(m.getGame() != null) { eB.addField("Playing", ""+ m.getGame().getName(), true); } else { eB.addField("Playing", ":no_entry_sign: ", true); }
		
        eB.setThumbnail(u.getEffectiveAvatarUrl());
        eB.setColor(Color.CYAN);
        channel.sendMessage(eB.build()).queue();
    }
}
