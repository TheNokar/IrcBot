package net.plommer.IRCBot;

import java.io.BufferedWriter;
import java.io.IOException;

public class U {

	public static String b(String[] s) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < s.length; i++) {
			String string = s[i];
			builder.append(string + " ");
		}
		builder.append("\r\n");
		String a = builder.toString();
		a = colorParser(a);
		return a;
	}
	
	public static void sendMessage(String s) {
		BufferedWriter bw = ConnectToServer.w;
		if(bw != null) {
			try {
				s = colorParser(s);
				bw.write("PRIVMSG " + Main.channel + " " +s + " \r\n");
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void sendMessage(String s, String[] arr) {
		BufferedWriter bw = ConnectToServer.w;
		if(arr.length > 0) {
			for(int i = 0; i < arr.length; i++) {
				if(s.contains("{"+i+"}")) {
					String rplc = arr[i];
					if(rplc == null) {
						rplc = "UGH";
					}
					s = s.replace("{"+i+"}", rplc);
				}
			}
		}
		try {
			s = colorParser(s);
			bw.write("PRIVMSG " + Main.channel + " " +s + " \r\n");
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getMessageString(String s, String[] arr) {
		if(arr.length > 0) {
			for(int i = 0; i < arr.length; i++) {
				if(s.contains("{"+i+"}")) {
					String rplc = arr[i];
					if(rplc == null) {
						rplc = "UGH";
					}
					s = s.replace("{"+i+"}", rplc);
				}
			}
		}
		s = colorParser(s);
		return s += "\r\n";
	}
	
	public static String colorParser(String s) {
		if(s.contains("&0")) {
			s = s.replace("&0", "\0031");
		}
		if(s.contains("&1")) {
			s = s.replace("&1", "\0032");
		}
		if(s.contains("&2")) {
			s = s.replace("&2", "\0033");
		}
		if(s.contains("&3")) {
			s = s.replace("&3", "\00310");
		}
		if(s.contains("&4")) {
			s = s.replace("&4", "\0035");
		}
		if(s.contains("&5")) {
			s = s.replace("&5", "\0036");
		}
		if(s.contains("&6")) {
			s = s.replace("&6", "\0037");
		}
		if(s.contains("&7")) {
			s = s.replace("&7", "\00315");
		}
		if(s.contains("&8")) {
			s = s.replace("&8", "\00314");
		}
		if(s.contains("&9")) {
			s = s.replace("&7", "\00312");
		}
		if(s.contains("&a")) {
			s = s.replace("&a", "\00319");
		}
		if(s.contains("&b")) {
			s = s.replace("&b", "\00311");
		}
		if(s.contains("&c")) {
			s = s.replace("&c", "\0034");
		}
		if(s.contains("&e")) {
			s = s.replace("&e", "\0038");
		}
		if(s.contains("&d")) {
			s = s.replace("&d", "\00313");
		}
		if(s.contains("&e")) {
			s = s.replace("&e", "\00318");
		}
		return s;
	}
	
}
