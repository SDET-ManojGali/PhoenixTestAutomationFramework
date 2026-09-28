package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.constants.*;
import com.api.request.model.*;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {

    @Test
    public static void createJobAPITest() {
        Customer customer = new Customer("Jarret", "Kemmer", "302-712-6655", "", "Tony_Robel95@hotmail.com", "");
        CustomerAddress customer_address = new CustomerAddress("c 304", "Jupiter", "MG road", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
        CustomerProduct customer_product = new CustomerProduct(getTimeWithDaysAgo(10), "10763902147603", "10763902147603", "10763902147603", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
        Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
        List<Problems> problemsList=new ArrayList<>();
        problemsList.add(problems);
        CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customer_address, customer_product, problemsList);
        given().
                spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
                .when()
                .post("job/create")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("responseSchema/CreateJobAPIResponseSchema.json"))
                .body("message",equalTo("Job created successfully. "))
                .body("data.mst_service_location_id",equalTo(1))
                .body("data.job_number",startsWith("JOB_"));

    }
}
