package com.virus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.db.DatabaseConnection;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res ) throws IOException, ServletException {
		String a = null,b = null;
		try {
		 a=req.getParameter("username");
		 b=req.getParameter("password");
		 
		int  cnt=0;
		
		 
		 cnt=checkuser(a, b);
		 
		 if (cnt==1){
	        	
	        	
		    	req.getRequestDispatcher("/AccessPage.html").forward(req, res);
	        	
	        	        } 
	        
	        else {
	        	
	        	req.getRequestDispatcher("/index.html").forward(req, res);
	        	
	        	System.out.println("Invalid Username or Password");
	        
	        }
		 
		 
		 
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
	   
	}
	
	 public int checkuser(String a,String b) 
	
	
	{
		 int rowcount=0;
		 
			  try {
				  
			  
				String query="Select count(*) from vuser where userid='"+a+"'";
				
				
				 DatabaseConnection.getInstance();
				Connection conn = DatabaseConnection.getConnection();
				 Statement s1=conn.createStatement();
				
				 ResultSet rs=s1.executeQuery(query);
				 while(rs.next()) {
//					 System.out.println();
				 rowcount=rs.getInt(1);
				 }
				System.out.println(rowcount);
				}
			
				catch(Exception e) {
					
				
				System.out.println(e.getMessage());
				}
			
		
			  
			return rowcount;
	
	}
	
	
	public void doGet(HttpServletRequest req,HttpServletResponse res ) throws IOException, ServletException {
		req.getRequestDispatcher("/index.html").forward(req, res);
		
	}
	
	
}
