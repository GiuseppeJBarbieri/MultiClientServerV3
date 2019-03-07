package model;

import java.io.Serializable;

public class Login_Object implements Serializable {

	private String userName;

	public Login_Object(String userName) {
		super();
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
	
}
