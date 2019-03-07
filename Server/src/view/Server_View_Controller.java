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

public class Server_View_Controller  implements Initializable {
	
	@FXML
	private Button startBtn, messageBtn;
	@FXML
	private TextArea consoleTextArea;
	@FXML
	private TextField messageTxt;
	@FXML
	private TableColumn<String, String> ipCol;
	@FXML
	private TableView<String> ipTblView;

	@SuppressWarnings("unused")
	private Stage stage;
	private Thread t1;
	private Server_Thread server;
	private ArrayList<String> userList;
	private Set<Socket> socketList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		socketList = new HashSet<Socket>();
		userList = new ArrayList<>();
		startBtn.setOnAction(e -> startServer());
		messageBtn.setOnAction(e -> sendMessage());
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
	}
	
	public void appendToTextField(String string) {
		consoleTextArea.appendText(string + "\n");
	}

	public void addUser(Socket socket) {
		socketList.add(socket);
		for(Socket e: socketList) {
			userList.add(e.getInetAddress().toString());
		}
		
		ObservableList<String> tableList = FXCollections.observableArrayList(userList);
		ipTblView.setItems(tableList);
	}

}