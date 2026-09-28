package com.api.tests;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class LoginAPITest {

    @Test
    public void loginAPITest() {
        UserCredentials userCredentials = new UserCredentials("iamfd", "password");
                 given().
                 spec(SpecUtil.requestSpec(userCredentials))
                .when()
                .post("login")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("data.token", notNullValue())
                .body("message", equalTo("Success"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/LoginResponseSchema.json"))
                .extract()
                .response();
    }
}
