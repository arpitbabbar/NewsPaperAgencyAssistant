package login;

import database.*;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class loginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtId;

    @FXML
    private PasswordField txtPwd;
    
    @FXML
    private Label lblresult;
    
//    @FXML
//    void doChange(KeyEvent event) {
//    	String p = txtPwd.getText();
////    	int c=0;
////    	int l = p.length();
////    	while()
////    	{
////    		
////    		for(int i=0;i<=c;i++)
////    		{
////    			txtPwd.setText("*");
////    		}
////    		c++;
////    	}
//    	txtPwd.setText("");
//    	txtPwd.setText("*");
//    }

    
    Connection con;
    PreparedStatement pstmt;
    String uidd=null;
    String pass= null;

    @FXML
    void doExit(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void doLogin(ActionEvent event) {
    		doCheck();
    }
    void doCheck()
    {
    	String uid = txtId.getText();
    	String pwd = txtPwd.getText();
    	System.out.println(uid + "       " + pwd);
    	try 
    	{
			pstmt = con.prepareStatement("select * from login where stat=1 and uid=? and pwd=?");
			pstmt.setString(1, uid);
			pstmt.setString(2, pwd);
			ResultSet table = pstmt.executeQuery();
			if(table.next())
			{
			    	try
			    	{
			    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("showAllforms/allView.fxml")); 
						Scene scene = new Scene(root);
						Stage stage=new Stage();
						stage.setScene(scene);
						stage.show();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
			}
			else
				lblresult.setText("Invalid UserID or Password");
    	}
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}

    }
    

    @FXML
    void initialize() {
    	con = ConnectDatabase.getConnection();
    }
}
