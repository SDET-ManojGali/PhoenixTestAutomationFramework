package com.api.tests;

import com.api.pojos.UserCredentials;
import static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class LoginAPITest {

    @Test
    public void loginAPITest(){
        UserCredentials userCredentials = new UserCredentials("iamfd","password");
                given().
                baseUri(getProperty("BASE_URI"))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userCredentials)
                .log().uri()
                .log().headers()
                .log().method()
                .log().body()
                .when()
                .post("login")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .time(lessThan(1500L))
                .body("data.token", notNullValue())
                .body("message",equalTo("Success"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/LoginResponseSchema.json"))
                .extract().response();
    }
}
