package com.api.tests;

import static io.restassured.RestAssured.*;
import static com.api.constants.Role.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import com.api.utils.SpecUtil;
import org.testng.annotations.Test;

public class CountAPITest {
    @Test
    public static void verifyCountAPIResponse() {
        given().spec(SpecUtil.requestSpecWithAuth(FD))
                .when()
                .get("dashboard/count")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", equalTo("Success"))
                .body("data.count", everyItem(greaterThanOrEqualTo(0)))
                .body("data.size()", equalTo(3))
                .body("data.label",everyItem(not(blankOrNullString())))
                .body("data.key",containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"))
                .body(matchesJsonSchemaInClasspath("responseSchema/CountAPIResponseSchema-FD.json"));
    }

    @Test
    public static void countAPITest_MissingAuthToken(){
        given().spec(SpecUtil.requestSpec())
                .when()
                .get("dashboard/count")
                .then()
                .spec(SpecUtil.responseSpec_TEXT(401));
    }
}
