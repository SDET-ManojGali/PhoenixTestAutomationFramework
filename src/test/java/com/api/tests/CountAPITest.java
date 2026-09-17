package com.api.tests;

import static io.restassured.RestAssured.*;
import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CountAPITest {
    @Test
    public static void verifyCountAPIResponse() {
        given().baseUri(ConfigManager.getProperty("BASE_URI"))
                .header("Authorization", getToken(FD))
                .accept(ContentType.JSON)
                .log().uri()
                .log().method()
                .log().headers()
                .when()
                .get("dashboard/count")
                .then()
                .statusCode(200)
                .log()
                .all()
                .time(lessThan(1000L))
                .body("message", equalTo("Success"))
                .body("data.count", everyItem(greaterThanOrEqualTo(0)))
                .body("data.size()", equalTo(3))
                .body("data.label",everyItem(not(blankOrNullString())))
                .body("data.key",containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"))
                .body(matchesJsonSchemaInClasspath("responseSchema/CountAPIResponseSchema-FD.json"));
    }

    @Test
    public static void countAPITest_MissingAuthToken(){
        given().baseUri(ConfigManager.getProperty("BASE_URI"))
                .accept(ContentType.JSON)
                .log().uri()
                .log().method()
                .log().headers()
                .when()
                .get("dashboard/count")
                .then()
                .statusCode(401);
    }
}
