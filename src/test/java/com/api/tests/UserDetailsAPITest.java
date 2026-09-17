package com.api.tests;

import static com.api.constants.Role.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static com.api.utils.ConfigManager.*;
import static com.api.utils.AuthTokenProvider.*;

public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest(){
        given()
                .baseUri(getProperty("BASE_URI"))
                .header("Authorization", getToken(FD))
                .accept(ContentType.JSON)
                .log()
                .uri()
                .log()
                .headers()
                .log()
                .method()
                .when()
                .get("userdetails")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(matchesJsonSchemaInClasspath("responseSchema/UserDetailsResponseSchema.json"))
                .log()
                .body();
    }
}
