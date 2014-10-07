package net.plommer.IRCBot.Commands;

import java.util.ArrayList;

import net.plommer.IRCBot.Main;
import net.plommer.IRCBot.U;

public class RektCommand extends Commands {

	public RektCommand() {
		setCMDName("rekt:re:r");
	}
	
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(getArgs().length > 1) {
			if(getArgs()[1].toLowerCase().contains("nokar")) {
				U.sendMessage("&aGET REKT &c{0}", new String[] {getUser()});
				Main.db.addRekt(getUser(), getUser());
			} else if(getArgs()[1].equalsIgnoreCase("count") && getArgs().length > 2) {
				if(!getArgs()[2].equalsIgnoreCase("top")) {
					int i = Main.db.getHowRekt(getArgs()[2]);
					String um = "sinni";
					if(i > 1) {
						um = "sinnum";
					}
					U.sendMessage("&c{0} &ehefur verið&c {1} &e{2} rektaður", new String[] {getArgs()[2], ""+i, um});
				}
			} else if(getArgs()[1].equalsIgnoreCase("top")) {
				ArrayList<String> s = Main.db.getRektTop();
				U.sendMessage("===[Mest Rektaðir]===");
				for(int i = 0; i < s.size(); i++) {
					String name = s.get(i);
					U.sendMessage("{0}. {1}", new String[] {""+(i+1), name});
				}
			} else {
				U.sendMessage("&aGET REKT &c{0}", new String[] {getArgs()[1]});
				Main.db.addRekt(getArgs()[1], getUser());
			}
		} else {
			U.sendMessage("&aGET REKT");
		}
		return false;
	}

}
