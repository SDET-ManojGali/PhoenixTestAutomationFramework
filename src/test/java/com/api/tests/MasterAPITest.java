package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class MasterAPITest {

    @Test
    public static void masterAPITest(){
        given().
                baseUri(ConfigManager.getProperty("BASE_URI"))
                .header("Authorization", AuthTokenProvider.getToken(Role.FD))
                .accept(ContentType.JSON)
                .contentType("")
                .log().uri()
                .log().headers()
                .log().method()
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(200)
                .time(Matchers.lessThan(1000L))
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
                baseUri(ConfigManager.getProperty("BASE_URI"))
                .header("Authorization", "")
                .accept(ContentType.JSON)
                .contentType("")
                .log().uri()
                .log().headers()
                .log().method()
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(401);
    }
}
