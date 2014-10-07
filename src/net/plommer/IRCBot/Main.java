package net.plommer.IRCBot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import net.plommer.IRCBot.Commands.*;
import net.plommer.IRCBot.MySQL.Database;

public class Main {

	public static String server = "irc.lolnet.is";
	public static int port = 6667;
	public static String nick = "SB";
	public static String login = "SB";
	public static String channel = "#brbad";
	public static Database db;

	public static String muser = "root";
	public static String mpass = "root";
	
	public static ArrayList<Commands> cmds = new ArrayList<Commands>();
	
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Server ip: ");
			server = bf.readLine();
			System.out.print("Port: ");
			port = Integer.parseInt(bf.readLine());
			System.out.print("Nick: ");
			nick = bf.readLine();
			login = nick;
			System.out.print("Channel name: ");
			channel = bf.readLine();
			System.out.print("MySQL User: ");
			muser = bf.readLine();
			System.out.print("MySQL Pass: ");
			mpass = bf.readLine();
			File f = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			File file = new File(f.getParent() + "/mysql-connector-java-5.1.18.jar");
			if(!file.exists()) {
				new UrlDownload("http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.33/mysql-connector-java-5.1.33.jar", "mysql-connector-java-5.1.18.jar");
				System.out.println("Downloading mysql");
			}
			Class.forName("com.mysql.jdbc.Driver");
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
			db = new Database();
			registerCommands();
			new ConnectToServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void registerCommands() {
		cmds.add(new TestCommand());
		cmds.add(new LogCommand());
		//cmds.add(new RektCommand());
		cmds.add(new RanksCommand());
		cmds.add(new StopCommand());
		cmds.add(new AddPermissionsCommand());
	}
	
}
