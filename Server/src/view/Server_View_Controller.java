package view;

import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import controller.Server_Thread;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.User_Model;

public class Server_View_Controller implements Initializable {

	@FXML
	private Button startBtn, messageBtn;
	@FXML
	private TextArea consoleTextArea;
	@FXML
	private TextField messageTxt;
	@FXML
	private TableColumn<User_Model, String> ipCol, usernameCol;
	@FXML
	private TableView<User_Model> ipTblView;

	@SuppressWarnings("unused")
	private Stage stage;
	private Thread t1;
	private Server_Thread server;
	private ArrayList<User_Model> userList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userList = new ArrayList<>();
		setTableColumns();
		startBtn.setOnAction(e -> startServer());
		messageBtn.setOnAction(e -> sendMessage());
	}

	private void setTableColumns() {
		ipCol.setCellValueFactory(new PropertyValueFactory<User_Model, String>("ipAddy"));
		usernameCol.setCellValueFactory(new PropertyValueFactory<User_Model, String>("username"));
	}

	private void startServer() {
		server = new Server_Thread(this);
		t1 = new Thread(server);
		t1.setDaemon(true);
		t1.start();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void appendText(String message) {
		consoleTextArea.appendText(message + "\n");
	}

	private void sendMessage() {
		server.broadcast(messageTxt.getText());
	}

	public void appendToTextField(String string) {
		consoleTextArea.appendText(string + "\n");
	}

	public void addUser(Socket socket, String username) {
		userList.add(new User_Model(socket.getInetAddress().toString(), username));

		ObservableList<User_Model> tableList = FXCollections.observableArrayList(userList);
		ipTblView.setItems(tableList);
	}

	public void removeUser(Socket socket, String userName) {
		for (User_Model e : userList) {
			if (e.getIpAddy().equals(socket.getInetAddress().toString()) && userName.equals(e.getUsername())) {
				userList.remove(e);
				break;
			}
		}
		ObservableList<User_Model> tableList = FXCollections.observableArrayList(userList);
		ipTblView.setItems(tableList);

	}

}
