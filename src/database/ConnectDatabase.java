package database;


import java.sql.*;

public class ConnectDatabase {
	
	public static Connection getConnection()
	{
		Connection con =null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/newspaperassistant", "root" ,"");
			/*System.out.println("Connected..........");*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
	/*public static void main(String args[])
	{
		Connection conRef=getConnection();
		if(conRef==null)
			System.out.println("error");
		else
			System.out.println("Connected...");
		
	}*/
	
}
