package de.lesh.betterself;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.lesh.betterself.commands.*;
import de.lesh.betterself.commands.admin.*;
import de.lesh.betterself.commands.admin.manage.Ban;
import de.lesh.betterself.commands.admin.manage.Kick;
import de.lesh.betterself.commands.admin.org.*;
import de.lesh.betterself.commands.fun.*;
import de.lesh.betterself.commands.info.*;
import de.lesh.betterself.commands.personal.*;
import de.lesh.betterself.commands.server.*;
import de.lesh.betterself.commands.url.*;
import de.lesh.betterself.commands.url.shorter.*;
import de.lesh.betterself.util.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDA.Status;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.JDAInfo;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.utils.JDALogger;

public class Main {

	public static JDA jda = null;
	public static Config CONFIG = new Config();
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	public static String fileName = "config.json";
	public static String dir = System.getProperty("user.dir") + "/";
	public static final Logger LOG = JDALogger.getLog("BetterSelf");
    public static User USER = null;
    public static final String OS = System.getProperty("os.name").toLowerCase(); 
	
	public static void main(String[] args) throws Exception{
		installer();
		setup();
		UserCard.loading();
		jda = new JDABuilder(AccountType.CLIENT).setToken(CONFIG.getToken()).buildAsync();
		
		System.out.println("[BOOT] >> Launching BetterSelf");
		System.out.println("[BOOT] >> Version: BETA");
		System.out.println("[INFO] >> Checking JDA Version: " + JDAInfo.VERSION);
		LOG.info(JDAInfo.VERSION);
		System.out.println("[INFO] >> Newest JDA Version: " + JDAInfo.VERSION_BUILD);
		System.out.println("[INFO] >> Bot Prefix: " + CONFIG.getPrefix());
		System.out.println("[INFO] >> Privacy Settings: " + CONFIG.getPrivat());

		login();
		USER = jda.getSelfUser();
		LOG.info("Succesful logged into " + USER.getName());
		
		jda.addEventListener(new Unshort());
		jda.addEventListener(new Ban());
		jda.addEventListener(new Kick());
		jda.addEventListener(new WhatAPleb());
		jda.addEventListener(new Github());
		jda.addEventListener(new AdAway());
		jda.addEventListener(new ServerInfo());
		jda.addEventListener(new ListBots());
		jda.addEventListener(new Reaction());
		jda.addEventListener(new Lmgtfy());
		//jda.addEventListener(new Translate());
		jda.addEventListener(new UserCard());
		jda.addEventListener(new Poll());
		jda.addEventListener(new DeleteMSG());
		jda.addEventListener(new Memes());
		jda.addEventListener(new Duden());
		jda.addEventListener(new Postillon());
		jda.addEventListener(new Help());
		jda.addEventListener(new UserInfo());
		jda.addEventListener(new GroupMember());
		jda.addEventListener(new NonStopMoving());
		jda.addEventListener(new Memer());
		jda.addEventListener(new EmoteLib());
		jda.addEventListener(new ListReload());
		jda.addEventListener(new Bild());
		jda.addEventListener(new Spoiler());
		jda.addEventListener(new ChangeGame());
		jda.addEventListener(new EightBall());
		jda.addEventListener(new ChangelogCreator());
		//jda.addEventListener(new Lists());
		jda.addEventListener(new Me());
		jda.addEventListener(new Discriminator());
		
		EmoteLib.setEmotes();
		
		//System.out.println("[INFO] >> Current User: " + USER.getName());
		System.out.println("[SUCCESSFUL] >> Added all EventListeners");
		System.out.println("[SUCCESSFUL] >> Activating BetterSelf");
		LOG.warn("[INFO] >> Dont use this to spam others discord server");
	}
	
	public static void setup(){
		try{
			File file = new File(dir + fileName);
			if(!file.exists()){
				if(file.createNewFile()){
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					writer.write(GSON.toJson(CONFIG));
					writer.close();
					LOG.info("Succesful created CONFIG file in " + dir);
					System.exit(0);
				} else {
					LOG.info("Something went wrong. Error will be checked");
					System.exit(0);
				}
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				CONFIG = GSON.fromJson(reader, Config.class);
				if(CONFIG.getToken() == null || CONFIG.getToken().isEmpty()) {
					LOG.error("There is no token. Insert your personal token to use this bot");
					return;
				} 
				if (CONFIG.prefix == null) {
					LOG.error("There is no prefix. Set your prefix for your commands");
					return;
				}
				LOG.info("Config has been loaded ... ");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void login(){
        while (jda.getStatus() != Status.CONNECTED) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        }
	}
	
	public static void installer() throws IOException{
		if(OS.indexOf("win") >= 0){
		File installer = new File("start.bat");
			if(!installer.exists()) {
				if(installer.createNewFile()) {
					BufferedWriter write = new BufferedWriter(new FileWriter(installer));
					write.write("@echo off");
					write.newLine();
					write.write("java -jar BetterSelf.jar");
					write.newLine();
					write.write("pause");
					write.close();
//					LOG.info("Successful created Start.bat");
				} else {
//					LOG.fatal("There was a problem? I dont now the problem");
				}
			}
		} else {
//			LOG.info("Detected differend system. Not windows. Startup will come soon");
		}
	}
}
