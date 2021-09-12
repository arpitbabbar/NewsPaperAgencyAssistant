package showAllforms;

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
				Parent root6=FXMLLoader.load(getClass().getResource("allView.fxml")); 
				Scene scene6 = new Scene(root6,500,500);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				primaryStage.setScene(scene6);
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

