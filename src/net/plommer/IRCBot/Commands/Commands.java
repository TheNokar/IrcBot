package net.plommer.IRCBot.Commands;

import net.plommer.IRCBot.U;
import net.plommer.IRCBot.Users;

public abstract class Commands {

	private String user;
	private String cmdname;
	private String[] args;
	private String permissions;
	
	public boolean run(String cmd, String user, String[] args) {
		Users u = new Users(user);
		if(!u.hasPermissions(getPermissions())) {
			U.sendMessage("&cÞú hefur ekki leyfi á þessu!");
			return false;
		}
		setUser(user);
		setArgs(args);
		return execute();
	}
	
	public abstract boolean execute();
	
	public String getUser() {
		return this.user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPermissions(String perm) {
		this.permissions = perm;
	}
	public String getPermissions() {
		return this.permissions;
	}
	
	public void setCMDName(String cmdname) {
		this.cmdname = cmdname;
	}
	public String getCMDName() {
		return this.cmdname;
	}
	
	public String[] getArgs() {
		return this.args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}
	
}
