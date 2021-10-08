package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*create or replace function FX_GET_DNAME_BY_DEPTNO(
		NO IN NUMBER,
		NAME OUT VARCHAR2
)RETURN VARCHAR2 AS
LOCT VARCHAR2(242);
BEGIN
SELECT DNAME,LOC INTO NAME,LOCT FROM DEPT WHERE DEPTNO=NO;
RETURN LOCT;
END;
/*/


public class CsProcedureTest5 {
	
private static final String CALL_FUNCTION_QUERY= "{ ?= call FX_GET_DNAME_BY_DEPTNO(?,?)}";
	public static void main(String[] args) {
		Connection con=null;
		CallableStatement cs=null;
		Scanner sc=null;
		int Deptno=0;
		//get Connection obj
		try {
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//get Callable Statement obj
		if(con!=null)
		cs=con.prepareCall(CALL_FUNCTION_QUERY);
		sc=new Scanner(System.in);
		System.out.println("Enter DeptNo");
		Deptno=sc.nextInt();
		//register out parameterswith jdbc data types
		if(cs!=null) {
		cs.registerOutParameter(1, Types.VARCHAR);//return
		cs.registerOutParameter(3, Types.VARCHAR);//out
		}
		//set values to query param
		if(cs!=null) {
			cs.setInt(2, Deptno);
			//execute the query
			cs.execute();
			System.out.println(cs.getString(3)+" "+cs.getString(1));
		}//if
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
