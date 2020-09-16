package com.virus;

import java.util.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DatabaseConnection;

public class SearchServlet extends HttpServlet {
	
	public void doPost (HttpServletRequest req,HttpServletResponse res ) {
		
		String value=req.getParameter("value");
		String field=req.getParameter("type");
		
		try {
			ResultSet searchresult = search(value,field);
			 List alist = new ArrayList<String>();
			 alist= resultSetToArrayList(searchresult);
			try {
				req.setAttribute("list", alist);
				req.getRequestDispatcher("/View.jsp").forward(req, res);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//Calling view 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public ResultSet search(String value, String field) throws SQLException {
		
		
			
		
		String query="Select * from virus where "+field+" ='"+value+"'";
		System.out.println(query);
		 DatabaseConnection.getInstance();
		Connection conn = DatabaseConnection.getConnection();
		 Statement s1=conn.createStatement();
		
		ResultSet rsl=s1.executeQuery(query);
		return rsl;
		
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res ) throws IOException, ServletException {
		req.getRequestDispatcher("/Search.jsp").forward(req, res);
		
	}
	
	public List resultSetToArrayList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    List<Map<String, Object>> list = new ArrayList<>();
	    while (rs.next()) {
	        Map<String, Object> row = new LinkedHashMap<>(columns);
	        for (int i = 1; i <= columns; ++i) {
	            row.put(md.getColumnName(i), rs.getObject(i));
	        }
	        list.add(row);
	    }

	    return list;
	}
}
