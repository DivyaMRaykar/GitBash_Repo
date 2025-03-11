package com.ninza.hrm.api.projectandemployeeTest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.constants.endpoint.IEndPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoclass.utility.ProjectPojo;

public class ProjectNewTest {
	
	JavaUtility jLib = new JavaUtility();
	FileUtility fLib = new FileUtility();
	DataBaseUtility dbLib = new DataBaseUtility();
	String Baseurl;

	ProjectPojo pObj;

	@Test
	public void addSingleProjectWithCreated() throws Throwable {
		
		Baseurl = fLib.getDataFromPropertiesFile("BaseURI");

		String projectName = "TCS" + jLib.getRandomNumber();
		String expMsg = "Successfully Added";

		pObj = new ProjectPojo(projectName, "Divya", "Created", 0);

		// Verify in API Layer
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(pObj)
			.when().post(Baseurl + IEndPoint.ADD_Proj);
		resp.then()
		.assertThat().statusCode(201).statusLine("HTTP/1.1 201 ")
		.assertThat().contentType(ContentType.JSON)
		.assertThat().time(Matchers.lessThan(3000L))
		.log().all();

		String actMsg = resp.body().jsonPath().get("msg");
		Assert.assertEquals(expMsg, actMsg);
		
		// Verify projectName in DataBase layer
		dbLib.getDbconnection();
		dbLib.executeSelectQueryAndVerifyInDB("Select * from project", 4, projectName);
		dbLib.closeDbconnection();
	}
		
	
		@Test(dependsOnMethods = "addSingleProjectWithCreated")
		public void createDuplicateProjectWithCreatedTest() {
			given()
				.contentType(ContentType.JSON).body(pObj)
			.when()
				.post(Baseurl + IEndPoint.ADD_Proj)
			.then()
				.assertThat()
				.statusCode(409)
				.log().all();
	}
}


