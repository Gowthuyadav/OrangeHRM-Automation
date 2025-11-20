package testcases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITestOrangeHRM {

    private String baseUri = "https://opensource-demo.orangehrmlive.com/api/v1";
    private String empId; // to store employee ID for edit/delete

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUri;
    }

@Test(priority = 1)
public void addEmployeeTest() {
    String payload = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"middleName\": \"M\" }";

    Response resp = given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/employees")
        .then()
            .statusCode(201)
            .body("firstName", equalTo("John"))
            .extract().response();

    empId = resp.jsonPath().getString("employeeId");
    System.out.println("Created Employee ID: " + empId);
}

@Test(priority = 2, dependsOnMethods = "addEmployeeTest")
public void editEmployeeTest() {
    String payload = "{ \"firstName\": \"JohnUpdated\", \"lastName\": \"Doe\" }";

    given()
        .contentType(ContentType.JSON)
        .body(payload)
    .when()
        .put("/employees/" + empId)
    .then()
        .statusCode(200)
        .body("firstName", equalTo("JohnUpdated"));
}
@Test(priority = 3, dependsOnMethods = "addEmployeeTest")
public void searchEmployeeTest() {
    given()
    .when()
        .get("/employees/" + empId)
    .then()
        .statusCode(200)
        .body("employeeId", equalTo(empId));
}
@Test(priority = 4, dependsOnMethods = "addEmployeeTest")
public void deleteEmployeeTest() {
    given()
    .when()
        .delete("/employees/" + empId)
    .then()
        .statusCode(204);
}
}

