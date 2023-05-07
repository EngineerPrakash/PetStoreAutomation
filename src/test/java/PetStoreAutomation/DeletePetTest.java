package PetStoreAutomation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePetTest extends BaseTest{

    @Test(priority = 1)
    public void deletePetFromStore(){

        Response responseCreate = postNewPet("fish","sold");
        responseCreate.print();

        long petid = responseCreate.jsonPath().getLong("id");
        System.out.println(petid);

        Response responseDelete = RestAssured.given(spec).contentType(ContentType.JSON)
                .delete("/pet/"+petid);
        responseDelete.print();

        Assert.assertEquals(responseDelete.statusCode(), 200, "Response code is not correct");

        Response response=RestAssured.given(spec).contentType(ContentType.JSON).get("/pet/"+petid);
        response.print();

        Assert.assertEquals(response.statusCode(),404,"petid was not deleted successfully");
    }
}
