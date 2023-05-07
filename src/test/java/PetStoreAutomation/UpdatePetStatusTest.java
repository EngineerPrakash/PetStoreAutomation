package PetStoreAutomation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdatePetStatusTest extends BaseTest{
    @Test(priority = 1)
    public void updatePetStatus(){

        Response responseCreate = postNewPet("Dog","available");
        responseCreate.print();

        int petid = responseCreate.jsonPath().getInt("id");
        System.out.println(petid);

        JSONObject body = new JSONObject();
        body.put("id",petid);
        body.put("status", "sold");

        Response responseUpdate = RestAssured.given(spec).contentType(ContentType.JSON)
                .body(body.toString()).put("/pet");
        responseUpdate.print();

        Assert.assertEquals(responseUpdate.statusCode(), 200, "Response code is not correct");

        SoftAssert softAssert = new SoftAssert();

        int actualId = responseUpdate.jsonPath().getInt("id");
        String actualStatus=responseUpdate.jsonPath().getString("status");

        Assert.assertEquals(actualId, petid, "petId does not matches");

        Assert.assertEquals(actualStatus, "sold", "status is not matching");

    }

}
