package net.plommer.IRCBot;

public class ParseIrcMessage {

	private String message;
	private boolean isMc = false;
	private String mcUser = null;
	
	public ParseIrcMessage(String s) {
		setMessage(s);
	}
	
	public void setMessage(String s) {
		this.message = s;
	}
	public String getMessage() {
		return this.message;
	}
	
	public String getUser() {
		String user = null;
		if(getMessage().contains("!")) {
			user = getMessage().split("!")[0].replace(":", "");
		}
		getMsg();
		if(isMc == true) {
			user = mcUser;
		}
		return user;
	}
	public String getChannel() {
		return getMessage().split(" ")[2];
	}
	public String getMsg() {
		String msg = null;
		int ts = getMessage().indexOf(" :");
		if(ts > 0) {
			msg = getMessage().substring(ts + 2);
			for(String s : msg.split(" ")) {
				if(s.matches("<[a-zA-Z0-9\\._\\-]{3,}>")) {
					msg = msg.replaceFirst(" ", "");
					msg = msg.replace(s, "");
					mcUser = s.replace("<", "").replace(">", "");
					isMc = true;
				}
			}
		}
		return msg;
	}
	
}
