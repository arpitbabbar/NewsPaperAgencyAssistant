package front;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class frontViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void doExit(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void doLogin(MouseEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("login/loginView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }
}
