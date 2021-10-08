package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;
/*create or replace procedure P_GET_EMPDETAILS_By_DESGS
(
	DESG1 IN VARCHAR2,
	DESG2 IN VARCHAR2,
EMPDETAILS OUT SYS_REFCURSOR
)AS
BEGIN
OPEN EMPDETAILS FOR
SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN(DESG1,DESG2) ORDER BY JOB;
END;
/*/


public class CsprocedureCursorTest4 {
	
	private static final String GET_EMP_DETAILS="{CALL  P_GET_EMPDETAILS_By_DESGS(?,?,?)}";
	
	public static void main(String[] args) {
		Connection con=null;
		CallableStatement cs=null;
		Scanner sc=null;
		String Desg1=null,Desg2=null,EName=null,job=null;
		int Empno=0;
		float sal=0.0f;
		ResultSet rs=null;
		boolean flag=false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//get Connection obj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get callable Statement obj
			if(con!=null) 
				cs=con.prepareCall(GET_EMP_DETAILS);
			//register  out params with Oracle datatypes
			if(cs!=null)
				cs.registerOutParameter(3, OracleTypes.CURSOR);
				sc=new Scanner(System.in);
				System.out.println("Enter the Desg1");
				Desg1=sc.next().toUpperCase();
				System.out.println("Enter the Desg2");
				Desg2=sc.next().toUpperCase();
				//Set values to query  params
				if(cs!=null) {
					cs.setString(1,Desg1);
					cs.setString(2,Desg2 );
				//execute the query
				cs.execute();
				}
				//get results from out params
				rs=(ResultSet) cs.getObject(3);
				if(rs!=null) {
					while(rs.next()) {
						flag=true;
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}
					if(flag) {
						System.out.println("Records are Dislayed");
					}
					else
						System.out.println("Record not Found");
				}
	}//try
		catch(SQLException se) {
			//System.out.println(se.);
			se.printStackTrace();
		}
		
		catch(Exception e) {
			e.printStackTrace();
	}
		finally{
			try{
				if(cs!=null)
					cs.close();
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
