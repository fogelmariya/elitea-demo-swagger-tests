import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    static {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
    }

    @Test
    public void testCreateAuthor() {
        String requestBody = "{\"id\":0,\"idBook\":0,\"firstName\":\"string\",\"lastName\":\"string\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/v1/Authors")
                .then()
                .statusCode(200)
                .extract().response();

        // Optionally, you can validate the response body
        response.then().body("firstName", equalTo("string"));
    }

    @Test
    public void testGetUsers() {
        Response response = given()
                .when()
                .get("/api/v1/Users")
                .then()
                .statusCode(200)
                .extract().response();

        // Optionally, you can validate the response body
        response.then().body("size()", equalTo(10)); // Assuming there are 10 users
    }

    @Test
    public void testGetCoverPhoto() {
        Response response = given()
                .when()
                .get("/api/v1/CoverPhotos/books/covers/2")
                .then()
                .statusCode(200)
                .extract().response();

        // Optionally, you can validate the response body
        response.then().body("id", equalTo(2));
    }
}