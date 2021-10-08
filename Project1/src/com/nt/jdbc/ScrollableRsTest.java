package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollableRsTest {
	
	private static final String GET_SQL_QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		
		//Java 8 new Features
		try(
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				PreparedStatement ps=con.prepareCall(GET_SQL_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs=ps.executeQuery();	
				){
		//perform the operations
			while(rs.next()){
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}
			System.out.println(rs.getRow());
			rs.first();
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		rs.last();
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		System.out.println(rs.isFirst());
		System.out.println(rs.isLast());
		rs.beforeFirst();
		System.out.println(rs.getRow());
		rs.afterLast();
		System.out.println(rs.getRow());
		rs.absolute(3);
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		rs.absolute(-2);
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		rs.relative(1);
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			
			
			}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
