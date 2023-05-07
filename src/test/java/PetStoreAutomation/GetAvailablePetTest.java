package PetStoreAutomation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import PetStoreAutomation.Pet;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

public class GetAvailablePetTest extends BaseTest {

        @Test(priority = 1)
        public void getAvailablePet() {
                Response response = RestAssured.given(spec).get("/pet/findByStatus?status=available");
                // Send a GET request to the /pet/findByStatus endpoint with the status parameter set to available
                given(spec)
                        .basePath("/pet/findByStatus")
                        .queryParam("status", "available")
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .body("status", everyItem(equalTo("available")));
                response.print();

        }

}
