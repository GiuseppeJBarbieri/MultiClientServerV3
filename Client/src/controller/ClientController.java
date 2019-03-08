package controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Platform;
import view.Client_View_Controller;

public class ClientController implements Runnable {

	private String hostname;
	private int port;
	private Client_View_Controller clientViewController;

	private ReadThread readThread;
	private Thread t1;
	private Socket socket;
	
	public ClientController(String hostname, int port, Client_View_Controller clientViewController) {
		this.hostname = hostname;
		this.port = port;
		this.clientViewController = clientViewController;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(hostname, port);
			clientViewController.appendText("Connected to server!");

			readThread = new ReadThread(socket, clientViewController);
			t1 = new Thread(readThread);
			t1.setDaemon(true);
			t1.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ReadThread getReadThread() {
		return readThread;
	}
	
	public WriteThread getWriteThread() {
		try {
			socket = new Socket(hostname, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		WriteThread writeThread = new WriteThread(socket);
		return writeThread;
	}

	public void closeStage() {
		
		Platform.exit();
	}
}
