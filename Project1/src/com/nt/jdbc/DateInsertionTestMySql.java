package com.nt.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertionTestMySql {
	private static final String INSERT_PERSON_DETAILS="INSERT INTO PERSON_INFO(PNAME,DOB,DOJ,DOM) VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		int id=0;
		String pname=null;
		String Dob=null,Dom=null,Doj=null;
		SimpleDateFormat sd1=null,sd2=null;
		java.util.Date udob=null,udoj=null;
		java.sql.Date sdob=null,sdoj=null,sdom=null;
		long ms=0l,ms2=0l;
		int count=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//get ConnectionObj
			con=DriverManager.getConnection("jdbc:mysql:///ntaj414db","root","root");
			//get precompiled sql query
			if(con!=null)
				ps=con.prepareStatement(INSERT_PERSON_DETAILS);
			//Enter the input values
			System.out.println("ENter the person  name");
			sc=new Scanner(System.in);
			pname=sc.next();
			System.out.println("Enter the Dob value(DD-MM-YYYY");
			Dob=sc.next();
			System.out.println("ENter the Doj value(MM-dd-YYYY");
			Doj=sc.next();
			System.out.println("ENter the Dom value(YYYY-MM-dd)");
			Dom=sc.next();
			//convert String date  value to java.uti.date format
			sd1=new SimpleDateFormat("dd-MM-yyyy");
			sd2=new SimpleDateFormat("MM-dd-yyyy");
			
			//parese the value java.util.date format
			udob=sd1.parse(Dob);
			udoj=sd2.parse(Doj);
			//convert the Jva.util.date format to java.sql.date format
			ms=udob.getTime();
			sdob=new java.sql.Date(ms);
			ms2=udoj.getTime();
			sdoj=new java.sql.Date(ms2);
			sdom= java.sql.Date.valueOf(Dom);
			if(ps!=null) {
				//set valu to the query param
				ps.setString(1, pname);
				ps.setDate(2, sdob);
				ps.setDate(3,sdoj);
				ps.setDate(4, sdom);
				//execute the query
				count=ps.executeUpdate();
				}
			if(count==0)
				System.out.println("record not inserted");
			else
				System.out.println("REcord inserted");
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
