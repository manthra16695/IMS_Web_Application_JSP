package com.virus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DatabaseConnection;

public class RemoveServlet extends HttpServlet{
	
	

	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		
		String code=req.getParameter("value");
		String field=req.getParameter("type");
		System.out.println(field);
		
		
		removerecord(field,code);
		
		try {
			req.getRequestDispatcher("RemovePage.jsp?added=1").forward(req, res);
		}
		catch(ServletException e)
		{
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removerecord(String field,String Product_Code) 
	
	
	{
		
		  try {
			  
			  String query="DELETE FROM virus WHERE " +field+ " ='"+Product_Code+"'";
				 DatabaseConnection.getInstance();
				Connection conn = DatabaseConnection.getConnection();
				 Statement s1=conn.createStatement();
				
				int rowcount=s1.executeUpdate(query);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}

	
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res ) throws IOException, ServletException {
		req.getRequestDispatcher("/RemovePage.jsp").forward(req, res);
		
	}

	
}
