package net.plommer.IRCBot.Commands;

import net.plommer.IRCBot.Main;
import net.plommer.IRCBot.U;

public class AddPermissionsCommand extends Commands {

	public AddPermissionsCommand() {
		setCMDName("permissions:permission:perm");
		setPermissions("permission");
	}
	
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(getArgs().length > 2) {
			Main.db.addPermissions(getArgs()[1], getArgs()[2]);
			U.sendMessage("{0} hefur verið gefið leyfi á: {1}", new String[] {getArgs()[1], getArgs()[2]});
		}
		return false;
	}
	
	
}
