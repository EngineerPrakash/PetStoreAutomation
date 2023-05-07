package PetStoreAutomation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import PetStoreAutomation.Pet;
import PetStoreAutomation.PetId;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAvailablePetTest extends BaseTest {

    @Test(priority = 1)
    public void postAvailablePet() {
        String requestBody = "{ \"name\": \"Test Pet\", \"status\": \"available\" }";

            given(spec)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("id", notNullValue());

    }

}
