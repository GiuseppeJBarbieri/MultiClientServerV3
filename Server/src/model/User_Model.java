package model;

public class User_Model {

	private String ipAddy;
	private String username;
	public User_Model(String ipAddy, String username) {
		super();
		this.ipAddy = ipAddy;
		this.username = username;
	}
	public String getIpAddy() {
		return ipAddy;
	}
	public String getUsername() {
		return username;
	}
	
	
}
