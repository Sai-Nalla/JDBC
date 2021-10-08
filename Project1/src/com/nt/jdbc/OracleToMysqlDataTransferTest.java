package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMysqlDataTransferTest {
	private static final String GET_BANK_DETAILS="SELECT* FROM BANKACCOUNT";
	private static final String INSERT_BANK_DETAILS="INSERT INTO BANKACCOUNT(ACCNO,HOLDERNAME,BALANCE) VALUES(?,?,?)";
	
	public static void main(String[] args) {
	Connection OraCon=null,mysqlCon=null;
	Statement st=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	float balance=0.0f;
	
	//load the driver class
	try {
		//load the driver class
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Class.forName("com.mysql.cj.jdbc.Driver");
	//get the connection objs
	OraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
	mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj414db","root","root");
	//get stament obj
	if(OraCon!=null)
		st=OraCon.createStatement();
	if(mysqlCon!=null) {
		ps=mysqlCon.prepareStatement(INSERT_BANK_DETAILS);
	}
	//exeute the query
	if(st!=null)
		rs=st.executeQuery(GET_BANK_DETAILS);
//get the details using result set
	if(rs!=null) {
		while(rs.next()) {
			//Set values to the param
			
			ps.setInt(1, rs.getInt(2));
			ps.setString(2,rs.getString(3));
		ps.setFloat(3,rs.getFloat(4));
		ps.executeUpdate();
		}
		System.out.println("records are copied");
	}//if

		
	}//try
	catch(SQLException se) {
		se.printStackTrace();
	}
	
	catch(Exception e) {
		e.printStackTrace();
	}//catch
	finally{
		try{
			if(rs!=null)
				rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		try{
			if(st!=null)
				st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
			if(OraCon!=null)
				OraCon.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(mysqlCon!=null)
					mysqlCon.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
		}//finally
}//main
}//class