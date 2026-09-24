package com.api.tests;

import com.api.utils.SpecUtil;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static com.api.constants.Role.*;

public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest(){
        given()
                .spec(SpecUtil.requestSpecWithAuth(FD))
                .when()
                .get("userdetails")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("responseSchema/UserDetailsResponseSchema.json"))
                .log()
                .body();
    }
}
