package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateConversionTest {
	private static final String INSERT_DATE_TABLE="INSERT INTO DATE_INFO VALUES(?,?)";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		int id=0;
		String date=null;
		SimpleDateFormat sd=null;
		java.util.Date udob=null;
		java.sql.Date sdob=null;
		long ms=0l;
		int count=0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//get ConnectionObj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get precompiled sql query
			ps=con.prepareStatement(INSERT_DATE_TABLE);
			//read the inputs
			sc=new Scanner(System.in);
			System.out.println("enter the person id");
			id=sc.nextInt();
			System.out.println("Enter dob of the person(mm-dd-yyyy)");
			date=sc.next();
			//holds the pattern of date using simple date format
			sd=new SimpleDateFormat("dd-MM-yyyy");
			//parse the value to java.util date
			//udob=new java.util.Date();
			udob=sd.parse(date);
			System.out.println(udob);
			//convert util date class obj to  sql date class obj
			ms=udob.getTime();
			sdob=new java.sql.Date(ms);
			System.out.println(sdob);
			//set param values to queRy
			if(ps!=null) {
				ps.setInt(1, id);
				ps.setDate(2, sdob);
				//execute the query
				count=ps.executeUpdate();
			}
			if(count==0)
				System.out.println("record not inserted");
			else
				System.out.println("record inserted");	
		}//try
		catch(SQLException se) {
			se.printStackTrace();
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
				if(sc!=null)
					sc.close();
				}
				catch(Exception se){
					se.printStackTrace();
				}
			
}//finally
}//main
}//class