package de.lesh.betterself.commands.fun;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class NonStopMoving extends ListenerAdapter{

	public void onMessageReceived(MessageReceivedEvent e){
		Message msg = e.getMessage();
		Member m = e.getMember();
		EmbedBuilder eB = new EmbedBuilder();
		if(!msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "nsm") || !msg.getAuthor().equals(Main.USER)) {
			return;
		}
		
		String[] split = msg.getRawContent().split("\\s+",4);
		if(split.length < 2){
			eB.setTitle("Missing variables - `nm <user_id> <channel_id>`");
			e.getChannel().sendMessage(eB.build()).queue();
			return;
		}
		
		boolean active = false;
		
		Member u = e.getGuild().getMemberById(split[1]);
		String ch = split[2];
		String ac = split[3];
		
		switch(ac.toLowerCase()){
			case "add":{
				System.out.println("Activated");
				active = true;
				break;
			}
			case "remove":{
				System.out.println("Deactivated");
				active = false;
				break;
			}
			default:{
				break;
			}
		}
		while(active == true){
			for(VoiceChannel channel : e.getGuild().getVoiceChannels()){
				if(channel.getName().equalsIgnoreCase(ch)){
					e.getGuild().getController().moveVoiceMember(u, channel);
					System.out.println("Working Moving");
				}
			}
		}
	}
}
