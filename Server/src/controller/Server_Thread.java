package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import model.Message_Object;
import view.Server_View_Controller;

public class Server_Thread implements Runnable {

	private Server_View_Controller viewController;
	private ServerSocket serverSocket;
	private Socket socket;
	private Set<UserThread> userThreads = new HashSet<>();
	private Set<String> userNames = new HashSet<>();
	private Thread t1;

	public Server_Thread(Server_View_Controller viewController) {
		try {
			this.viewController = viewController;
			serverSocket = new ServerSocket(8000);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			viewController.appendText("Server Started!");
			while (true) {
				socket = serverSocket.accept();
				viewController.appendText("New User Connected!");
				UserThread newUser = new UserThread(socket, viewController, this);
				userThreads.add(newUser);
				viewController.addUser(socket);
				t1 = new Thread(newUser);
				t1.setDaemon(true);
				t1.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void broadcast(String message) {
		for (UserThread aUser : userThreads) {
			aUser.sendMessage(new Message_Object(message));
		}
	}
	
	/**
	 * Stores username of the newly connected client.
	 */
	void addUserName(String userName) {
		userNames.add(userName);
	}
	
}
