package CustomerDisplayBoard;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



import database.ConnectDatabase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class CdisplayViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboArea;

    @FXML
    private Button btnArea;

    @FXML
    private ComboBox<String> comboPaper;

    @FXML
    private Button btnPaper;

    @FXML
    private Button btnAll;
    @FXML
    private Button btnExport;

    @FXML
    private TableView<UserBean> tableRecords;
    
     PreparedStatement pstmt;
	 Connection con;

    @SuppressWarnings({ "unchecked"})
	@FXML
    void doAreaFetch(ActionEvent event) {
    	
    	//String musicFile = "laugh.wav";     // For example

//    	Media sound = new Media(new File(musicFile).toURI().toString());
//    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
//    	mediaPlayer.play();
    	
    	
    	TableColumn<UserBean, String>name = new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<UserBean, String>mob = new TableColumn<UserBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mob.setMinWidth(100);
    	
    	TableColumn<UserBean, String>address = new TableColumn<UserBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(100);
    	
    	TableColumn<UserBean, String>paper = new TableColumn<UserBean, String>("Paper");
    	paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
    	paper.setMinWidth(100);
    	
    	TableColumn<UserBean, String>hwkr = new TableColumn<UserBean, String>("Hawker Name");
    	hwkr.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hwkr.setMinWidth(100);
    	
    	TableColumn<UserBean, String>dof = new TableColumn<UserBean, String>("Date Of Join");
    	dof.setCellValueFactory(new PropertyValueFactory<>("dof"));
    	dof.setMinWidth(100);
    	
    	tableRecords.getColumns().clear();
    	tableRecords.getColumns().addAll(name,mob,address,paper,hwkr,dof);
    	ObservableList<UserBean>ary = areaRecords();
    	tableRecords.setItems(null);
    	tableRecords.setItems(ary);
    }

    @FXML
    void doPaperFetch(ActionEvent event) {
    	
//    	String musicFile = "dildanimaada.mpeg";     // For example
//
//    	Media sound = new Media(new File(musicFile).toURI().toString());
//    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
//    	mediaPlayer.play();
    	
    	TableColumn<UserBean, String>name = new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<UserBean, String>mob = new TableColumn<UserBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mob.setMinWidth(100);
    	
    	TableColumn<UserBean, String>address = new TableColumn<UserBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(100);
    	
    	TableColumn<UserBean, String>hwkr = new TableColumn<UserBean, String>("Hawker Name");
    	hwkr.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hwkr.setMinWidth(100);
    	
    	TableColumn<UserBean, String>dof = new TableColumn<UserBean, String>("Date Of Join");
    	dof.setCellValueFactory(new PropertyValueFactory<>("dof"));
    	dof.setMinWidth(100);
    	
    	tableRecords.getColumns().clear();
    	tableRecords.getColumns().addAll(name,mob,address,hwkr,dof);
    	ObservableList<UserBean>ary = paperRecords();
    	tableRecords.setItems(null);
    	tableRecords.setItems(ary);

    }

    @SuppressWarnings("unchecked")
	@FXML
    void doShowAll(ActionEvent event) {
    	TableColumn<UserBean, String>name = new TableColumn<UserBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<UserBean, String>mob = new TableColumn<UserBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mob.setMinWidth(100);
    	
    	TableColumn<UserBean, String>address = new TableColumn<UserBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(100);
    	
    	TableColumn<UserBean, String>paper = new TableColumn<UserBean, String>("Paper");
    	paper.setCellValueFactory(new PropertyValueFactory<>("paper"));
    	paper.setMinWidth(100);
    	
    	TableColumn<UserBean, String>hwkr = new TableColumn<UserBean, String>("Hawker Name");
    	hwkr.setCellValueFactory(new PropertyValueFactory<>("hawker"));
    	hwkr.setMinWidth(100);
    	
    	TableColumn<UserBean, String>dof = new TableColumn<UserBean, String>("Date Of Join");
    	dof.setCellValueFactory(new PropertyValueFactory<>("dof"));
    	dof.setMinWidth(100);
    	
    	tableRecords.getColumns().clear();
    	tableRecords.getColumns().addAll(name,mob,address,paper,hwkr,dof);
    	ObservableList<UserBean>ary = allRecords();
    	tableRecords.setItems(null);
    	tableRecords.setItems(ary);

    }
    
    ObservableList<UserBean> areaRecords()
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	try 
    	{
			pstmt=con.prepareStatement("select * from customers where areas=?");
			pstmt.setString(1, comboArea.getSelectionModel().getSelectedItem());
			ResultSet area = pstmt.executeQuery();
			while(area.next())
			{
				String name = area.getString("name");
				String mob = area.getString("mobile");
				String address = area.getString("address");
				String paper = area.getString("paper");
				String hwkr = area.getString("hawker");
				String dof = area.getString("dateofjoin");
				UserBean ref = new UserBean(name,mob,address,paper,hwkr,dof);
				ary.add(ref);
			}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	return ary;
    }
    
    ObservableList<UserBean> paperRecords()
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	String paper = comboPaper.getSelectionModel().getSelectedItem();
    	try 
    	{
			pstmt=con.prepareStatement("select * from customers where paper like ?");
			pstmt.setString(1, "%" + paper + "%");
			ResultSet area = pstmt.executeQuery();
			while(area.next())
			{
				String name = area.getString("name");
				String mob = area.getString("mobile");
				String address = area.getString("address");
				
				String hwkr = area.getString("hawker");
				String dof = area.getString("dateofjoin");
				UserBean ref = new UserBean(name,mob,address,paper,hwkr,dof);
				ary.add(ref);
			}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	return ary;
    }
    
    ObservableList<UserBean> allRecords()
    {
    	ObservableList<UserBean> ary=FXCollections.observableArrayList();
    	try 
    	{
			pstmt=con.prepareStatement("select * from customers");
			ResultSet area = pstmt.executeQuery();
			while(area.next())
			{
				String name = area.getString("name");
				String mob = area.getString("mobile");
				String address = area.getString("address");
				String paper = area.getString("paper");
				String hwkr = area.getString("hawker");
				String dof = area.getString("dateofjoin");
				UserBean ref = new UserBean(name,mob,address,paper,hwkr,dof);
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
    void initialize() 
    {
    	con = ConnectDatabase.getConnection();
    	try 
    	{
			pstmt=con.prepareStatement("select distinct areas from customers");
			ResultSet areas = pstmt.executeQuery();
			while(areas.next())
			{
				String area = areas.getString("areas");
				comboArea.getItems().add(area);
			}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	try 
    	{
			pstmt=con.prepareStatement("select distinct paper from papers");
			ResultSet papers = pstmt.executeQuery();
			while(papers.next())
			{
				String paper = papers.getString("paper");
				comboPaper.getItems().add(paper);
			}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}

    }
}
