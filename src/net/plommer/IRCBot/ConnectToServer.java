package net.plommer.IRCBot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import net.plommer.IRCBot.Commands.CommandHandler;

public class ConnectToServer implements Runnable {
	
	public static BufferedWriter w = null;
	public static BufferedReader r = null;
	public static Socket s = null;
	public ConnectToServer() {
		try {
			s = new Socket(Main.server, Main.port);
			w = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));
			r = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
			if(s.isConnected()) {
				w.write(U.getMessageString("PASS {0}:{1}", new String[] {"NRG", "plommer123"}));
				w.write(U.getMessageString("NICK {0}", new String[] {Main.nick}));
				w.write(U.getMessageString("USER {0} {1}", new String[] {Main.login, "8 * : Bot"}));
				w.flush();
				System.out.println(U.b(new String[] {"Starting the Thread!"}));
				new Thread(this).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Can't connect to server");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				String line = null;
		        while ((line = r.readLine( )) != null) {if (line.indexOf("004") >= 0) {break;} else if (line.indexOf("433") >= 0) {System.out.println("Nickname is already in use.");return;}}
		        //Connect to channel
				w.write(U.getMessageString("JOIN {0}", new String[]{Main.channel}));
				w.flush();
				while((line = r.readLine()) != null) {
					if(line.startsWith("PING ")) {
						w.write(U.getMessageString("PONG {0}", new String[]{line.substring(5)}));
						w.flush();
					} else {
						ParseIrcMessage pirc = new ParseIrcMessage(line);
						if(pirc.getUser() != null) {
							System.out.print(U.getMessageString("{0} - {1}: {2}", new String[]{pirc.getChannel(), pirc.getUser(), pirc.getMsg()}));
							if(!CommandHandler.CheckCommand(pirc.getMsg(), pirc.getUser())) {
								Main.db.addLog(pirc.getUser(), pirc.getMsg(), pirc.getChannel());
							}
						} else {
							System.out.println(pirc.getMessage());
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
