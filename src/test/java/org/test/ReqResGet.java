package org.test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class ReqResGet {
	Response response;

	@BeforeMethod
	public void beforeMethod() {
		baseURI = "https://reqres.in/api/";
	}

	@Test
	public void listUsers() {
		given().get("users?page=2").then().statusCode(200).body("data[4].first_name", equalTo("George"))
				.body("data.first_name", hasItems("George", "Rachel"));
	}

	@Test
	public void singleUser() {
		given().get("users/2").then().statusCode(200).body("data.last_name", equalTo("Weaver"));
	}

	@Test
	public void singleUserNotFound() {
		response = RestAssured.given().get("unknown/23");
		Assert.assertEquals(response.getStatusCode(), 404);
		Object object = response.jsonPath().get();
		Assert.assertEquals(object.toString(), "{}");
//		given().get("users/23").then().statusCode(404).body("", equalTo("{}"));
	}

	@Test
	public void listOfResource() {
		response = RestAssured.given().get("unknown");
		Assert.assertEquals(response.getStatusCode(), 200);
		List<Object> list = response.jsonPath().getList("data.name");
		Assert.assertEquals(list.contains("true red"), true);
	}

	@Test
	public void SingleResource() {
		response = RestAssured.given().get("unknown/2");
		Assert.assertEquals(response.getStatusCode(), 200);
		Object object = response.jsonPath().get("data.year");
		Assert.assertEquals(object.toString(), "2001");
	}
	@Test
	public void singleResourceNotFound() {
		response = RestAssured.given().get("unknown/23");
		Assert.assertEquals(response.getStatusCode(), 404);
		Object object = response.jsonPath().get();
		Assert.assertEquals(object.toString(), "{}");
	}
}
