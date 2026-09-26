package com.api.utils;

import com.api.constants.Role;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class SpecUtil {

    public static RequestSpecification requestSpec() {
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri(ConfigManager.getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        return requestSpecification;
    }

    public static RequestSpecification requestSpec(Object payload) {
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri(ConfigManager.getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBody(payload)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        return requestSpecification;
    }

    public static RequestSpecification requestSpecWithAuth(Role role) {
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri(ConfigManager.getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", AuthTokenProvider.getToken(role))
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        return requestSpecification;
    }

    public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri(ConfigManager.getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", AuthTokenProvider.getToken(role))
                .setBody(payload)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        return requestSpecification;
    }

    public static ResponseSpecification responseSpec_OK() {
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(1000L))
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();
        return responseSpecification;
    }

    public static ResponseSpecification responseSpec_JSON(int statusCode) {
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(1000L))
                .expectStatusCode(statusCode)
                .log(LogDetail.ALL)
                .build();
        return responseSpecification;
    }

    public static ResponseSpecification responseSpec_TEXT(int statusCode) {
        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(Matchers.lessThan(1000L))
                .expectStatusCode(statusCode)
                .log(LogDetail.ALL)
                .build();
        return responseSpecification;
    }
}
