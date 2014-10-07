package net.plommer.IRCBot.Commands;

import net.plommer.IRCBot.Main;
import net.plommer.IRCBot.U;

public class LogCommand extends Commands {

	public LogCommand() {
		setCMDName("log:l:lo");
		setPermissions("log");
	}
	
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(getArgs().length > 2) {
			int i = Main.db.getLogsUserMsg(getArgs()[1], getArgs()[2]);
			U.sendMessage("{0} hefu sagt {1} {2} sinnum!", new String[] {getArgs()[1], getArgs()[2], i+""});
		}
		return false;
	}

}
