package com.ninza.hrm.api.PnEfinalTest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseApiClass;
import com.ninza.hrm.constants.endpoint.IEndPoint;

import io.restassured.response.Response;
import pojoclass.utility.ProjectPojo;

public class ProjectFinalTest extends BaseApiClass 
{
	String Baseurl;
	ProjectPojo pObj;
	
	@Test
	public void addSingleProjectWithCreated() throws Throwable
	{
		
		//Baseurl = fLib.getDataFromPropertiesFile("BaseURI");

		String projectName = "TCS" + jLib.getRandomNumber();
		String expMsg = "Successfully Added";

		pObj = new ProjectPojo(projectName, "Divya", "Created", 0);

		// Verify in API Layer
		Response resp = given()
				.spec(specReqObj)
				.body(pObj)
			.when().post(IEndPoint.ADD_Proj);
		resp.then()
		.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ")
		.assertThat().time(Matchers.lessThan(3000L))
		.spec(specResObj)
		.log().all();

		String actMsg = resp.body().jsonPath().get("msg");
		Assert.assertEquals(expMsg, actMsg);
		
		// Verify projectName in DataBase layer
		dbLib.getDbconnection();
		dbLib.executeSelectQueryAndVerifyInDB("Select * from project", 4, projectName);
	}
		
	@Test(dependsOnMethods = "addSingleProjectWithCreated")
	public void createDuplicateProjectWithCreatedTest() throws Throwable 
	{
		//Baseurl = fLib.getDataFromPropertiesFile("BaseURI");
			given()
				.spec(specReqObj)
				.body(pObj)
			.when()
				.post(IEndPoint.ADD_Proj)
			.then()
				.assertThat()
				.statusCode(409)
				.spec(specResObj)
				.log().all();
	}
}


