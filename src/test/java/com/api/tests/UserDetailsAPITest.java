package com.api.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static com.api.utils.ConfigManager.*;

public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest() throws IOException {
        given()
                .baseUri(getProperty("BASE_URI"))
                .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3ODkzNTQ5MDJ9.l_6mGyHYOxQWqcT0zS_kl_HMFxb76xGMHl8ti8MUHwY")
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
                .body(matchesJsonSchemaInClasspath("responseSchema/userDetailsResponseSchema.json"))
                .log()
                .body();

    }
}
