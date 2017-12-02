package de.lesh.betterself.commands.fun;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.lesh.betterself.Main;
import de.lesh.betterself.util.AnimatedGifEncoder;
import de.lesh.betterself.util.WrapUtil;
import de.lesh.betterself.util.lib;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Spoiler extends ListenerAdapter{
    private final Color DARKTHEME = new Color(54, 57, 62);
    private final Color HOVERCOLOR = new Color(155,155,155);
    private final Color TEXTCOLOR = Color.WHITE;
    private final Font FONT = new Font("Arial", Font.PLAIN, 15);
    private final int BUFFER = 3;
    
    public void onMessageReceived(MessageReceivedEvent event) {
    	Message msg = event.getMessage();
		String g = event.getGuild().getId();
		if(!msg.getRawContent().startsWith(Main.CONFIG.getPrefix() + "spoiler") || !msg.getAuthor().equals(Main.USER)|| lib.getServerSecure(event)) {
			return;
		}
		String input = msg.getRawContent();
        try{
            Canvas c = new Canvas();
            @SuppressWarnings("unchecked")            
			List<String> lines = WrapUtil.wrap(input.replace("\n", " "), c.getFontMetrics(FONT), 400-(2*BUFFER)-2);
            input.replace("+spoiler", " ");
            if(lines.size()>8)
            {
                System.out.println("Too many lines (>8)");
                return;
            }
            BufferedImage text = new BufferedImage(400,lines.size()*(FONT.getSize()+BUFFER)+2*BUFFER+2,BufferedImage.TYPE_INT_RGB);
            BufferedImage hover = new BufferedImage(400,lines.size()*(FONT.getSize()+BUFFER)+2*BUFFER+2,BufferedImage.TYPE_INT_RGB);
            Graphics2D textG = text.createGraphics();
            Graphics2D hoverG = hover.createGraphics();
            textG.setFont(FONT);
            hoverG.setFont(FONT);
            textG.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            hoverG.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            textG.setColor(DARKTHEME);
            hoverG.setColor(DARKTHEME);
            textG.fillRect(1, 1, text.getWidth()-2, text.getHeight()-2);
            hoverG.fillRect(1, 1, text.getWidth()-2, text.getHeight()-2);
            textG.setColor(TEXTCOLOR);
            hoverG.setColor(HOVERCOLOR);
            for(int i=0; i<lines.size(); i++)
                textG.drawString(lines.get(i), BUFFER+1, (i+1)*(FONT.getSize()+BUFFER)-1);//text.getHeight()-BUFFER);
            hoverG.drawString("[ Hover to View Spoiler ]", BUFFER+1, FONT.getSize()+BUFFER-1);
            AnimatedGifEncoder e = new AnimatedGifEncoder();
            e.setRepeat(0);
            e.start("spoiler.gif");
            e.setDelay(1);
            e.addFrame(hover);
            e.setDelay(60000);
            e.addFrame(text);
            e.setDelay(60000);
            e.finish();
            event.getChannel().sendFile(new File("spoiler.gif"), null).queue();
            textG.dispose();
            hoverG.dispose();
        }catch(Exception e){
            e.printStackTrace();
        }
        event.getMessage().delete().queueAfter(1, TimeUnit.MILLISECONDS);
    }
    
}
