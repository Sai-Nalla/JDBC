package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class AgeCalculatorTest {

	private static final String DATE_RETRIEVE_QUERY="SELECT (SYSDATE-DOB)/365.25 FROM PERSON_INFO WHERE PID=?";

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		ResultSet rs=null;
		int id=0;
		long sysDate=0l,udobMs=0l;
		java.util.Date udob=null;
		float age=0.0f;
		
		try {
			//load the deiver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			sc=new Scanner(System.in);
			System.out.println("enter the person id");
			id=sc.nextInt();
			//get ConnectionObj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get precompiled sql query
			if(con!=null) {
				ps=con.prepareStatement(DATE_RETRIEVE_QUERY);
				//set value to query param
				ps.setInt(1,id);
			}
				//execute the query
				if(ps!=null) {
					rs=ps.executeQuery();
				}
				//get result set obj values
				if(rs.next()) {
					age=rs.getFloat(1);
					System.out.println("age is"+age);
				}
				else
					System.out.println("Record not found");
			}
			
			catch(Exception e) {
				e.printStackTrace();
		}
			finally{
				try{
					if(ps!=null)
						ps.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}
				try{
					if(con!=null)
						con.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}
				try{
					if(rs!=null)
						rs.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}
				try{
					if(sc!=null)
						sc.close();
					}
					catch(Exception se){
						se.printStackTrace();
					}
				
	}//finally
	}

}
