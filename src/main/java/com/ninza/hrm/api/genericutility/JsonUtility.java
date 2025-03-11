package com.ninza.hrm.api.genericutility;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class JsonUtility {
	
	public String getAccessToken() throws Throwable
	{
		FileUtility fLib=new FileUtility();
		 Response resp = given()
				    .formParam("ClientId", fLib.getDataFromPropertiesFile("ClientId"))
				    .formParam("client_secret", fLib.getDataFromPropertiesFile("client_secret"))
				    .formParam("grant_type", "client_credentials")
				.when()
				    .post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
				resp.then()
				    .log().all();
				
				//capture token from response
				String token = resp.jsonPath().get("access_token");
				return token;
	}

}
