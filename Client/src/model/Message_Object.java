package model;

import java.io.Serializable;

public class Message_Object implements Serializable {

	private String message;

	public Message_Object(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
