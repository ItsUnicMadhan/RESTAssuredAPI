package org.reqres;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class ReqResPatch {
	JSONObject request = new JSONObject();

	@BeforeMethod
	public void beforeMethod() {
		baseURI = "https://reqres.in/api/";
	}

	@Test
	public void update() {
		request.put("name", "Madhan");
		request.put("job", "Senior Software Engineer");

		baseURI = "https://reqres.in/api";
		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().patch("users/2").then().statusCode(200).log().all();
	}

}
