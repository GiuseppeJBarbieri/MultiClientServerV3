package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import model.Message_Object;
import view.Client_View_Controller;

public class ReadThread implements Runnable {

	private Socket socket;
	private ClientController clientController;
	private Client_View_Controller clientViewController;
	private ObjectInputStream input;

	public ReadThread(Socket socket, ClientController clientController, Client_View_Controller clientViewController) {
		this.socket = socket;
		this.clientController = clientController;
		this.clientViewController = clientViewController;

		try {
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				input = new ObjectInputStream(socket.getInputStream());
				Message_Object message = (Message_Object) input.readObject();
				clientViewController.appendText(message.getMessage());
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}