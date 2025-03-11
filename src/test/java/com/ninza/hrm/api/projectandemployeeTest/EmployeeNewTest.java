package com.ninza.hrm.api.projectandemployeeTest;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.constants.endpoint.IEndPoint;

import io.restassured.http.ContentType;
import pojoclass.utility.EmployeePojo;
import pojoclass.utility.ProjectPojo;

public class EmployeeNewTest {
	
	JavaUtility jLib=new JavaUtility();
	FileUtility fLib=new FileUtility();
	DataBaseUtility dbLib=new DataBaseUtility();
	String Baseurl;
	
	@Test
	public void addEmployeeToProject() throws Throwable {
		Baseurl = fLib.getDataFromPropertiesFile("BaseURI");
		
		String projectName = "Tata" +jLib.getRandomNumber();
		ProjectPojo pObj = new ProjectPojo(projectName, "Divya", "Created", 0);

		given().contentType(ContentType.JSON).body(pObj).when().post(Baseurl + IEndPoint.ADD_Proj).then()
				.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ").log().all();

		// Api-2 ===> add employee to same project
		String userName = "Divya" + jLib.getRandomNumber();
		EmployeePojo emp = new EmployeePojo("TeamLead", "23-04-1990", "divya@gmail.com", userName, 6,
				"9898989898", projectName, "QA", userName);

		given().contentType(ContentType.JSON).body(emp).when().post(Baseurl + IEndPoint.ADD_Emy).then()
				.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ").and().contentType(ContentType.JSON).and()
				.time(Matchers.lessThan(3000L)).log().all();

		// Verify EmployeeName in DataBase layer
		
		dbLib.getDbconnection();
		dbLib.executeSelectQueryAndVerifyInDB("select * from employee", 11, userName);
		dbLib.closeDbconnection();
	}
	
	@Test
	public void addEmployeeWithoutEmailTest() throws SQLException {
		
		// Api-1 ===> add a project in the server
		String projectName = "Tata" + jLib.getRandomNumber();
		ProjectPojo pObj = new ProjectPojo(projectName, "Divya", "Created", 0);

		given()
			.contentType(ContentType.JSON)
			.body(pObj)
		.when()
			.post(Baseurl + IEndPoint.ADD_Proj)
		.then()
			.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ")
			.log().all();

		// Api-2 ===> add employee to same project
		String userName = "Divya" + jLib.getRandomNumber();
		EmployeePojo emp = new EmployeePojo("TeamLead", "23-04-1990", "", userName, 6, "9898989898",
				projectName, "QA", userName);

		given()
			.contentType(ContentType.JSON)
			.body(emp)
		.when()
			.post(Baseurl + IEndPoint.ADD_Emy)
		.then()
			.assertThat()
			.statusCode(500)
			.and().contentType(ContentType.JSON)
			.and().time(Matchers.lessThan(3000L))
			.log().all();
	}

}
