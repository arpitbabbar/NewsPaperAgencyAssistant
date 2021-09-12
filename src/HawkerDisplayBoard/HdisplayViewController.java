package HawkerDisplayBoard;

import database.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HdisplayViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnHawker;

    @FXML
    private TableView<UserBean> table;
    
    PreparedStatement pstmt;
	 Connection con;


    @SuppressWarnings("unchecked")
	@FXML
    void doShowHawker(ActionEvent event) {
    	TableColumn<UserBean, String>name = new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<UserBean, String>mob = new TableColumn<UserBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mob.setMinWidth(100);
    	
    	TableColumn<UserBean, String>address = new TableColumn<UserBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(100);
    	
    	TableColumn<UserBean, String>areas = new TableColumn<UserBean, String>("Areas");
    	areas.setCellValueFactory(new PropertyValueFactory<>("areas"));
    	areas.setMinWidth(100);
    	
    	TableColumn<UserBean, String>sal = new TableColumn<UserBean, String>("Salary");
    	sal.setCellValueFactory(new PropertyValueFactory<>("salary"));
    	sal.setMinWidth(100);
    	
    	TableColumn<UserBean, String>doj = new TableColumn<UserBean, String>("Date Of Join");
    	doj.setCellValueFactory(new PropertyValueFactory<>("doj"));
    	doj.setMinWidth(100);
    	
    	table.getColumns().clear();
    	table.getColumns().addAll(name,mob,address,areas,sal,doj);
    	ObservableList<UserBean>ary = allRecords();
    	table.setItems(null);
    	table.setItems(ary);
    }

    ObservableList<UserBean> allRecords()
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	try 
    	{
			pstmt=con.prepareStatement("select * from hawkers");
			ResultSet area = pstmt.executeQuery();
			while(area.next())
			{
				String name = area.getString("name");
				String mob = area.getString("mobile");
				String address = area.getString("address");
				String areas = area.getString("areas");
				String salary = area.getString("salary");
				String doj = area.getString("doj");
				UserBean ref = new UserBean(name,mob,address,areas,salary,doj);
				ary.add(ref);
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
    }
}
