package de.lesh.betterself.util;

public class Config {

	private String userToken = "";
	public String prefix = "";
	public boolean privat;
	
	public boolean getPrivat() {
		return privat;
	}

	public String getToken(){
		return userToken;
	}
	
	public String getPrefix() {
		return prefix;
	}
}
