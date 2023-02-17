package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDAO {
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
	//投稿内容登録機能
	public static boolean SubmissionDdetails(String SubmissioDetails,String userId) {
		String sql = "INSERT INTO post(text,likes,user_id,createdtime,del_flg) VALUES (?,?,?,NOW(),0)";
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, SubmissioDetails);
			pstmt.setInt(2, 0);
			pstmt.setString(3, userId);
			
			if (pstmt.executeQuery() == null) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return true;
	}
}
