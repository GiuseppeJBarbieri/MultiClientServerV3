package requests;

import controller.Server_Thread;
import model.Message_Object;

public class Message_Request {

	public Message_Request(Object object, Server_Thread server_Thread) {
		Message_Object message = (Message_Object) object;
		server_Thread.broadcast(message.getMessage());
	}

}
