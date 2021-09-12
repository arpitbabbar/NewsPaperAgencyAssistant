package customerManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	@Override
 public void start(Stage primaryStage) 
   {
		try {
				Parent root=FXMLLoader.load(getClass().getResource("custView.fxml")); 
				Scene scene = new Scene(root,550,530);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("Customer Manager");
				primaryStage.setScene(scene);
				primaryStage.show();
		    } 
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
