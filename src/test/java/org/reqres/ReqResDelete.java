package org.reqres;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class ReqResDelete {
	JSONObject request = new JSONObject();

	@BeforeMethod
	public void beforeMethod() {
		baseURI = "https://reqres.in/api/";
	}

	@Test
	public void delete() {
		when().delete("users/2").then().statusCode(204).log().all();
	}
}
