package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Message_Object;
import view.Client_View_Controller;

public class ReadThread implements Runnable {

	private Socket socket;
	private Client_View_Controller clientViewController;
	private ObjectInputStream input;

	public ReadThread(Socket socket, Client_View_Controller clientViewController) {
		this.socket = socket;
		this.clientViewController = clientViewController;

		try {
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean run = true;
		while (run) {
			try {
				input = new ObjectInputStream(socket.getInputStream());
				Message_Object message = (Message_Object) input.readObject();
				clientViewController.appendText(message.getMessage());
			} catch (IOException | ClassNotFoundException e) {
				run = false;
				try {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Server Shut Down");
							alert.setHeaderText("You have been logged out by the server!");
							alert.setContentText("Click ok to continue...");
							alert.showAndWait();
							Platform.exit();
						}
					});
					input.close();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
