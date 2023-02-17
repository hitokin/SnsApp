package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Account;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class AccountRegistDAO {
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
	public static int Regist(Account account) {
		String sql = "INSERT INTO SNSAccount VALUES(default, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		
		String salt = GenerateSalt.getSalt(32);
		String hashedPw = GenerateHashedPw.getSafetyPassword(account.getPassword(), salt);

		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, account.getUserName());
			pstmt.setString(2, account.getUserMail());
			pstmt.setString(3, account.getUserId());
			pstmt.setString(4, account.getUserBirthday());
			pstmt.setString(5, hashedPw);
			pstmt.setString(6, salt);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	
	public static String getSalt(String mail) {
		String sql = "SELECT salt FROM SNSAccount WHERE mail = ?";
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);
			
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
