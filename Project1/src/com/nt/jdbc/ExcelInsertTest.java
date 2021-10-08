package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcelInsertTest {
	private static final String EXCELL_QUEYRY="INSERT INTO COLLEGE.SHEET1 VALUES (?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0;
		String sname=null;
		float avg=0.0f;
		int count=0;
		try {
			sc=new Scanner(System.in);
			System.out.println("Enter student no");
			sno=sc.nextInt();
			System.out.println("Enter Student name");
			sname=sc.next();
			System.out.println("Enter student avg");
			avg=sc.nextFloat();	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(sc!=null) {
					sc.close();
				}
			}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		try(Connection con=DriverManager.getConnection("jdbc:Excel:///E:/workspace/AdvJava")){
			try(PreparedStatement ps=con.prepareStatement(EXCELL_QUEYRY)){
				ps.setInt(1, sno);
				ps.setString(2,sname);
				ps.setFloat(3,avg);
				
						count=ps.executeUpdate();
						if(count==0)
							System.out.println("Record not Inserted");
						else
							System.out.println("Record Inserted");	
				}//try3
			}//try2
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
