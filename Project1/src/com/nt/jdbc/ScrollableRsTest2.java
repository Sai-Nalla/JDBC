package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollableRsTest2 {
	
	private static final String GET_SQL_QUERY="SELECT SID,SNAME,ADDRS,AVG FROM STUDENT";
	public static void main(String[] args) {
		int count=0;
		//Java 8 new Features
		try(
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				PreparedStatement ps=con.prepareCall(GET_SQL_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=ps.executeQuery();	
				){
					System.out.println("ScrollableRs is created");
			while(rs.next()){
				rs.refreshRow();
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}
			//Insert new record to table
			rs.absolute(6);
			rs.moveToInsertRow();
			rs.updateInt(1, 8);
			rs.updateString(2, "Anvesh");
			rs.updateString(3, "Vijag");
			rs.updateFloat(4,99.8f);
			rs.insertRow();
			//update the record values in table
			rs.absolute(3);
			rs.updateString(3, "Banglore");
			rs.updateRow();
			//delete the record in table 
			rs.absolute(4);
			rs.deleteRow();
	
			}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
