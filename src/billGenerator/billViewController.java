package billGenerator;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.time.*;
import java.time.chrono.*;
import java.time.temporal.ChronoUnit;

import database.ConnectDatabase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import sms.SST_SMS;

public class billViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboMob;

    @FXML
    private Button btnGetDetails;

    @FXML
    private TextField txtName;

    @FXML
    private ListView<String> lstPaper;

    @FXML
    private ListView<String> lstPrice;

    @FXML
    private TextField txtDays;

    @FXML
    private Button btnBill;

    @FXML
    private TextField txtAmount;

    @FXML
    private Button btnStore;
    
    @FXML
    private DatePicker txtDate;
    
    @FXML
    private Label lblResult;
    
	 PreparedStatement pstmt;
	 Connection con;
	 LocalDate ldob=null;
	 String mob=null;
	 float price = 0f;
	 int days=0;
	 LocalDate date=null;
	 String name="";
	 

    @FXML
    void doGetDetails(ActionEvent event) {
    	mob = comboMob.getEditor().getText();
    	lstPaper.getItems().clear();
    	lstPrice.getItems().clear();
    
    	try 
    	{
			pstmt=con.prepareStatement("select * from customers where mobile=?");
			pstmt.setString(1, mob);
			ResultSet table = pstmt.executeQuery();
			if(table.next())
			{
				name = table.getString("name");
				txtName.setText(name);
				String papers = table.getString("paper");
				String p[] = papers.split(",");
				ArrayList<String>lpaper = new ArrayList<String>(Arrays.asList(p));
				for(String paper : lpaper)
				{
					lstPaper.getItems().add(paper);
				}
			}
			lstPaper.getSelectionModel().selectAll();
    	} 
    	
    	catch (SQLException e) 
    	{
    		e.printStackTrace();
		}
    	getPrice();
    }
    
    void getPrice()
    {
    	ObservableList<String> sel = lstPaper.getSelectionModel().getSelectedItems();
    	for(String sp : sel)
    	{
    		try
    		{
				pstmt = con.prepareStatement("select * from papers where paper=?");
				pstmt.setString(1, sp);
				ResultSet table = pstmt.executeQuery();
				if(table.next())
				{
					String price = table.getString("price");
					lstPrice.getItems().add(price);
				}
			}
    		catch (SQLException e) 
    		{
    			e.printStackTrace();
			}
    	}
    	lstPrice.getSelectionModel().selectAll();
    }

    @FXML
    void doPrepareBill(ActionEvent event) {
    	date = txtDate.getValue();
    	days = (int) ChronoUnit.DAYS.between(ldob, date);
    	txtDays.setText(String.valueOf(days));
    	ObservableList<String> sel = lstPrice.getSelectionModel().getSelectedItems();
    	for(String p: sel)
    	{
    		price = price + Float.parseFloat(p)*days;
    	}
    	txtAmount.setText(String.valueOf(price));
    	try 
    	{
			pstmt = con.prepareStatement("update customers set dateofjoin=DATE_ADD(CURRENT_DATE,INTERVAL 1 DAY) where mobile=?");
			pstmt.setString(1, mob);
			pstmt.executeUpdate();
			
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}

    }

    @FXML
    void doStore(ActionEvent event) {
    	java.sql.Date swd = java.sql.Date.valueOf(date);
    	try 
    	{
			pstmt = con.prepareStatement("insert into billig(custmob,noofdays,dateofbill,amount) values(?,?,?,?)");
			pstmt.setString(1, mob);
			pstmt.setInt(2, days);
			pstmt.setDate(3, swd);
			pstmt.setFloat(4, price);
			pstmt.executeUpdate();
			sendSMS(mob,"Hello " + name + " Your Bill is prepared for the month. Your Bill is " + price + ". Please Pay before the due date.");
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
    	lstPaper.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	lstPrice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	ResultSet customer= null;
    	try 
    	{
			pstmt = con.prepareStatement("select * from customers");
			customer = pstmt.executeQuery();
			while(customer.next())
			{
				  comboMob.getItems().addAll(customer.getString("mobile")); 
				  java.sql.Date dobb = customer.getDate("dateofjoin");
				  ldob=	dobb.toLocalDate();
			}
			
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	
    }
}
