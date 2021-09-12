package BillHistory;

import java.io.File;
//import database.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.ConnectDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class billHviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton paid1;

    @FXML
    private ToggleGroup bill;

    @FXML
    private RadioButton unpaid1;

    @FXML
    private ComboBox<String> comboMob;

    @FXML
    private RadioButton paid2;

    @FXML
    private ToggleGroup bill1;

    @FXML
    private RadioButton unpaid2;

    @FXML
    private TableView<UserBean> table;

    @FXML
    private TextField txtBill;
    

	 PreparedStatement pstmt;
	 Connection con;
	 ResultSet ab; 
	 Float pr = 0f;

	 
	@FXML
    void doFetch1(ActionEvent event) {
		txtBill.setText("0");
    	
    	String musicFile = "lightning1.mp3";  

    	Media sound = new Media(new File(musicFile).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.play();
    	if(paid1.isSelected())
    	{
        	TableColumn<UserBean, String>bid = new TableColumn<UserBean, String>("Bill ID");
        	bid.setCellValueFactory(new PropertyValueFactory<>("billid"));
        	bid.setMinWidth(100);
        	
        	TableColumn<UserBean, String>mob = new TableColumn<UserBean, String>("Mobile");
        	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));
        	mob.setMinWidth(100);
        	
        	TableColumn<UserBean, String>nod = new TableColumn<UserBean, String>("No. of Days");
        	nod.setCellValueFactory(new PropertyValueFactory<>("nod"));
        	nod.setMinWidth(100);
        	
        	TableColumn<UserBean, String>dob = new TableColumn<UserBean, String>("Date of Billing");
        	dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        	dob.setMinWidth(100);
        	
        	TableColumn<UserBean, String>amount = new TableColumn<UserBean, String>("Amount");
        	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        	amount.setMinWidth(100);
        
        	
        	        	
        	table.getColumns().clear();
        	table.getColumns().addAll(bid,mob,nod,dob,amount);
        	ObservableList<UserBean>ary = paid1Records();
        	table.setItems(null);
        	table.setItems(ary);
			
    	}
    	
    	else if(unpaid1.isSelected())
    	{
        	TableColumn<UserBean, String>bid = new TableColumn<UserBean, String>("Bill ID");
        	bid.setCellValueFactory(new PropertyValueFactory<>("billid"));
        	bid.setMinWidth(100);
        	
        	TableColumn<UserBean, String>mob = new TableColumn<UserBean, String>("Mobile");
        	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));
        	mob.setMinWidth(100);
        	
        	TableColumn<UserBean, String>nod = new TableColumn<UserBean, String>("No. of Days");
        	nod.setCellValueFactory(new PropertyValueFactory<>("nod"));
        	nod.setMinWidth(100);
        	
        	TableColumn<UserBean, String>dob = new TableColumn<UserBean, String>("Date of Billing");
        	dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        	dob.setMinWidth(100);
        	
        	TableColumn<UserBean, String>amount = new TableColumn<UserBean, String>("Amount");
        	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        	amount.setMinWidth(100);
        	        	
        	table.getColumns().clear();
        	table.getColumns().addAll(bid,mob,nod,dob,amount);
        	ObservableList<UserBean>ary = unpaid1Records();
        	table.setItems(null);
        	table.setItems(ary);
    	}

    }

    @FXML
    void doFetch2(ActionEvent event) {
    	txtBill.setText("0");
    	
    	String musicFile = "lightning2.mp3";  

    	Media sound = new Media(new File(musicFile).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.play();
    	if(paid2.isSelected())
    	{
        	TableColumn<UserBean, String>bid = new TableColumn<UserBean, String>("Bill ID");
        	bid.setCellValueFactory(new PropertyValueFactory<>("billid"));
        	bid.setMinWidth(100);
        	
        	TableColumn<UserBean, String>mob = new TableColumn<UserBean, String>("Mobile");
        	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));
        	mob.setMinWidth(100);
        	
        	TableColumn<UserBean, String>nod = new TableColumn<UserBean, String>("No. of Days");
        	nod.setCellValueFactory(new PropertyValueFactory<>("nod"));
        	nod.setMinWidth(100);
        	
        	TableColumn<UserBean, String>dob = new TableColumn<UserBean, String>("Date of Billing");
        	dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        	dob.setMinWidth(100);
        	
        	TableColumn<UserBean, String>amount = new TableColumn<UserBean, String>("Amount");
        	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        	amount.setMinWidth(100);
        	        	
        	table.getColumns().clear();
        	table.getColumns().addAll(bid,mob,nod,dob,amount);
        	ObservableList<UserBean>ary = paid2Records();
        	table.setItems(null);
        	table.setItems(ary);
    	}
    	else if(unpaid2.isSelected())
    	{
        	TableColumn<UserBean, String>bid = new TableColumn<UserBean, String>("Bill ID");
        	bid.setCellValueFactory(new PropertyValueFactory<>("billid"));
        	bid.setMinWidth(100);
        	
        	TableColumn<UserBean, String>mob = new TableColumn<UserBean, String>("Mobile");
        	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));
        	mob.setMinWidth(100);
        	
        	TableColumn<UserBean, String>nod = new TableColumn<UserBean, String>("No. of Days");
        	nod.setCellValueFactory(new PropertyValueFactory<>("nod"));
        	nod.setMinWidth(100);
        	
        	TableColumn<UserBean, String>dob = new TableColumn<UserBean, String>("Date of Billing");
        	dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        	dob.setMinWidth(100);
        	
        	TableColumn<UserBean, String>amount = new TableColumn<UserBean, String>("Amount");
        	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        	amount.setMinWidth(100);
        	        	
        	table.getColumns().clear();
        	table.getColumns().addAll(bid,mob,nod,dob,amount);
        	ObservableList<UserBean>ary = unpaid2Records();
        	table.setItems(null);
        	table.setItems(ary);
    	}

    }
    
    ObservableList<UserBean> paid1Records()
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	pr=(float) 0;
    	try 
		{
			pstmt = con.prepareStatement("select * from billig where status=1");
			ab = pstmt.executeQuery();
			while(ab.next())
			{
				String billid = ab.getString("billid");
				String mob = ab.getString("custmob");
				String nod = ab.getString("noofdays");
				String dob = ab.getString("dateofbill");
				String amounts = ab.getString("amount");
				UserBean ref = new UserBean(billid,mob,nod,dob,amounts);
				ary.add(ref);
	        	pr=pr+ Float.parseFloat(amounts);
	        	txtBill.setText(String.valueOf(pr));

			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
    	return ary;
    }

    ObservableList<UserBean> unpaid1Records()
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	pr=(float) 0;
    	try 
		{
			pstmt = con.prepareStatement("select * from billig where status=0");
			ab = pstmt.executeQuery();
			while(ab.next())
			{
				String billid = ab.getString("billid");
				String mob = ab.getString("custmob");
				String nod = ab.getString("noofdays");
				String dob = ab.getString("dateofbill");
				String amount = ab.getString("amount");
				UserBean ref = new UserBean(billid,mob,nod,dob,amount);
				ary.add(ref);
				pr=pr+ Float.parseFloat(amount);
	        	txtBill.setText(String.valueOf(pr));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
    	return ary;
    }
    
    ObservableList<UserBean> paid2Records()
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	pr=(float) 0;
    	try 
		{
			pstmt = con.prepareStatement("select * from billig where status=1 and custmob=?");
			pstmt.setString(1, comboMob.getEditor().getText());
			ab = pstmt.executeQuery();
			while(ab.next())
			{
				String billid = ab.getString("billid");
				String mob = ab.getString("custmob");
				String nod = ab.getString("noofdays");
				String dob = ab.getString("dateofbill");
				String amount = ab.getString("amount");
				UserBean ref = new UserBean(billid,mob,nod,dob,amount);
				ary.add(ref);
				pr=pr+ Float.parseFloat(amount);
	        	txtBill.setText(String.valueOf(pr));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
    	return ary;
    }
    
    ObservableList<UserBean> unpaid2Records()
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	pr=(float) 0;
    	try 
		{
			pstmt = con.prepareStatement("select * from billig where status=0 and custmob=?");
			pstmt.setString(1, comboMob.getEditor().getText());
			ab = pstmt.executeQuery();
			while(ab.next())
			{
				String billid = ab.getString("billid");
				String mob = ab.getString("custmob");
				String nod = ab.getString("noofdays");
				String dob = ab.getString("dateofbill");
				String amount = ab.getString("amount");
				UserBean ref = new UserBean(billid,mob,nod,dob,amount);
				ary.add(ref);
				pr=pr+ Float.parseFloat(amount);
	        	txtBill.setText(String.valueOf(pr));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
    	return ary;
    }

    @FXML
    void initialize() {
    	con = ConnectDatabase.getConnection();
    	try 
    	{
			pstmt = con.prepareStatement("select * from billig");
			ab = pstmt.executeQuery();
			while(ab.next())
			{
				String mob = ab.getString("custmob");
				comboMob.getItems().addAll(mob);
			}
			
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}

    }
}
