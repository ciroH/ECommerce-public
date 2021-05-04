package data;

import entities.*;
import java.sql.*;

public class DataUser {
	
	public User getOnLogin(User userRec) {
		User user = null;
		PreparedStatement loginStatement = null;
		ResultSet rs = null;
		try {
			loginStatement = DbConnector.getInstance().getConn().prepareStatement("select id,mail,password,name,usertype,userpic from user where mail=? and password=?");
			loginStatement.setString(1, userRec.getMail());
			loginStatement.setString(2, userRec.getPassword());
			
			rs = loginStatement.executeQuery();
			if (rs!=null && rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setUserType(rs.getString("usertype"));
				user.setUserPic(rs.getString("userpic"));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (loginStatement!=null) {
					loginStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	return user;
	}
	
	public boolean signIn(User user) {
		PreparedStatement signInStmt = null;
		if (!verifyUser(user)) {
			return false;
		}
		try {
			if(user.getUserType() == null) {
			signInStmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO user (mail,password,name) VALUES (?,?,?)"); //the default value for usertype on the DB is 'client', that's why it isn't specified in this INSERT 
			signInStmt.setString(1, user.getMail());
			signInStmt.setString(2, user.getPassword());
			signInStmt.setString(3, user.getName());
			}
			else {
				signInStmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO user (mail,password,name,usertype) VALUES (?,?,?,?)");
				signInStmt.setString(1, user.getMail());
				signInStmt.setString(2, user.getPassword());
				signInStmt.setString(3, user.getName());
				signInStmt.setString(4, user.getUserType());
			}
			signInStmt.executeUpdate(); //podría usar en su lugar execute(), que devuelve un booleano
			
		} catch (SQLException e) {
			e.printStackTrace();
			// devolver error de conexión a la db como un throwException al jsp de SignIn
		} finally {
			
			try {
				if (signInStmt!=null) {
					signInStmt.close();
				}
				DbConnector.getInstance().releaseConn();
					
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
			return true;
		
	}

	private boolean verifyUser(User user) { //couldn't find a workaround for reusing getOnLogin method (in this case, the query shouldn't check password) without overcomplicating readability of getOnLogin method.
		boolean response = false;					// - this method returns false if a user with the Email Address written on the SignIn form already exists on the DataBase.
		PreparedStatement loginStatement = null;
		ResultSet rs = null;
		try {
			loginStatement = DbConnector.getInstance().getConn().prepareStatement("select id,mail,password,name from user where mail=?");
			loginStatement.setString(1, user.getMail());
			
			rs = loginStatement.executeQuery();
			if (rs!=null && rs.next()) {
				response = false;
			} else {
				response = true;
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (loginStatement!=null) {
					loginStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return response;
	}
	
}
