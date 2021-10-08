package com.nt.jdbc;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*create or replace procedure P_AUTHENTICATION(user in varchar2,password in varchar2,
		result out varchar2)
as
cnt number;
begin
select count(*) into cnt from userinfo where uname=user and pwd=password;
if(cnt<>0)then
result:='VALID CREDENTIALS';
else
result:='INVALID CREDENTIALS';
end if;
end;
/*/


public class CsProcedureTest3 {
	private static final String AUTHENTICATION_QUERY="{CALL P_AUTHENTICATION(?,?,?)}";

	public static void main(String[] args) {
		Connection con=null;
		CallableStatement cs=null;
		int empno=0;
		String uname=null,pwd=null;
		String result=null;
		Scanner sc=null;
		//get Connection obj
		try {
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//get Callable Statement obj
		cs=con.prepareCall(AUTHENTICATION_QUERY);
		if(cs!=null) {
			//register out params with jdbc data types
			cs.registerOutParameter(3, Types.VARCHAR);
		
			//set values to query params
			sc=new Scanner(System.in);
			System.out.println("Enter user name ");
			uname=sc.next();
			System.out.println("Enter password");
			pwd=sc.next();
			cs.setString(1, uname);
			cs.setString(2,pwd);
			//excute the query
			cs.execute();
			result=cs.getString(3);
			//get results from out params
			System.out.println("Result is ::"+result);
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
