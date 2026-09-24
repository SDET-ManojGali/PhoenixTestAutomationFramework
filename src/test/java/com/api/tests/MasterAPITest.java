package com.api.tests;

import static io.restassured.RestAssured.*;
import com.api.constants.Role;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class MasterAPITest {

    @Test
    public static void masterAPITest(){
        given().
                spec(SpecUtil.requestSpecWithAuth(Role.FD))
                .when()
                .post("master")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message",Matchers.equalTo("Success"))
                .body("data",Matchers.notNullValue())
                .body("data",Matchers.hasKey("mst_oem"))
                .body("data",Matchers.hasKey("mst_model"))
                .body("$",Matchers.hasKey("data"))
                .body("data.mst_oem",Matchers.hasSize(2))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/MasterAPIResponseSchema.json"));
    }

    @Test
    public void invalidTokenMasterAPITest(){
        given().
                spec(SpecUtil.requestSpec())
                .when()
                .post("master")
                .then()
                .spec(SpecUtil.responseSpec_TEXT(401));
    }
}
