package customerManager;

import database.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class custViewController {

	 PreparedStatement pstmt;
	 Connection con;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCustName;

    @FXML
    private ComboBox<String> comboMob;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtAddress;

    @FXML
    private ListView<String> lstPaper;

    @FXML
    private ListView<String> lstPrice;

    @FXML
    private ComboBox<String> comboArea;

    @FXML
    private TextField txtHawkerName;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnStore;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private ImageView imgSearch;
    	
    	
    	ResultSet customer;
    	ResultSet papername;
    	ResultSet areas;
    	ObservableList<String>selPaper;
    	ObservableList<Integer>selIndx;
    	String lipaper="";
    	String liprice="";
    	
    @FXML
    void doSearchHawker(MouseEvent event) {
    	String name = comboArea.getSelectionModel().getSelectedItem();
    	try 
    	{
			pstmt = con.prepareStatement("select name from hawkers where areas like ?");
			pstmt.setString(1, "%" + name + "%");
			ResultSet hwker = pstmt.executeQuery();
			while(hwker.next())
			{
				String hwkname = hwker.getString("name");
				txtHawkerName.setText(hwkname);
			}
			
		}
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
    
    @FXML
    void doClear(ActionEvent event) {
//    	selPaper.clear();
//    	selIndx.clear();
    	lipaper="";
    	liprice="";
    	txtAddress.setText("");
    	txtCustName.setText("");
    	txtHawkerName.setText("");
    	comboMob.getEditor().clear();
    	comboArea.getSelectionModel().clearSelection();
    	lstPaper.getSelectionModel().clearSelection();
    	lstPrice.getSelectionModel().clearSelection();
//    	try 
//    	{
//			pstmt = con.prepareStatement("select * from papers");
//			papername = pstmt.executeQuery();
//			
//			while(papername.next())
//			{
//				lstPaper.getItems().addAll(papername.getString("paper"));
//				lstPrice.getItems().addAll(papername.getString("price"));
//			}
//			
//		}
//    	catch (SQLException e) 
//    	{
//			e.printStackTrace();
//		}
    	
    }

    @FXML
    void doDelete(ActionEvent event) {
    	try
    	{
			pstmt=con.prepareStatement("delete from customers where mobile=?");
			pstmt.setString(1, comboMob.getEditor().getText());
			
			pstmt.executeUpdate();
			
			
		}
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    }

    @FXML
    void doSearch(ActionEvent event) {
    	String mob = comboMob.getEditor().getText();
    	lstPaper.getSelectionModel().clearSelection();
    	lstPrice.getSelectionModel().clearSelection();
    	try 
    	{
			pstmt = con.prepareStatement("select * from customers where mobile=?");
			pstmt.setString(1, mob);
			ResultSet table = pstmt.executeQuery();
			while(table.next())
			{
				String name = table.getString("name");
				String address = table.getString("address");
				String hwkname = table.getString("hawker");
				String paper = table.getString("paper");
				String price = table.getString("prices");
				String area = table.getString("areas");
				txtCustName.setText(name);
				txtAddress.setText(address);
				txtHawkerName.setText(hwkname);
				comboArea.getSelectionModel().select(area);
				String []a = paper.split(",");
				ArrayList<String>paperl = new ArrayList<String>(Arrays.asList(a));
				for(String lp: paperl)
				{
					lstPaper.getSelectionModel().select(lp);
				}
				String []b = price.split(",");
				ArrayList<String>pricel = new ArrayList<String>(Arrays.asList(b));
				for(String lpr: pricel)
				{
					lstPrice.getSelectionModel().select(lpr);
				}
			}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}

    }

    @FXML
    void doStoreInTable(ActionEvent event) {
    	selPaper = lstPaper.getSelectionModel().getSelectedItems();
    	selIndx=lstPaper.getSelectionModel().getSelectedIndices();
    	
    	for(String s : selPaper)
    	{
    		lipaper=lipaper+s+",";
    	}
    	  for (int indx : selIndx)
    	  {
    		  String selPrice = lstPrice.getItems().get(indx);
    		  liprice=liprice+selPrice+",";
    	  }
    	try 
    	{
			pstmt = con.prepareStatement("insert into customers(name,mobile,address,paper,prices,hawker,areas) values(?,?,?,?,?,?,?)");
			pstmt.setString(1, txtCustName.getText());
			pstmt.setString(2, comboMob.getEditor().getText());
			pstmt.setString(3, txtAddress.getText());
			pstmt.setString(4, lipaper);
			pstmt.setString(5, liprice);
			pstmt.setString(6, txtHawkerName.getText());
			pstmt.setString(7, comboArea.getSelectionModel().getSelectedItem());
			
			pstmt.executeUpdate();
			
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	lipaper="";
    	liprice="";

    }

    @FXML
    void doUpdate(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	
    	con = ConnectDatabase.getConnection();
    	lstPaper.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	lstPrice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	try 
    	{
			pstmt = con.prepareStatement("select * from papers");
			papername = pstmt.executeQuery();
			
			while(papername.next())
			{
				lstPaper.getItems().addAll(papername.getString("paper"));
				lstPrice.getItems().addAll(papername.getString("price"));
			}
			
		}
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	try 
    	{
			pstmt = con.prepareStatement("select distinct areas from hawkers");
			areas = pstmt.executeQuery();
			
			while(areas.next())
			{
				String ar = areas.getString("areas");
				String []a =ar.split(",");
				ArrayList<String>areal = new ArrayList<String>(Arrays.asList(a));
				for(String la: areal)
				{
					comboArea.getItems().addAll(la);
				}
			}
			
			
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	try 
    	{
			pstmt=con.prepareStatement("select * from customers");
			customer = pstmt.executeQuery();
			while(customer.next())
			{
				comboMob.getItems().addAll(customer.getString("mobile"));
			}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}

    }
}
