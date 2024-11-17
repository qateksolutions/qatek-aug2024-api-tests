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

public class GetTestWithPathVariable {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithPathVariable.class);

    @Test
    public void getSingleUser(){
        LOGGER.info("-------API Test: Get Single User---------");

        //Specify the Base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        String id = "2";
        Response response = httpRequest.request(Method.GET, id);
        LOGGER.debug(response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        String actualEmailId = jsonPath.getString("data.email");

        String emailId = "janet.weaver@reqres.in";
        Assert.assertEquals(actualEmailId, emailId);

        LOGGER.info("----End of Test: Get Single User-------");
    }

    @Test
    public void attemptToGetUserWithInvalidId(){
        LOGGER.info("-------API Test: Attempt to get user's record with invalid id---------");

        //Specify the Base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        String invalidId = "23";
        Response response = httpRequest.request(Method.GET, invalidId);
        LOGGER.debug(response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 404);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get().toString(), "{}");

        LOGGER.info("----End of Test: Attempt to get user's record with invalid id-------");
    }

}
