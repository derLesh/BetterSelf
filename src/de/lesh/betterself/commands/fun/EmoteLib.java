package de.lesh.betterself.commands.fun;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import de.lesh.betterself.Main;
import de.lesh.betterself.util.lib;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EmoteLib extends ListenerAdapter {

	public final static Map<String, String> emoteLib = new HashMap<>();

	public static void setEmotes() {
		emoteLib.put("OMEGALUL", "https://cdn.frankerfacez.com/emoticon/128054/1");
		emoteLib.put("HyperLUL", "https://cdn.frankerfacez.com/emoticon/167825/1");
		emoteLib.put("ZULUL", "https://cdn.frankerfacez.com/emoticon/130077/1");
		emoteLib.put("PagChomp", "https://cdn.frankerfacez.com/emoticon/61496/1");
		emoteLib.put("gachiGASM", "https://cdn.frankerfacez.com/emoticon/103171/1");
		emoteLib.put("monkaGUN", "https://cdn.frankerfacez.com/emoticon/187256/1");
		emoteLib.put("Kappa", "https://static-cdn.jtvnw.net/emoticons/v1/25/1.0");
		emoteLib.put("DansGame", "https://cdn.frankerfacez.com/script/replacements/33-DansGame.png");
		emoteLib.put("FeelsRareMan", "https://cdn.betterttv.net/emote/5678a310bf317838643c8188/1x");
		emoteLib.put("LUL", "https://static-cdn.jtvnw.net/emoticons/v1/425618/1.0");
	}

	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		if (emoteLib.containsKey(msg.getRawContent()) && !e.getAuthor().isBot() && msg.getAuthor().equals(Main.USER)&& !lib.getServerSecure(e)) {
			lib.removeMessage(e, 1);
			lib.betterLog(e, msg.getRawContent() + " - " + emoteLib.get(msg.getRawContent()));
			e.getChannel().sendMessage(emoteLib.get(msg.getRawContent())).queue();
			return;
		}
	}
}
