package com.virus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DatabaseConnection;

public class Adminservlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		String userid=req.getParameter("userid");
		String password=req.getParameter("password");
		String admin=req.getParameter("admin");
		
		adduser(userid,password,admin);
	}
	
	   public void adduser(String userid,
				 String password,String admin) 
		
		
		{
			
			  try {
				  
					String query="INSERT INTO vuser(userid,PASS,adminuser )"+
							"Values	('"+userid+ "','"+password+"','"+admin+"')";
					
					 DatabaseConnection.getInstance();
					Connection conn = DatabaseConnection.getConnection();
					 Statement s1=conn.createStatement();
					
					int rowcount=s1.executeUpdate(query);
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
	
		
		}
		
	public void doGet(HttpServletRequest req,HttpServletResponse res ) throws IOException, ServletException {
		req.getRequestDispatcher("/adduser.html").forward(req, res);
		
	}

}
