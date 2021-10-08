package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsFunctionTest5 {
	/*
	 * create or replace FUNCTION Fx_GET_STUD_DETAILS_BY_SID( NO IN NUMBER, NAME OUT
	 * VARCHAR2, ADDRESS OUT VARCHAR2)RETURN FLOAT AS
	 *  PERFORMANCE FLOAT; 
	 * BEGIN
	 * SELECT SNAME,ADDRS,AVG INTO NAME,ADDRESS,PERFORMANCE FROM STUDENT WHERE SID=NO; 
	 * RETURN PERFORMANCE;
	 *  END; /
	 */

private static final String CALL_FUNCTION_QUERY= "{ ?=call Fx_GET_STUD_DETAILS_BY_SID(?,?,?)}";
	public static void main(String[] args) {
		Connection con=null;
		CallableStatement cs=null;
		Scanner sc=null;
		int sid=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//get Connection obj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get callable Statement obj
			if(con!=null) 
				cs=con.prepareCall(CALL_FUNCTION_QUERY);
			sc=new Scanner(System.in);
			System.out.println("Enter Sid ");
			sid=sc.nextInt();
			//register out param with sql data ypes
			if(cs!=null) {
				cs.registerOutParameter(1, Types.FLOAT);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.VARCHAR);
				//set vaues to query params
				cs.setInt(2, sid);
				//execute the query
				cs.execute();
				System.out.println(cs.getString(3)+" "+cs.getString(4)+" "+cs.getFloat(1));
			}
		}
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
