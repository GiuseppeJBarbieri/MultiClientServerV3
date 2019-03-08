package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class WriteThread {
	private ObjectOutputStream output;
	private Socket socket;
	

	public WriteThread(Socket socket) {
		this.socket = socket;
		
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
