package com.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		
		String prdcode=req.getParameter("prcode");
		String prname=req.getParameter("prname");
		String Model=req.getParameter("model");
		String Manufacturer=req.getParameter("manufacturer");
		String Type=req.getParameter("type");
		String Location=req.getParameter("location");
		String MSRP=req.getParameter("msrp");
		String Unitprice=req.getParameter("unitprice");
		String Discount=req.getParameter("discount");
		String Quantity=req.getParameter("quantity");
				
		addtable(prdcode,prname,Model,Manufacturer,Type,Location,MSRP,Unitprice,Discount,Quantity);
		  try {

			req.getRequestDispatcher("Add_Screen.jsp?added=1").forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	   
	   public void addtable(String Product_Id,
				 String Product_Name,String Model,String Manufacturer,
				 String Type_Code,String Location_Code,String MSRP,String Unit_Cost,String Discount_Price,
				 String Stock_Quantity) 
		
		
		{
			System.out.println(Product_Name);
			  try {
				  
					String query="INSERT INTO virus(Product_ID,Product_Name,Model,Manufacturer,Type_Code,Location_Code,MSRP,Unit_Cost,Discount_Price,Stock_Quantity )"+
							"Values	('"+Product_Id+ "','"+Product_Name+"','"+Model+"',' "+Manufacturer+"','"+Type_Code+"','"+Location_Code+"','"+MSRP+"','"+Unit_Cost+"','"+Discount_Price+"','"+Stock_Quantity+"')";
					
					 DatabaseConnection.getInstance();
					Connection conn = DatabaseConnection.getConnection();
					 Statement s1=conn.createStatement();
					
					int rowcount=s1.executeUpdate(query);
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
	
		
		}
		public void doGet(HttpServletRequest req,HttpServletResponse res ) throws IOException, ServletException {
			req.getRequestDispatcher("/Add_Screen.jsp").forward(req, res);
			
		}
	
	
}
