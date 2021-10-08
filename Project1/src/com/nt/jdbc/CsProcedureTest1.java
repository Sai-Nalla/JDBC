package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTest1 {
	private static final String GET_PS_QUERY= "{CALL P_FIRST(?,?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int result=0;
		try {
			//create connection obj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get Callable Statement obj
			cs=con.prepareCall(GET_PS_QUERY);
			if(cs!=null) {
				//register out param with jdbc data types
				cs.registerOutParameter(3, Types.INTEGER);
				//set values to in params
				cs.setInt(1,100);
				cs.setInt(2,200);
				//exwcute the query
				cs.execute();
				//get results from out param
				result=cs.getInt(3);
				System.out.println("ResultIs ::"+result);
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
