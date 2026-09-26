package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.constants.Role;
import com.api.pojos.*;
import com.api.utils.SpecUtil;
import org.testng.annotations.Test;

public class CreateJobAPITest {

    @Test
    public static void createJobAPITest() {
        Customer customer = new Customer("Jarret", "Kemmer", "302-712-6655", "", "Tony_Robel95@hotmail.com", "");
        CustomerAddress customer_address = new CustomerAddress("c 304", "Jupiter", "MG road", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
        CustomerProduct customer_product = new CustomerProduct("2025-04-06T18:30:00.000Z", "10763902147694", "10763902147694", "10763902147694", "2025-04-06T18:30:00.000Z", 1, 1);
        Problems problems = new Problems(1, "Battery Issue");
        Problems[] problems1 = {problems};
        CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customer_address, customer_product, problems1);
        given().
                spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
                .when()
                .post("job/create")
                .then()
                .spec(SpecUtil.responseSpec_OK());

    }
}
