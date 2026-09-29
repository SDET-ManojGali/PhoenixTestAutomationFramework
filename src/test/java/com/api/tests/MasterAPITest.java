package com.api.tests;

import static io.restassured.RestAssured.*;
import com.api.constants.Role;
import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class MasterAPITest {

    @Test(description = "Verifying if master api is giving correct response",groups = {"api","regression","smoke"})
    public static void masterAPITest(){
        given().
                spec(requestSpecWithAuth(Role.FD))
                .when()
                .post("master")
                .then()
                .spec(responseSpec_OK())
                .body("message",Matchers.equalTo("Success"))
                .body("data",Matchers.notNullValue())
                .body("data",Matchers.hasKey("mst_oem"))
                .body("data",Matchers.hasKey("mst_model"))
                .body("$",Matchers.hasKey("data"))
                .body("data.mst_oem",Matchers.hasSize(2))
                .body(matchesJsonSchemaInClasspath("responseSchema/MasterAPIResponseSchema.json"));
    }

    @Test(description = "Verifying if master api is giving correct status code for invalid token",groups = {"api","negative","regression","smoke"})
    public void invalidTokenMasterAPITest(){
        given().
                spec(requestSpec())
                .when()
                .post("master")
                .then()
                .spec(responseSpec_TEXT(401));
    }
}
