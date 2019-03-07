package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import view.Client_View_Controller;

public class WriteThread {
	private ObjectOutputStream output;
	private Socket socket;
	private ClientController clientController;
	private Client_View_Controller clientViewController;
	

	public WriteThread(Socket socket, ClientController clientController, Client_View_Controller clientViewController) {
		this.socket = socket;
		this.clientController = clientController;
		this.clientViewController = clientViewController;
		
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeMessage(Object obj) {
		try {
			output.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}