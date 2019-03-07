package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Login_Object;
import model.Message_Object;

public class Client_View_Controller implements Initializable {

	@FXML
	private Button messageBtn, loginBtn;
	@FXML
	private TextField messageTxt, usernameTxt;
	@FXML
	private TextArea chatTextArea;

	@SuppressWarnings("unused")
	private Stage stage;
	private ClientController client;
	private Thread t1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		messageBtn.setOnAction(e -> sendMessage());
		loginBtn.setOnAction(e -> login());
	}
	
	private void login() {
		client = new ClientController("192.168.26.105", 8000, this);
		t1 = new Thread(client);
		t1.setDaemon(true);
		t1.start();
		client.getWriteThread().writeMessage(new Login_Object(usernameTxt.getText()));
	}

	private void sendMessage() {
		client.getWriteThread().writeMessage(new Message_Object(messageTxt.getText()));
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	public void appendText(String message) {
		chatTextArea.appendText(message + "\n");
	}

}
