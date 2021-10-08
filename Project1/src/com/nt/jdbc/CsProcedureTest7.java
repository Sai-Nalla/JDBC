package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;
/*create or replace function FX_VEIW_DELETE_STUD_BY_SNO(
		 NO IN NUMBER,
		STUDDETAILS OUT SYS_REFCURSOR
		)RETURN VARCHAR2 AS
	CNT NUMBER(3);
	RESULT VARCHAR2(30);
	BEGIN
		OPEN STUDDETAILS FOR
		SELECT SID,SNAME,ADDRS,AVG FROM STUDENT WHERE SID=NO;
	DELETE FROM STUDENT WHERE SID=NO;
	CNT:=SQL%ROWCOUNT;
	IF(CNT=1)THEN
	RESULT:='RECORD DELETED';
	ELSE
	RESULT:='RECORD NOT FOUND FOR DELETION';
	END IF;
	RETURN RESULT;
	END;
/
*/

public class CsProcedureTest7 {
	
	private static final String CALL_FUNCTION_QUERY="{?=call FX_VEIW_DELETE_STUD_BY_SNO(?,?)}";
	public static void main(String[] args) {
		Connection con=null;
		CallableStatement cs=null;
		Scanner sc=null;
		ResultSet rs=null;
		int sid=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//get Connection obj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get callable Statement obj
			if(con!=null) 
				cs=con.prepareCall(CALL_FUNCTION_QUERY);
			sc=new Scanner(System.in);
			System.out.println("Enter Student id ");
			sid=sc.nextInt();
			//register out params with jdbc data types
			if(cs!=null) {
				cs.registerOutParameter(1, Types.VARCHAR);
				cs.registerOutParameter(3, OracleTypes.CURSOR);
				//set values to query params
				cs.setInt(2, sid);
				//execute the query
				cs.execute();
				//convert to result set obj
				rs=(ResultSet) cs.getObject(3);
				if(rs!=null) {
					if(rs.next())
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
						else
							System.out.println("Record Not Found");
					}//if
				System.out.println("Result is::"+cs.getString(1));
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
