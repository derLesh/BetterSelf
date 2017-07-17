package de.lesh.betterself;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.lesh.betterself.commands.*;
import de.lesh.betterself.commands.admin.*;
import de.lesh.betterself.commands.admin.org.*;
import de.lesh.betterself.commands.admin.survey.*;
import de.lesh.betterself.commands.fun.*;
import de.lesh.betterself.commands.info.*;
import de.lesh.betterself.commands.url.*;
import de.lesh.betterself.commands.user.*;
import de.lesh.betterself.util.Config;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDA.Status;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.JDAInfo;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.utils.SimpleLog;

public class Main {

	public static JDA jda = null;
	public static Config CONFIG = new Config();
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	public static String fileName = "config.json";
	public static String dir = System.getProperty("user.dir") + "/";
    public static final SimpleLog LOG = SimpleLog.getLog("BetterSelf");
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

		login();
		USER = jda.getSelfUser();
		LOG.info("Succesful logged into " + USER.getName());
		
		jda.addEventListener(new Unshortener());
		jda.addEventListener(new Ban());
		jda.addEventListener(new Kick());
		jda.addEventListener(new WhatAPleb());
		jda.addEventListener(new Github());
		jda.addEventListener(new NoMoney());
		jda.addEventListener(new ServerInfo());
		jda.addEventListener(new ListBots());
		jda.addEventListener(new Reaction());
		jda.addEventListener(new Lmgtfy());
		//jda.addEventListener(new Translate());
		jda.addEventListener(new UserCard());
		jda.addEventListener(new Survey());
		//jda.addEventListener(new SurveyClose());
		jda.addEventListener(new DeleteMSG());
		jda.addEventListener(new Memes());
		jda.addEventListener(new Duden());
		jda.addEventListener(new Postillon());
		
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
					LOG.fatal("There is no token. Insert your personal token to use this bot");
					return;
				} 
				if (CONFIG.prefix == null) {
					LOG.fatal("There is no prefix. Set your prefix for your commands");
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
					LOG.info("Successful created Start.bat");
				} else {
					LOG.fatal("There was a problem? I dont now the problem");
				}
			}
		} else {
			LOG.info("Detected differend system. Not windows. Startup will come soon");
		}
	}
}
