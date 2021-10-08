package com.nt.jdbc;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class test1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		java.util.Date udob=null;
		String date=null;
		Scanner sc=null;
		SimpleDateFormat sd=null;
		sc=new Scanner(System.in);
		System.out.println("Enter dob of the person(mm-dd-yyyy)");
			date=sc.next();
		sd=new SimpleDateFormat("mm-dd-yyyy");
		//parse the value to java.util date
		//udob=new java.util.Date();
		udob=sd.parse(date);
		System.out.println(udob);
	}

}
