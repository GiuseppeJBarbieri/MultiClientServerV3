package controller;

import java.io.ObjectOutputStream;

import model.Login_Object;
import model.Message_Object;
import view.Server_View_Controller;

public class ServerDataController {

	public void directInformationRequest(Object object, ObjectOutputStream outputToClient,
			Server_View_Controller viewController, Server_Thread server_Thread) {
		if (object.getClass() == Login_Object.class) {
			Login_Object loginObj = (Login_Object) object;
			server_Thread.addUserName(loginObj.getUserName());
			String serverMessage = "New User Connected: " + loginObj.getUserName();
			System.out.println("Sending Message");
			server_Thread.broadcast(serverMessage);
		} else if(object.getClass() == Message_Object.class) {
			Message_Object message = (Message_Object) object;
			server_Thread.broadcast(message.getMessage());
		}

	}

}
