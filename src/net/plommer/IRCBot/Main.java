package net.plommer.IRCBot;

import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import net.plommer.IRCBot.Commands.*;
import net.plommer.IRCBot.MySQL.Database;

public class Main {

	public static String server = "irc.lolnet.is";
	public static String nick = "UGH";
	public static String login = "UGH";
	public static String channel = "#bots";
	public static Database db;
	
	public static ArrayList<Commands> cmds = new ArrayList<Commands>();
	
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			File f = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			File file = new File(f.getParent() + "/mysql-connector-java-5.1.18.jar");
			if(!file.exists()) {
				new UrlDownload("http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.33/mysql-connector-java-5.1.33.jar", "mysql-connector-java-5.1.18.jar");
				System.out.print("Downloading mysql");
			}
			Class.forName("com.mysql.jdbc.Driver");
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
			db = new Database();
			registerCommands();
			new ConnectToServer();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void registerCommands() {
		cmds.add(new TestCommand());
		cmds.add(new LogCommand());
		cmds.add(new RektCommand());
	}
	
}
