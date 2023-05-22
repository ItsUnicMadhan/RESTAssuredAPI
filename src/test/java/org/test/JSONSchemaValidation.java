package org.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JSONSchemaValidation {
	@Test
	public void test_1() {
		baseURI = "https://reqres.in/";
		given()
		.when()
		.get("api/users?page=2")
		.then()
		.assertThat()
		.body(JsonSchemaValidator.matchesJsonSchema(new File("src\\test\\resources\\schema.json")));
	}

}
