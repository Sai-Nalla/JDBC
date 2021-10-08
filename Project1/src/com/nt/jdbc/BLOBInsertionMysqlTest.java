package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBInsertionMysqlTest {
	
	private static final String INSERT_SHDHI_QUERY="INSERT INTO SHADI_INFO(PNAME,AGE,ADDRS,PHOTO,VIDEO) VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		String name=null,addrs=null,photolocation=null,videolocation=null;
		InputStream PhotoIS=null,VideoIS=null;
		int count=0;
		float age=0.0f;
		//load drivEr class
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//get Connection obj
			con=DriverManager.getConnection("jdbc:mysql:///ntaj414db","root","root");
			//get prepared Statement obj
			if(con!=null) 
				ps=con.prepareStatement(INSERT_SHDHI_QUERY);
			if(ps!=null) {
				//read the input values
				sc=new Scanner(System.in);
				System.out.println("Enter the person name");
				name=sc.next();
				System.out.println("Enter the addrs");
				addrs=sc.next();
				System.out.println("Enter the age");
				age=sc.nextFloat();
				System.out.println("ENter the photo location");
				photolocation=sc.next();
				System.out.println("Enter video location");
				videolocation=sc.next();
				//get Input Stream obj
				PhotoIS=new FileInputStream(photolocation);
				VideoIS=new FileInputStream(videolocation);
				//set values to query params
				ps.setString(1, name);
				ps.setFloat(2, age);
				ps.setString(3,addrs);
				ps.setBlob(4, PhotoIS);
				ps.setBlob(5, VideoIS);
				//execute the query
				count=ps.executeUpdate();
			}
				if(count==0)
					System.out.println("REcord not inserted");
				else
					System.out.println("Record inserted");
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
