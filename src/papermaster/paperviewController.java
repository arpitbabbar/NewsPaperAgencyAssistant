package papermaster;
import database.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class paperviewController {
	
	 PreparedStatement pstmt;
	 Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> txtPaperTitle;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button btnFetch;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnRemove;
    
    @FXML
    private Label lblResult;


    @FXML
    void doFetch(ActionEvent event) {
    	String paper = txtPaperTitle.getEditor().getText();
    	try 
    	{
			pstmt=con.prepareStatement("Select * from papers where paper=?");
			pstmt.setString(1, paper);
			ResultSet table = pstmt.executeQuery();
			
			if(table.next())
			{
				float price = table.getFloat("price");
				txtPrice.setText(String.valueOf(price));
			}
			
			else
				lblResult.setText("Paper Not Found!");
			
		} 
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}

    }

    @FXML
    void doNew(ActionEvent event) {
    	txtPaperTitle.getEditor().setText("");
    	txtPrice.setText("");
    	lblResult.setText("");

    }

    @FXML
    void doRemove(ActionEvent event) {
    	try 
    	{
			pstmt = con.prepareStatement("delete from papers where paper=?");
			pstmt.setString(1, txtPaperTitle.getEditor().getText());
			
			int count = pstmt.executeUpdate();
			lblResult.setText(count + " Records Deleted");
			
			
		} 
    	catch (SQLException e)
    	{
			
			e.printStackTrace();
		}

    }

    @FXML
    void doSave(ActionEvent event) {
    	try 
    	{
			pstmt = con.prepareStatement("insert into papers values(?,?)");
			pstmt.setString(1, txtPaperTitle.getEditor().getText());
			pstmt.setFloat(2, Float.parseFloat(txtPrice.getText()));
			
			int count = pstmt.executeUpdate();
			
			lblResult.setText(count + " Records Saved");
		} 
    	catch (SQLException e)
    	{
		
			e.printStackTrace();
		}
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	try 
    	{
			pstmt=con.prepareStatement("update papers set price=? where paper=?");
			pstmt.setString(2, txtPaperTitle.getEditor().getText());
			pstmt.setFloat(1, Float.parseFloat(txtPrice.getText()));
			
			int count = pstmt.executeUpdate();
			lblResult.setText(count + " Records Updated");
			
    	} 
    	catch (SQLException e)
    	{
			
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
    	con = ConnectDatabase.getConnection();
    	   // first I execute the query that select name of department one by one
        ResultSet paperlist = null;
		try {
			pstmt = con.prepareStatement("select * from papers");
			paperlist = pstmt.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
			while (paperlist.next()) {  // loop

			   // Now add the comboBox addAll statement 
			    txtPaperTitle.getItems().addAll(paperlist.getString("paper")); 
      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
