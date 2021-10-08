package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PropertiesFileTest {

	public static void main(String[] args) {
		//locate the jdbc properties file
		try(InputStream is=new FileInputStream("src/com/nt/commons/jdbc.properties")){
		//craete properties object
		Properties props=new Properties();
		//load the the properties file
		props.load(is);
		Class.forName(props.getProperty("jdbc.driver"));
		try(Connection con=DriverManager.getConnection(props.getProperty("jdbc.url"),props.getProperty("jdbc.user"),props.getProperty("jdbc.pwd"))){
			if(con!=null)
				try(Statement st=con.createStatement()){
					if(st!=null)
						try(ResultSet rs=st.executeQuery("SELECT * FROM STUDENT")){
							if(rs!=null) {
							while(rs.next()) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
							}
							System.out.println("Records are dispalced");
							
						}
							else {
								System.out.println("records not Found");
							}
				}
					
				}
				
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
