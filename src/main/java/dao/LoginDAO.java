package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Account;
import util.GenerateSalt;

public class LoginDAO {
	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		
		return DriverManager.getConnection(dbUrl, username, password);
		}
	
	public static Account login(String mail, String hashedPw) {
		String sql = "SELECT * FROM SNSAccount WHERE mail = ? AND password = ?";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);
			pstmt.setString(2, hashedPw);

			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					String userName = rs.getString("account_name");
					String userMail = rs.getString("mail");
					String userId = rs.getString("user_id");
					String password = rs.getString("password");
					String salt = rs.getString("salt");
					String userBirthday = rs.getString("birthday");
					return new Account(userName, userMail, userId, password, salt, userBirthday);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
		public static String getSalt(String account_id) {
		String sql = "SELECT salt FROM SNSAccount WHERE mail = ?";
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, account_id);
			
			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					String salt = rs.getString("salt");
					return salt;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public static void main(String args[]) {
		String salt = GenerateSalt.getSalt(32);
		System.out.println(salt);
	}	
	}
