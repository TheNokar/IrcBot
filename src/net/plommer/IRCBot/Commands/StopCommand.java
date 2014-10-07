package net.plommer.IRCBot.Commands;

import java.io.IOException;

import net.plommer.IRCBot.ConnectToServer;

public class StopCommand extends Commands {

	public StopCommand() {
		setCMDName("stop");
		setPermissions("stop");
	}
	
	@Override
	public boolean execute() {
		try {
			ConnectToServer.w.close();
			ConnectToServer.r.close();
			ConnectToServer.s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
