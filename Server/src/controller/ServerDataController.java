package controller;

import model.Login_Object;
import model.Message_Object;
import requests.Login_Request;
import requests.Message_Request;

public class ServerDataController {

	public void directInformationRequest(Object object, Server_Thread server_Thread, UserThread userThread) {
		if (object.getClass() == Login_Object.class) {
			new Login_Request(object, server_Thread, userThread);
		} else if(object.getClass() == Message_Object.class) {
			new Message_Request(object, server_Thread);
		}

	}

}
