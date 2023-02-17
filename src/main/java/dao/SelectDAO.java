package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Post;

public class SelectDAO {
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
	/**
	 * 投稿内容全件検索
	 * @author hnakamura
	 * @param String postText
	 * @param int likes
	 */
	public static ArrayList<Post> selectPost() {
		System.out.println("selectPost実行");
		String sql = "SELECT p.text,p.likes,p.createdtime,s.account_name FROM post AS p inner join SNSAccount AS s ON p.user_id = s.user_id";
		String account_name;
		String post;
		int likes;

		String createdtime;
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			try (ResultSet rs = pstmt.executeQuery()){
				//return用のリストを作成
				ArrayList<Post> list = new ArrayList<Post>(); 
				while(rs.next()) {
					account_name = rs.getString("account_name");
					post = rs.getString("text");
					likes = rs.getInt("likes");
					createdtime = rs.getString("createdtime");
					
					Post result = new Post(account_name, post, likes,null, createdtime);
					list.add(result);

				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return null;

	}
}
