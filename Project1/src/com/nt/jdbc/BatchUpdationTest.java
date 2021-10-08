package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdationTest {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager")){
			if(con!=null)
				try(Statement st=con.createStatement()){
					if(st!=null) {
						st.addBatch("INSERT INTO STUDENT VALUES(9,'Manoj','Delhi',87.90)");
						st.addBatch("UPDATE STUDENT SET AVG=AVG+20 WHERE AVG<65");
						st.addBatch("DELETE STUDENT WHERE SID=7");
					}
					int result[]=st.executeBatch();
					int total=0;
					for(int i=0;i<result.length;i++) 
						total= total+result[i];
						System.out.println("no of records that are effected "+total);
					}	
				}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
