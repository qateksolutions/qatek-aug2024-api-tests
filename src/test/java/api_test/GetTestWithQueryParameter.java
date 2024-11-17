package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetTestWithQueryParameter {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithQueryParameter.class);

    @Test
    public void getUsersWithQueryParameter(){
        LOGGER.info("-------API Test: Get All Users with Query Parameters---------");

        //Specify the Base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.queryParam("page", "2").request(Method.GET);
        LOGGER.debug(response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.get("data.email");

        String emailId = "michael.lawson@reqres.in";
        boolean emailExist = list.contains(emailId);

        Assert.assertTrue(emailExist, emailId + " does not exist");

        LOGGER.info("----End of Test: Get All Users with Query Parameters-------");
    }
}
