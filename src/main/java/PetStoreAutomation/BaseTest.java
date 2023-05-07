package PetStoreAutomation;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BaseTest {

    protected RequestSpecification spec;
    @BeforeMethod
    public void setUp(){
        spec   = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2").build();

    }

    @DataProvider(name = "petData")
    public Object[][] createPetData() {
        return new Object[][] {
                { "Dog", "available" },
                { "Cat",  "pending" },
                { "Fish", "sold" }
        };
    }
    @Test(dataProvider = "petData")
    protected Response postNewPet(String name, String status) {

        JSONObject body =new JSONObject();
        body.put("name",name);
        body.put("status",status);

        return RestAssured.given(spec).contentType(ContentType.JSON)
                .body(body.toString()).post("/pet");
    }
}
