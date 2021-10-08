package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {
	
	public static void main(String[] args)
	{
		Connection con=null;
		Statement st=null;
		String query=null;
		String job=null;
		Scanner sc=null;
		int count=0;
		//load the jdbc driver class
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//get ConnectionObj
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//get Sstatement obj
		st=con.createStatement();
		//read the inputs
		sc=new Scanner(System.in);
	System.out.println("Enter designation");
		job=sc.next();	
		job=job.toUpperCase();
		//convert into aql query type
		job="'"+job+"'";
		//prepare query
		//SQL> update emp set sal=(sal*0.2) where job='SALESMAN';
		query="update emp set sal=(sal*0.2) where job="+job;
		//execut query
		count=st.executeUpdate(query);
		if(count==0)
			System.out.println("No records are updated");
		else
			System.out.println("records are updates");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//close the connection obj
		finally{
		try{
			if(st!=null)
				st.close();
			}
			catch(Exception e2){
				e2.printStackTrace();
			}
			try{
			if(con!=null)
				con.close();
			}
			catch(Exception e3){
				e3.printStackTrace();
			}
		}//finally

	}//main

}//class
