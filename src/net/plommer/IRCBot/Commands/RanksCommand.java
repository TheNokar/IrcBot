package net.plommer.IRCBot.Commands;

import net.plommer.IRCBot.U;
import net.plommer.IRCBot.Utils;

public class RanksCommand extends Commands {

	public RanksCommand() {
		setCMDName("ranks:rank");
	}
	
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		U.sendMessage("--[Ranks]--");
		U.sendMessage("{0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}", Utils.s);
		return false;
	}

	
	
}
