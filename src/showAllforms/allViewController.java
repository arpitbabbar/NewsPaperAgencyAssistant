package showAllforms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class allViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

//    @FXML
//    private ImageView imgCustomer;
//
//    @FXML
//    private ImageView imgPaper;
//
//    @FXML
//    private ImageView imgHawker;
//
//    @FXML
//    private ImageView imgBillGen;
//
//    @FXML
//    private ImageView imgViewCust;
//
//    @FXML
//    private ImageView imgBollColl;

    @FXML
    void showBillCollector(MouseEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("BillCollector/billCview.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
    		
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void showBillGen(MouseEvent event1) {
    	try{
    		Parent root1=FXMLLoader.load(getClass().getClassLoader().getResource("billGenerator/billView.fxml")); 
			Scene scene1 = new Scene(root1);
			Stage stage1=new Stage();
			stage1.setScene(scene1);
			stage1.show();
    		
    		
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


    }

    @FXML
    void showCustomerDisplay(MouseEvent event2) {
    	try{
    		Parent root2=FXMLLoader.load(getClass().getClassLoader().getResource("CustomerDisplayBoard/CdisplayView.fxml")); 
			Scene scene2 = new Scene(root2);
			Stage stage2=new Stage();
			stage2.setScene(scene2);
			stage2.show();
    		
    		
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void showCustomerManager(MouseEvent event3) {
    	try{
    		System.out.println("*********************************");
    		Parent root3=FXMLLoader.load(getClass().getClassLoader().getResource("customerManager/custView.fxml")); 
			Scene scene3 = new Scene(root3);
			Stage stage3=new Stage();
			stage3.setScene(scene3);
			stage3.show();
    		
    		
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


    }

    @FXML
    void showHawker(MouseEvent event4) {
    	try{
    		Parent root4=FXMLLoader.load(getClass().getClassLoader().getResource("hawkerControlPanel/hawkerPanelView.fxml")); 
			Scene scene4 = new Scene(root4);
			Stage stage4=new Stage();
			stage4.setScene(scene4);
			stage4.show();
    		
    		
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


    }

    @FXML
    void showPaperMaster(MouseEvent event5) {
    	try{
    		Parent root5=FXMLLoader.load(getClass().getClassLoader().getResource("papermaster/paperview.fxml")); 
			Scene scene5 = new Scene(root5);
			Stage stage5=new Stage();
			stage5.setScene(scene5);
			stage5.show();
    		
    		
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

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
