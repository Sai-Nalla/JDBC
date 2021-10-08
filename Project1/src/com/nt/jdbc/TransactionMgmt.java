package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class TransactionMgmt {

	public static void main(String[] args) {
		
		int srcAcno=0,desAcno=0;
		float amount=0.0f;
		Scanner sc=null;
		Connection con=null;
		InputStream is=null;
		Properties props=null;
		Statement st=null;
		boolean flag=false;
		try {
			//Enter input values
			sc=new Scanner(System.in);
			System.out.println("Enter src account no");
			srcAcno=sc.nextInt();
			System.out.println("Enter dest account no");
			desAcno=sc.nextInt();
			System.out.println("Enter Transfer Amount");
			amount=sc.nextFloat();
			//create File Input stream obj pointing ro prperties file
			is=new FileInputStream("src/com/nt/commons/jdbc.properties");
			//create properties class obj
			props=new Properties();
			//load properties file
			props.load(is);
			//get Coonection obj
			con=DriverManager.getConnection(props.getProperty("jdbc.url"),props.getProperty("jdbc.user"),props.getProperty("jdbc.pwd"));
			//get statement obj
			if(con!=null) {
				st=con.createStatement();
				//set autocommit to false
				con.setAutoCommit(false);
			//add queries to batch 
				st.addBatch("UPDATE JDBC_BANKACCOUNT SET AMOUNT=AMOUNT-"+amount+"WHERE ACCNO="+srcAcno);
				st.addBatch("UPDATE JDBC_BANKACCOUNT SET AMOUNT=AMOUNT+"+amount+"WHERE ACCNO="+desAcno);
				//execute the query
				int result[];
				result=st.executeBatch();
				for(int i=0;i<result.length;i++) {
					if(result[i]==0) {
						flag=true;
					}
				}//
			}
			}//try
		catch(SQLException se) {
			flag=true;
			se.printStackTrace();
		}
		catch(Exception e) {
			flag=true;
			e.printStackTrace();
		}
		finally {
			try {
			if(flag==true) {
				con.rollback();
				System.out.println("Amount Transaction Failed");
			}	
			else {
				con.commit();
				System.out.println("Transaction completed Succesfully");
			}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			//close the jdbc obj
			try {
				if(st!=null) {
					st.close();
				}
			}
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				if(con!=null) {
					con.close();
				}
			}
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				if(sc!=null) {
					sc.close();
				}
			}
				catch(Exception e) {
					e.printStackTrace();
				}
		}//finally
	}//main
}//class
