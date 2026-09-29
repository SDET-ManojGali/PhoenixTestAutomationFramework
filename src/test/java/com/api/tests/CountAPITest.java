package com.api.tests;

import static io.restassured.RestAssured.*;
import static com.api.constants.Role.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.utils.SpecUtil.*;
import org.testng.annotations.Test;

public class CountAPITest {
    @Test(description = "Verifying if count api is giving correct response", groups = {"api", "regression", "smoke"})
    public static void verifyCountAPIResponse() {
        given().spec(requestSpecWithAuth(FD))
                .when()
                .get("dashboard/count")
                .then()
                .spec(responseSpec_OK())
                .body("message", equalTo("Success"))
                .body("data.count", everyItem(greaterThanOrEqualTo(0)))
                .body("data.size()", equalTo(3))
                .body("data.label", everyItem(not(blankOrNullString())))
                .body("data.key", containsInAnyOrder("pending_for_delivery", "pending_fst_assignment", "created_today"))
                .body(matchesJsonSchemaInClasspath("responseSchema/CountAPIResponseSchema-FD.json"));
    }

    @Test(description = "Verifying if count api is giving correct status code for invalid token", groups = {"api", "negative", "regression", "smoke"})
    public static void countAPITest_MissingAuthToken() {
        given().spec(requestSpec())
                .when()
                .get("dashboard/count")
                .then()
                .spec(responseSpec_TEXT(401));
    }
}
