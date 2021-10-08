package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBInsertionOracleTest {
	private static final String  STUDENT_RESUME_QUERY="INSERT INTO STUDENT_RESUME VALUES(STUDENT_REUME_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		String name=null,addrs=null,resumelocation=null;
		Reader reader=null;
		int count=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//get Connection obj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get prepared Statement obj
			if(con!=null) 
				ps=con.prepareStatement(STUDENT_RESUME_QUERY);
			//read the values
			sc=new Scanner(System.in);
			System.out.println("Enter Student name");
			name=sc.next();
			System.out.println("Enter student addrs");
			addrs=sc.next();
			System.out.println("Enter resume location ");
			resumelocation=sc.next();
			//get the data from file
			reader=new FileReader(resumelocation);
			//SET Values to quey params
			if(ps!=null) {
				ps.setString(1, name);
				ps.setString(2, addrs);
				ps.setClob(3,reader );
			}
			//execute query
			if(ps!=null)
				count=ps.executeUpdate();
			if(count==0)
				System.out.println("Recor not Inserted");
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
					if(reader!=null)
						reader.close();
					}
					catch(Exception e){
						e.printStackTrace();
					}
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
