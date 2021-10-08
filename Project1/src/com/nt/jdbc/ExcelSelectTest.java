package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExcelSelectTest {
	private static final String EXCELL_QUEYRY="SELECT * FROM COLLEGE.SHEET1";

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:Excel:///E:/workspace/AdvJava")){
			try(PreparedStatement ps=con.prepareStatement(EXCELL_QUEYRY)){
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
