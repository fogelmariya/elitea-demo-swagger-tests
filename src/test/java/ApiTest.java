import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    static {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
    }

    @Test
    public void testCreateAuthor() {
        String requestBody = "{\"id\":0,\"idBook\":0,\"firstName\":\"string\",\"lastName\":\"string\"}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/api/v1/Authors")
        .then()
                .statusCode(200)
                .body("firstName", equalTo("string"))
                .body("lastName", equalTo("string"));
    }

    @Test
    public void testGetUsers() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/api/v1/Users")
        .then()
                .statusCode(200)
                .body("size()", greaterThan(0)); // Check that the response contains users
    }

    @Test
    public void testGetCoverPhoto() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/api/v1/CoverPhotos/books/covers/2")
        .then()
                .statusCode(200)
                .body("size()", equalTo(1)) // Check that the response contains one cover photo
                .body("[0].idBook", equalTo(2)); // Check that the idBook is 2
    }
}