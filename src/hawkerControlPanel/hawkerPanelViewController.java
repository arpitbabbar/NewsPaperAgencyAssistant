package hawkerControlPanel;

import database.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class hawkerPanelViewController {
	
	 PreparedStatement pstmt;
	 Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboName;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtMob;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtAddress;

    @FXML
    private ImageView picAadhar;

    @FXML
    private Button btnPic;

    @FXML
    private ListView<String> listViewAreas;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnRemove;

    @FXML
    private TextField txtArea;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Label lblResult;

    List<String> lst;
    String path="";
   String afterpic="";
    String liarea="";
    
    @FXML
    void doAddArea(ActionEvent event) {
    	String ar = txtArea.getText();
    	ArrayList<String>larea = new ArrayList<String>();
    	larea.add(ar);
    	listViewAreas.getItems().add(ar);
//    	lblResult.setText(liarea);
    	
    	
    }

    @FXML
    void doChoosePic(ActionEvent event) 
    {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Images",lst));
    	File img = fc.showOpenDialog(null);
    	Image pic = null;
		try {
			pic = new Image(new FileInputStream(img));
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		path = img.getAbsolutePath();
		if(img!= null)
    	{
    		picAadhar.setImage(pic);
    		
    		
    	}	
    }

    @FXML
    void doNew(ActionEvent event) 
    {
    	comboName.getEditor().setText("");
    	txtAddress.setText("");
    	txtArea.setText("");
    	txtMob.setText("");
    	txtSalary.setText("");
    	
		listViewAreas.getItems().clear();
    	lblResult.setText("");
    	String imgH = "D:\\EclipseJavaPro\\NewsPaperAgencyAssistant\\src\\hawkerControlPanel\\29_11_2019-aadhar_19800047.jpg";
    	try {
			Image picc = new Image(new FileInputStream(imgH));
			picAadhar.setImage(picc);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    void doRemove(ActionEvent event) 
    {
    	try
    	{
			pstmt=con.prepareStatement("delete from hawkers where name=?");
			pstmt.setString(1, comboName.getEditor().getText());
			
			int count = pstmt.executeUpdate();
			lblResult.setText(count + " Records Deleted");
			
		}
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    }

    @FXML
    void doSave(ActionEvent event) 
    {
    	ObservableList<String>selArea = listViewAreas.getSelectionModel().getSelectedItems();
    	for(String s : selArea)
    	{
    		if(s=="")
    			continue;
    		liarea=liarea+s+",";
    	}
    	
    	try 
    	{
			pstmt = con.prepareStatement("insert into hawkers(name,mobile,address,areas,aadharpic,salary) values(?,?,?,?,?,?)");
			pstmt.setString(1, comboName.getEditor().getText());
			pstmt.setString(2, txtMob.getText());
			pstmt.setString(3, txtAddress.getText());
			pstmt.setString(4, liarea);
			pstmt.setString(5, path);
			pstmt.setInt(6, Integer.parseInt(txtSalary.getText()));
			
			int count = pstmt.executeUpdate();
			lblResult.setText(count + " Records Saved");
			
			
		} 
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    }

    @FXML
    void doSearch(ActionEvent event) 
    {
    	String name = comboName.getEditor().getText();
    	listViewAreas.getItems().clear();
    	
    	try 
    	{
			pstmt = con.prepareStatement("select * from hawkers where name=?");
			pstmt.setString(1, name);
			ResultSet table = pstmt.executeQuery();
			
			if(table.next())
			{
				String mob = table.getString("mobile");
				String address = table.getString("address");
				String areas = table.getString("areas");
				String apic = table.getString("aadharpic");
				int sal = table.getInt("salary");
				
				txtMob.setText(mob);
				txtAddress.setText(address);
				txtSalary.setText(String.valueOf(sal));
				path=apic;
				
				String []a = areas.split(",");
				ArrayList<String>areal = new ArrayList<String>(Arrays.asList(a));
				for(String la: areal)
				{
					listViewAreas.getItems().add(la);
				}
				
				try 
				{
					Image picc = new Image(new FileInputStream(apic));
					picAadhar.setImage(picc);
				} 
				catch (FileNotFoundException e) 
				{
					
					e.printStackTrace();
				}
				
			}
			else
				lblResult.setText("Hawker Not Found");
			
		} 
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    }

    @FXML
    void doUpdate(ActionEvent event)
    {
    	ObservableList<String>selArea = listViewAreas.getSelectionModel().getSelectedItems();
    	for(String s : selArea)
    	{
    		liarea=liarea+s+",";
    	}
    	
    	try 
    	{
			pstmt = con.prepareStatement("update hawkers set mobile=?, address=?, areas=?, aadharpic=?, salary=? where name=?");
			pstmt.setString(6, comboName.getEditor().getText());
			pstmt.setString(1, txtMob.getText());
			pstmt.setString(2, txtAddress.getText());
			pstmt.setString(3, liarea);
			pstmt.setString(4, path);
			pstmt.setInt(5, Integer.parseInt(txtSalary.getText()));
			
			int count = pstmt.executeUpdate();
			lblResult.setText(count + " Records Updates");
			
    	}
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    }

    @FXML
    void initialize()
    {
    	con = ConnectDatabase.getConnection();
    	lst = new ArrayList<>();
    	lst.add("*.jpg");
    	lst.add("*.jpeg");
    	lst.add("*.png");
    	listViewAreas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ResultSet hawkername = null;
		try 
		{
			pstmt = con.prepareStatement("select * from hawkers");
			hawkername = pstmt.executeQuery();
		} 
		catch (SQLException e1) 
		{
			
			e1.printStackTrace();
		}

        try 
        {
			while (hawkername.next()) 
			{   
			    comboName.getItems().addAll(hawkername.getString("name")); 
			}
		} 
        catch (SQLException e)
        {
			
			e.printStackTrace();
		}
    	

    }
}
