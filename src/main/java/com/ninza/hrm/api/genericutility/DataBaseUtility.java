package com.ninza.hrm.api.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility 
{
	Connection conn;
	public void getDbconnection(String url,String un,String pwd) throws Throwable
	{
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		conn=DriverManager.getConnection(url,un,pwd);
		}catch (Exception e) {
		}
	}
	
	FileUtility fLib = new FileUtility();
	
	public void getDbconnection() throws Throwable
	{
		String DBUrl = fLib.getDataFromPropertiesFile("DBUrl");
		String DB_Username = fLib.getDataFromPropertiesFile("DB_Username");
		String DB_Password = fLib.getDataFromPropertiesFile("DB_Password");
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		conn=DriverManager.getConnection(DBUrl,DB_Username,DB_Password);
		}catch (Exception e) {
		}
	}
	
	public void closeDbconnection() throws Throwable
	{
		try {
		conn.close();
		}catch (Exception e) {
		}
	}
	
	public boolean executeSelectQueryAndVerifyInDB(String query , int index , String projectName) throws SQLException
	{
		ResultSet result=null;
		boolean flag=false;
		try {
		Statement stat=conn.createStatement();
		result=stat.executeQuery(query);
		}catch (Exception e) {
		}
		while(result.next())
		{
			if(result.getString(index).equals(projectName))
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println(projectName+"Project in DB verified");
			return flag;
		}
		else
		System.out.println(projectName+"Project in DB not verified");
		return flag;
	}
	
	public int executeNonSelectQuery(String query) throws Throwable
	{
		int result=0;
		try {
		Statement stat=conn.createStatement();
		result=stat.executeUpdate(query);
		}catch (Exception e) {
		}
		return result;
	}
}
