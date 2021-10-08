package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class AgeCalculatorTest_JavaCode {

	private static final String DATE_RETRIEVE_QUERY="SELECT Dob FROM PERSON_INFO WHERE PID=?";

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		ResultSet rs=null;
		int id=0;
		long sysDate=0l,udobMs=0l;
		java.util.Date udob=null;
		
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
					sysDate=new Date().getTime();
					udob=rs.getDate(1);
					udobMs=udob.getTime();
					System.out.println("personAge is::"+((sysDate-udobMs))/(1000*60*60*24*365.25f));
				}
				else
					System.out.println("record not found");
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
