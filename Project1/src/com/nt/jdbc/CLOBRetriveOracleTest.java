package com.nt.jdbc;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class CLOBRetriveOracleTest {
	private static final String  STUDENT_RESUME_QUERY="SELECT SID,SNAME,ADDRS,STUDENT_BIO FROM  STUDENT_RESUME WHERE SID=?";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		String name=null,addrs=null,resumelocation=null;
		int pid=0;
		Writer writer=null;
		Reader reader=null;
		int count=0;
		ResultSet rs=null;
		int data=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//get Connection obj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get prepared Statement obj
			if(con!=null) 
				ps=con.prepareStatement(STUDENT_RESUME_QUERY);
			//read the values
			sc=new Scanner(System.in);
			System.out.println("Enter Student ID ");
			pid=sc.nextInt();
			
			//SET Values to quey params
			if(ps!=null) {
				ps.setInt(1,pid);
			}
			//execute query
			if(ps!=null)
				rs=ps.executeQuery();
			if(rs.next()) {
				pid=rs.getInt(1);
				name=rs.getString(2);
				addrs=rs.getString(3);
				reader=rs.getCharacterStream(4);
				if(reader!=null)
					reader=new FileReader("C:\\Users\\karthik\\Desktop\\abc.txt");
					writer=new FileWriter("resume.txt");
				if(reader!=null&&writer!=null) {
					while((data=reader.read())!=-1) {
					writer.write((char)data);
					}
					//IOUtils.copy(reader,writer);
				System.out.println(+pid+" "+name+" "+addrs);
				System.out.println("Rescord rettrieved");
				}
			}
			else
				System.out.println("Record not found");
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
