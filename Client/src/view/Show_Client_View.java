package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Show_Client_View {

	public Show_Client_View(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Client_View_Skin.fxml"));
			AnchorPane root = loader.load();
			Client_View_Controller controller = loader.getController();
			controller.setStage(stage);
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
