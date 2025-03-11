package com.ninza.hrm.api.employeetest;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;

import io.restassured.http.ContentType;
import pojoclass.utility.EmployeePojo;
import pojoclass.utility.ProjectPojo;

public class EmployeeTest {
	
	JavaUtility jLib=new JavaUtility();
	FileUtility fLib=new FileUtility();
	DataBaseUtility dbLib=new DataBaseUtility();
	String Baseurl;
	
	@Test
	public void addEmployeeToProject() throws Throwable {
		// create an object to Pojo Class

//		Random ran = new Random();
//		int ranNum = ran.nextInt(5000);

		// Api-1 ===> add a project in the server
		Baseurl = fLib.getDataFromPropertiesFile("BaseURI");
				
		String projectName = "Tata" +jLib.getRandomNumber();
		ProjectPojo pObj = new ProjectPojo(projectName, "Divya", "Created", 0);

		given().contentType(ContentType.JSON).body(pObj).when().post(Baseurl+"/addProject").then()
				.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ").log().all();

		// Api-2 ===> add employee to same project
		String userName = "Divya" + jLib.getRandomNumber();
		EmployeePojo emp = new EmployeePojo("TeamLead", "23-04-1990", "divya@gmail.com", userName, 6,
				"9898989898", projectName, "QA", userName);

		given().contentType(ContentType.JSON).body(emp).when().post(Baseurl+"/employees").then()
				.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ").and().contentType(ContentType.JSON).and()
				.time(Matchers.lessThan(3000L)).log().all();

		// Verify EmployeeName in DataBase layer
		//boolean flag = false;
//		Driver driver = new Driver();
//		DriverManager.registerDriver(driver);
//		Connection conn = DriverManager.getConnection(DBUrl, "root", "root");
//		ResultSet result = conn.createStatement().executeQuery("select * from employee");
		dbLib.getDbconnection();
		dbLib.executeSelectQueryAndVerifyInDB("select * from employee", 11, userName);
//		while (result.next()) {
//			if (result.getString(11).equals(userName)) {
//				flag = true;
//				break;
//			}
//		}
//		conn.close();
		dbLib.closeDbconnection();
	//	Assert.assertTrue(flag, "Employee in DB not verified");
	}

	@Test
	public void addEmployeeWithoutEmailTest() throws SQLException {
		// create an object to Pojo Class
//
//		Random ran = new Random();
//		int ranNum = ran.nextInt(5000);

		// Api-1 ===> add a project in the server
		String projectName = "Tata" + jLib.getRandomNumber();
		ProjectPojo pObj = new ProjectPojo(projectName, "Divya", "Created", 0);

		given().contentType(ContentType.JSON).body(pObj).when().post(Baseurl+"/addProject").then()
				.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ").log().all();

		// Api-2 ===> add employee to same project
		String userName = "Divya" + jLib.getRandomNumber();
		EmployeePojo emp = new EmployeePojo("TeamLead", "23-04-1990", "", userName, 6, "9898989898",
				projectName, "QA", userName);

		given().contentType(ContentType.JSON).body(emp).when().post(Baseurl+"/employees").then()
				.assertThat().statusCode(500).and().contentType(ContentType.JSON).and().time(Matchers.lessThan(3000L))
				.log().all();
	}

}
