package org.test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class ReqResPost {
	JSONObject request = new JSONObject();

	@BeforeMethod
	public void beforeMethod() {
		baseURI = "https://reqres.in/api/";
	}

	@Test
	public void create() {
		request.put("name", "Madhan");
		request.put("job", "Software Engineer");

		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().post("users").then().statusCode(201).log().all();
	}

	@Test
	public void registerSuccessful() {

		request.put("email", "eve.holt@reqres.in");
		request.put("password", "pistol");

		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().post("register").then().statusCode(200).log().all();
	}

	@Test
	public void registerUnSuccessful() {
		request.put("email", "sydney@fife");

		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().post("register").then().statusCode(400).log().all();
	}

	@Test
	public void loginSuccessful() {

		request.put("email", "eve.holt@reqres.in");
		request.put("password", "cityslicka");

		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().post("login").then().statusCode(200).log().all();
	}

	@Test
	public void loginUnSuccessful() {
		request.put("email", "peter@klaven");

		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().post("register").then().statusCode(400).log().all();
	}

}
