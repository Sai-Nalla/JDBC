package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class BLOBRetrieveOracleTest {
	
	private static final String RETRIEVE_SHADI_INFO_QUERY="SELECT PID,PNAME,AGE,ADDRS,PHOTO,VIDEO  FROM SHADI_INFO WHERE PID=?";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		String name=null,addrs=null;
		InputStream PhotoIS=null,VideoIS=null;
		OutputStream PhotoOS=null,VideoOS=null;
		ResultSet rs=null;
		int id=0,pid=0;
		float age=0.0f;
		//load drivEr class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//get Connection obj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//get prepared Statement obj
			if(con!=null) 
				ps=con.prepareStatement(RETRIEVE_SHADI_INFO_QUERY);
				sc=new Scanner(System.in);
				System.out.println("Enter the person id");
				id=sc.nextInt();
			if(ps!=null) {
				//set values to the query params
				ps.setInt(1, id);
				//execute the query
				rs=ps.executeQuery();
			}
			//process the result set obj
				if(rs.next()) {
					pid=rs.getInt(1);
					name=rs.getString(2);
					age=rs.getFloat(3);
					addrs=rs.getString(4);
					PhotoIS=rs.getBinaryStream(5);
					VideoIS=rs.getBinaryStream(6);
					//create output stream obj pointing to new file
				PhotoOS=new FileOutputStream("new_pic.jpg");
				VideoOS=new FileOutputStream("new_video.mp4");
				//copy the data of input stream File to output stream file
				IOUtils.copy(PhotoIS, PhotoOS);
				IOUtils.copy(VideoIS, VideoOS);
					
					System.out.println("pid ::"+pid+"name"+name+"age::"+age+"addrs::"+addrs);
				}	
				else
					System.out.println("Record not Found");		
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		
		catch(Exception e) {
			e.printStackTrace();
	}
		finally{
			try{
				if(PhotoIS!=null)
					PhotoIS.close();
				}
				catch(Exception se){
					se.printStackTrace();
				}
			try{
					if(VideoIS!=null)
						VideoIS.close();
					}
					catch(Exception se){
						se.printStackTrace();
					}
			try{
				if(PhotoOS!=null)
					PhotoOS.close();
				}
				catch(Exception se){
					se.printStackTrace();
				}
			try{
					if(VideoOS!=null)
						VideoOS.close();
					}
					catch(Exception se){
						se.printStackTrace();
					}
			try{
				if(rs!=null)
					rs.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			try{
				if(ps!=null)
					ps.close();
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
