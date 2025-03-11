package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoclass.utility.ProjectPojo;

public class ProjectTest {

	JavaUtility jLib = new JavaUtility();
	FileUtility fLib = new FileUtility();
	DataBaseUtility dbLib = new DataBaseUtility();
	String Baseurl;

	ProjectPojo pObj;

	@Test
	public void addSingleProjectWithCreated() throws Throwable {

//		Random ran=new Random();
//		int randNum=ran.nextInt(5000);

		Baseurl = fLib.getDataFromPropertiesFile("BaseURI");

		String projectName = "TCS" + jLib.getRandomNumber();
		String expMsg = "Successfully Added";

		pObj = new ProjectPojo(projectName, "Divya", "Created", 0);

		// Verify in API Layer
		Response resp = given().contentType(ContentType.JSON).body(pObj).when().post(Baseurl + "/addProject");
		resp.then().assertThat().statusCode(201).statusLine("HTTP/1.1 201 ").assertThat().contentType(ContentType.JSON)
				.assertThat().time(Matchers.lessThan(3000L)).log().all();

		String actMsg = resp.body().jsonPath().get("msg");
		Assert.assertEquals(expMsg, actMsg);

		// Verify projectName in DataBase layer
		// boolean flag=false;

//		Driver driver=new Driver();
//		DriverManager.registerDriver(driver);
//		Connection conn=DriverManager.getConnection(DBUrl,"root","root");
		dbLib.getDbconnection();
		// ResultSet result=conn.createStatement().executeQuery("select * from
		// project");
		dbLib.executeSelectQueryAndVerifyInDB("Select * from project", 4, projectName);
//		while(result.next())
//		{
//			if(result.getString(4).equals(projectName))
//			{
//				flag=true;
//				break;
//			}
//		}
//		while(result.next())
//		{
//			System.out.println(result.getString(2));
//			
//		}
		dbLib.closeDbconnection();
		//Assert.assertTrue(flag, "Project in DB not verified");
	}

	@Test(dependsOnMethods = "addSingleProjectWithCreated")
	public void createDuplicateProjectWithCreatedTest() {
		given().contentType(ContentType.JSON).body(pObj).when().post(Baseurl + "/addProject").then().assertThat()
				.statusCode(409).log().all();
	}

}
