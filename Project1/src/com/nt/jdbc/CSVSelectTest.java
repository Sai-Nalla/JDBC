package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVSelectTest {
	private static final String CSV_QUEYRY="SELECT * FROM FILE1.CSV";

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:Text:///E:/workspace/AdvJava")){
			try(PreparedStatement ps=con.prepareStatement(CSV_QUEYRY)){
				try(ResultSet rs=ps.executeQuery()){
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
						}
					}
				}//try3
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
