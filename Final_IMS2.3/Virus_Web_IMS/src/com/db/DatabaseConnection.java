package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

   private static DatabaseConnection instance;
   private static Connection connection;
   private String url = "jdbc:sqlserver://projectz.database.windows.net:1433;database=moorthy16695;user=moorthy16695@projectz;password=12.Moorthy;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
   private String databaseDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";


   private DatabaseConnection() throws SQLException {
       try {
           Class.forName(databaseDriver);
           this.connection = DriverManager.getConnection(url);
       } catch (ClassNotFoundException ex) {
           System.out.println("Database Connection Creation Failed : " + ex.getMessage());
       }
   }

   //method to get database connection
   public static Connection getConnection() {
       return connection;
   }

   //static method to create instance of Singleton class
   public static DatabaseConnection getInstance() throws SQLException {
       if (instance == null) {
           instance = new DatabaseConnection();
       } else if (instance.getConnection().isClosed()) {
           instance = new DatabaseConnection();
       }
       return instance;
   }

   //static method to close connection
   public static void closeConnection() throws SQLException {
       try {
           connection.close();
       }catch (Exception e)
       {
           System.out.println("Close connection failed  : " + e.getMessage());
       }
   }
   public static void main(String[] args) throws SQLException {
	   //For Connection use this
	   DatabaseConnection.getInstance();
	Connection conn = DatabaseConnection.getConnection();
System.out.println("Here");
	String sql = "SELECT * FROM Virus";
	Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       String first = rs.getString("Model");
       
       
      
       System.out.print(", First: " + first);
    }
//    String query="INSERT INTO virus(Product_ID,Product_Name,Model)"+
//			"Values	('"+"124"+ "','"+"123"+"','"+"M"+"')";
//   
//	
//	
//	 Statement s1=conn.createStatement();
//	
//	int rowcount=s1.executeUpdate(query);
    String query="Select * from virus where product_id ='A2'";
	 DatabaseConnection.getInstance();
	 Statement s1=conn.createStatement();
	
	
	ResultSet rsl=s1.executeQuery(query);
	  while(rsl.next()){
	       //Retrieve by column name
	       String first = rsl.getString("Model");
	       
	       
	      
	       System.out.println( first);
	    }
    rs.close();
	
}
}
