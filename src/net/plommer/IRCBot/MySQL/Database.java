package net.plommer.IRCBot.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import net.plommer.IRCBot.Main;

public class Database {
	public Database() {
		try {
			this.db().prepareStatement(this.getSQLTables("rekt.sql")).execute();
			this.db().prepareStatement(this.getSQLTables("log.sql")).execute();
			this.db().prepareStatement(this.getSQLTables("permissions.sql")).execute();
			this.db().prepareStatement("SET GLOBAL max_connections=12000000;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection db() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/minecraft?autoReconnect=true&user="+Main.muser+"&password="+Main.mpass);
		} catch (SQLException ex) {
			System.out.println("Failed to connect to mysql. Plugin is now shutting down!");
		}
		return null;
	}
	
	public Integer getHowRekt(String user) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("SELECT count(username) FROM `rekt` where username = (?)");
			ps.setString(1, user);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean hasPermissions(String user, String permissions) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("SELECT count(*) FROM `bot_permissions` where username = (?) && permission = (?)");
			ps.setString(1, user);
			ps.setString(2, permissions);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				if(rs.getInt(1) > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public ArrayList<String> getRektTop() {
		PreparedStatement ps = null;
		ArrayList<String> rt = new ArrayList<String>();
		try {
			ps = this.db().prepareStatement("SELECT DISTINCT username FROM `rekt` GROUP BY username order by count(username) DESC");
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				rt.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rt;
	}
	
	public boolean addRekt(String user, String rekter) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("INSERT INTO `rekt` (username, rekt_by) VALUES (?, ?)");
			ps.setString(1, user);
			ps.setString(2, rekter);
			return ps.executeUpdate() != 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean addPermissions(String user, String permissions) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("INSERT INTO `bot_permissions` (username, permission) VALUES (?, ?)");
			ps.setString(1, user);
			ps.setString(2, permissions);
			return ps.executeUpdate() != 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Integer getLogsUserMsg(String user, String msg) {
		PreparedStatement ps = null;
		try {
			ps = this.db().prepareStatement("SELECT count(message) FROM `bot_log` where username = (?) && message LIKE('%"+msg+"%')");
			ps.setString(1, user);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean addLog(String user, String message, String channel) {
		PreparedStatement ps = null;
		if(user == null || message == null || channel == null) {
			return false;
		}
		try {
			ps = this.db().prepareStatement("INSERT INTO `bot_log` (username, message, channel) VALUES (?, ?, ?)");
			ps.setString(1, user);
			ps.setString(2, message);
			ps.setString(3, channel);
			return ps.executeUpdate() != 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				db().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private String getSQLTables(String table) {
		Scanner scan = new Scanner(Main.class.getResourceAsStream("sql/" + table));
		StringBuilder builder = new StringBuilder();
		while(scan.hasNextLine()) {
			builder.append(scan.nextLine());
		}
		scan.close();
		return builder.toString();
	}
	
}
