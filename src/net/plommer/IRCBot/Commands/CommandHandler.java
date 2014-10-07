package net.plommer.IRCBot.Commands;

import net.plommer.IRCBot.Main;

public class CommandHandler {

	public static boolean CheckCommand(String cmd, String user) {
		if(cmd != null && user != null) {
			if(cmd.startsWith("!")) {
				cmd = cmd.substring(1);
				String cmd_name = cmd.split(" ")[0];
				for(Commands c : Main.cmds) {
					String ocmd_name = c.getCMDName();
					if(ocmd_name.contains(":")) {
						for(String cm : ocmd_name.split(":")) {
							if(cm.equalsIgnoreCase(cmd_name)) {
								cmd_name = c.getCMDName();
							}
						}
					}
					if(cmd_name.equalsIgnoreCase(ocmd_name)) {
						return c.run(cmd_name, user, cmd.split(" "));
					}
				}
			}
		}
		return false;
	}
	
}
