package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest2 {
	
	public static void main(String[] args)
	{
		Connection con=null;
		Statement st=null;
		String query=null;
		long sal=0;
		Scanner sc=null;
		int count=0;
		//load the jdbc driver class
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//get ConnectionObj
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		System.out.println(con);
		//get Sstatement obj
		st=con.createStatement();
		System.out.println(st);
		/*
		 * //read the inputs sc=new Scanner(System.in);
		 * System.out.println("enter sal to increase"); sal=sc.nextLong();
		 */
		//prepare query
		//SQL> update emp set sal=(sal+1000) where sal<=2000;
		query="update emp set sal=(sal+1000) where sal<=2000";
		//System.out.println(query);
		//execute query
		System.out.println(query);
		if(st!=null) {
	count=st.executeUpdate(query);
		}
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
