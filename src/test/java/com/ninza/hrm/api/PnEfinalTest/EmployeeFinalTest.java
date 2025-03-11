package com.ninza.hrm.api.PnEfinalTest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseApiClass;
import com.ninza.hrm.constants.endpoint.IEndPoint;

import pojoclass.utility.EmployeePojo;
import pojoclass.utility.ProjectPojo;

public class EmployeeFinalTest extends BaseApiClass 
{		
	@Test
	public void addEmployeeToProject() throws Throwable 
	{
		//String Baseurl = fLib.getDataFromPropertiesFile("BaseURI");
		String userName = "Divya" + jLib.getRandomNumber();
		String projectName = "Tata" +jLib.getRandomNumber();
		
		ProjectPojo pObj = new ProjectPojo(projectName, "Divya", "Created", 0);

		given()
			.spec(specReqObj)
			.body(pObj)
		.when()
			.post( IEndPoint.ADD_Proj)
		.then()
			.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ")
			.spec(specResObj)
			.log().all();

		// Api-2 ===> add employee to same project
		
		EmployeePojo emp = new EmployeePojo("TeamLead", "23-04-1990", "divya@gmail.com", userName, 6,
				"9898989898", projectName, "QA", userName);

		given()
			.spec(specReqObj)
			.body(emp)
		.when()
			.post(IEndPoint.ADD_Emy)
		.then()
			.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ")
			.time(Matchers.lessThan(5000L))
			.spec(specResObj)
			.log().all();

		// Verify EmployeeName in DataBase layer
		dbLib.getDbconnection();
		dbLib.executeSelectQueryAndVerifyInDB("select * from employee", 11, userName);
	}
	
	@Test
	public void addEmployeeWithoutEmailTest() throws Throwable
	{
		//String Baseurl = fLib.getDataFromPropertiesFile("BaseURI");
		// Api-1 ===> add a project in the server
		String projectName = "Tata" + jLib.getRandomNumber();
		String userName = "Divya" + jLib.getRandomNumber();
		
		ProjectPojo pObj = new ProjectPojo(projectName, "Divya", "Created", 0);

		given()
			.spec(specReqObj)
			.body(pObj)
		.when()
			.post( IEndPoint.ADD_Proj)
		.then()
			.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ")
			.spec(specResObj)
			.log().all();

		// Api-2 ===> add employee to same project
		
		EmployeePojo emp = new EmployeePojo("TeamLead", "23-04-1990", "", userName, 6, "9898989898",
				projectName, "QA", userName);

		given()
			.spec(specReqObj)
			.body(emp)
		.when()
			.post( IEndPoint.ADD_Emy)
		.then()
			.assertThat()
			.statusCode(500)
			.time(Matchers.lessThan(3000L))
			.spec(specResObj)
			.log().all();
	}
}
