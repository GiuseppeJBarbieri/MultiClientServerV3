package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Show_Client_View;

public class App_Client extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		new Show_Client_View(stage);
	}

}
