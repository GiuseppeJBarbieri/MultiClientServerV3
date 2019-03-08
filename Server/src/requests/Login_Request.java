package requests;

import controller.Server_Thread;
import controller.UserThread;
import model.Login_Object;

public class Login_Request {

	public Login_Request(Object object, Server_Thread server_Thread, UserThread userThread) {
		Login_Object loginObj = (Login_Object) object;
		server_Thread.addUserName(loginObj.getUserName());
		userThread.setUsername(loginObj.getUserName());
		String serverMessage = "New User Connected: " + loginObj.getUserName();
		server_Thread.broadcast(serverMessage);
	}

}
