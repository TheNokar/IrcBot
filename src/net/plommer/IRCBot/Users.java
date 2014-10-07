package net.plommer.IRCBot;

public class Users {

	private String user;
	
	public Users(String user) {
		setUser(user);
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	public String getUser() {
		return this.user;
	}
	
	public boolean hasPermissions(String permission) {
		if(Main.db.hasPermissions(getUser(), permission)) {
			return true;
		}
		return false;
	}
	
}
