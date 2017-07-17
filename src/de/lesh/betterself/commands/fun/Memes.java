package de.lesh.betterself.commands.fun;

import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Memes extends ListenerAdapter{

	public String[] memes = { "ᕕ(❍□❍)ᕗ", "(ง⚆ѽ⚆)ง", "( ͡° ͜ʖ ͡°)", "(⌐■_■)", "(╭☞ ͠° ͜ʖ °)╭☞", "ಠ_ಠ", "(づ◔ ͜ʖ◔)づ"};
	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if(msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "meme") && msg.getAuthor().equals(Main.USER)){
			// Random rng = new Random();
			int meme = (int) (Math.random() * memes.length);
			e.getChannel().sendMessage(memes[meme]).queue();
			e.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
		}
	}
}
