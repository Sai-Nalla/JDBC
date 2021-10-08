package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateRetrieveTest {
	private static final String DATE_RETRIEVE_QUERY="SELECT *FROM PERSON_INFO WHERE PID=?";

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		ResultSet rs=null;
		int id=0,pid=0;
		String name=null;
		String Dob=null,Doj=null,Dom=null;
		java.sql.Date sdob=null,sdoj=null,sdom=null;
		SimpleDateFormat sdf=null;
		try {
			sc=new Scanner(System.in);
			System.out.println("enter the person id");
			id=sc.nextInt();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//get ConnectionObj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get precompiled sql query
			if(con!=null) {
				ps=con.prepareStatement(DATE_RETRIEVE_QUERY);
				//set value to query param
				ps.setInt(1,id);
			}
			//execute the query
			if(ps!=null)
				rs=ps.executeQuery();
			//System.out.println(rs);
			//get Result set obj
					if(rs.next()) {
							pid=rs.getInt(1);
							name=rs.getString(2);
							sdob=rs.getDate(3);
							sdoj=rs.getDate(4);
							sdom=rs.getDate(5);
							//converting sql date obj to Simple date format
							sdf=new SimpleDateFormat("MMM-dd-yyyy");
							Dom=sdf.format(sdom);
							Dob=sdf.format(sdob);
							Doj=sdf.format(sdoj);
							
							System.out.println(pid+"  "+name+" "+sdob+"  "+sdoj+"  "+sdom);
							System.out.println(pid+"  "+name+" "+Dob+"  "+Doj+"  "+Dom);
			}
					else
				System.out.println("Record not Found");
			
		}
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
	}//main
}//clas
