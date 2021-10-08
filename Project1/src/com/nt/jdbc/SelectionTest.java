package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectionTest {

	public static void main(String[] args) {

		Scanner sc=null;
		int Deptno=0;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String query=null;
		boolean flag=false;
		//load driver class
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//create Scannerclass obj
		sc= new Scanner(System.in);
	
		//read the values
		System.out.println("Enter Dept No");
			Deptno=sc.nextInt();
		
			//Get Connection obj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get Statement class obj
			st=con.createStatement();
			query="SELECT * FROM DEPT WHERE DEPTNO="+Deptno;


			//Execute the query
			rs=st.executeQuery(query);
			while(rs.next()){
				flag=true;
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			if(flag)
				System.out.println("Records are displayed");
			else
				System.out.println("No recorrds found");
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
			if(rs!=null)
				rs.close();
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
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

	}

}
