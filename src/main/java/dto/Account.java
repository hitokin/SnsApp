package dto;

public class Account {
	private String userName;
	private String userId;
	private String userMail;
	private String userBirthday;
	private String password;
	private String salt;
	
	public Account(String userName, String userId, String userMail, String userBirthday, String password, String salt) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.userMail = userMail;
		this.userBirthday = userBirthday;
		this.password = password;
		this.salt = salt;
	}
		
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public  String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
