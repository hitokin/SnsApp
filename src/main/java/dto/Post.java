package dto;

public class Post {
	private String account_name;
	private String post;
	private int likes;
	private String mail;
	private String createdtime;
	
	public Post(String account_name, String post, int likes, String mail, String createdtime) {
		super();
		this.account_name = account_name;
		this.post = post;
		this.likes = likes;
		this.mail = mail;
		this.createdtime = createdtime;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}	

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	
	
}
