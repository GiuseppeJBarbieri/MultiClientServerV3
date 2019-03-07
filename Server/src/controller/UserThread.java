package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Message_Object;
import view.Server_View_Controller;

public class UserThread implements Runnable {

	private Socket socket;
	private Server_Thread server_Thread;
	private ServerDataController serverDataController;
	private Server_View_Controller viewController;
	private ObjectOutputStream outputToClient;
	
	public UserThread(Socket socket, Server_View_Controller viewController, Server_Thread server_Thread) {
		this.socket = socket;
		this.server_Thread = server_Thread;
		this.viewController = viewController;
		serverDataController = new ServerDataController();
	}

	@Override
	public void run() {
		while(true) {
			try {
				ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
				outputToClient = new ObjectOutputStream(socket.getOutputStream());
				Object object = inputFromClient.readObject();
				serverDataController.directInformationRequest(object, outputToClient, viewController, server_Thread);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		}
	}

	public void sendMessage(Message_Object obj) {
		try {
			outputToClient = new ObjectOutputStream(socket.getOutputStream());
			outputToClient.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
