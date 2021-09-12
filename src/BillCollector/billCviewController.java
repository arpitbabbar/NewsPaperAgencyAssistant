package BillCollector;

import database.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import sms.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class billCviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboMob;

    @FXML
    private Button btnFetch;

    @FXML
    private ListView<String> lstDate;

    @FXML
    private ListView<String> lstAmount;

    @FXML
    private TextField txtBill;

    @FXML
    private Button btnBill;

    @FXML
    private Label lblResult;
    
    PreparedStatement pstmt;
	Connection con;
	Float price=0f;
	Float pr=0f;
	String mob=null;

    @FXML
    void doFetch(ActionEvent event) {
    	mob = comboMob.getEditor().getText();
    	//comboMob.setDisable(true);
    	lstAmount.getItems().clear();
    	lstDate.getItems().clear();
    	ResultSet table=null;
    	try 
    	{
			pstmt = con.prepareStatement("select * from billig where status=0 and custmob=?");
			pstmt.setString(1, mob);
			table = pstmt.executeQuery();
			if(table.next())
			{
				java.sql.Date swd = table.getDate("dateofbill");
				String date = String.valueOf(swd);
				price =table.getFloat("amount");
				lstDate.getItems().add(date);
				lstAmount.getItems().add(String.valueOf(price));
			}
		} 
    	
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	doBill();
    }
    void doBill()
    {	
    	pr=(float) 0;
    	lstAmount.getSelectionModel().selectAll();
    	lstDate.getSelectionModel().selectAll();
    	ObservableList<String> sel = lstAmount.getSelectionModel().getSelectedItems();
    	for(String sp:sel)
    	{
    		pr=pr+Float.parseFloat(sp);
    	}
    	txtBill.setText(String.valueOf(pr));
    }
    @FXML
    void doPaidBill(ActionEvent event) {
 
    	try 
    	{
			pstmt = con.prepareStatement("update billig set status=1 where custmob=?");
			pstmt.setString(1, mob);
			pstmt.executeUpdate();
			sendSMS(mob,"Bill Paid");
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
    void sendSMS(String mobile,String msg)
    {
    	String resp=SST_SMS.bceSunSoftSend(mobile, msg);
    	
    	
    	if(resp.indexOf("Exception")!=-1)
    	lblResult.setText("Check Internet Connection");
    	
    	else
    		if(resp.indexOf("successfully")!=-1)
    			lblResult.setText("Sent");
    		else
    			lblResult.setText( "Invalid Number");
    }
    @FXML
    void initialize() {
    	con = ConnectDatabase.getConnection();
    	lstDate.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	lstAmount.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	ResultSet customer= null;
    	try 
    	{
			pstmt = con.prepareStatement("select * from billig where status=0");
			customer = pstmt.executeQuery();
			while(customer.next())
			{
				  comboMob.getItems().addAll(customer.getString("custmob")); 
			}
			
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
}
