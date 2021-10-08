package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*create or replace procedure getEmpdetails_By_No(name out varchar2,desg out varchar2,
		salary out number,no in number)as
begin
select ename,job,sal into name,desg,salary from emp where empno=no;
end; */

public class CsProcedureTest2 {
	private static final String GET_EMP_DETAILS_QUERY="{CALL GETEMPDETAILS_BY_NO(?,?,?,?)}";

	public static void main(String[] args) {
		Connection con=null;
		CallableStatement cs=null;
		int empno=0;
		String name=null,job=null;
		float sal=0.0f;
		Scanner sc=null;
		//get Connection obj
		try {
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//get Callable Statement obj
		cs=con.prepareCall(GET_EMP_DETAILS_QUERY);
		if(cs!=null) {
			//register out params with jdbc data types
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.FLOAT);
			//set values to query params
			sc=new Scanner(System.in);
			System.out.println("EntEr emp no");
			empno=sc.nextInt();
			cs.setInt(4, empno);
			//excute the query
			cs.execute();
			//get results from out params
			System.out.println("name ::"+cs.getString(1)+"  job ::"+cs.getString(2)+"  salary ::"+cs.getFloat(3) );
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
