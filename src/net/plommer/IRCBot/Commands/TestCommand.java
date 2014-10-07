package net.plommer.IRCBot.Commands;

import net.plommer.IRCBot.U;

public class TestCommand extends Commands {

	public TestCommand() {
		setCMDName("test:t:hello:h");
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		U.sendMessage("&cTEST");
		return false;
	}

}
