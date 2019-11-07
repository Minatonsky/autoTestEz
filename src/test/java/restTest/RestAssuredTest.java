package restTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import restSteps.MainRestSteps;

import java.io.File;
import java.io.IOException;


public class RestAssuredTest {
    MainRestSteps mainRestSteps = new MainRestSteps();
    JSONObject requestParams = new JSONObject();
    File file = new File("userToken.txt");
    String stringToken = mainRestSteps.reedFile(file);

    public RestAssuredTest() throws IOException {
    }

    @Test
    public void authorizationTest() throws IOException {
        RequestSpecification request = mainRestSteps.setBaseUrlForAuthorization();
        requestParams.put("email", "minatonski@gmail.com");
        requestParams.put("password", "testtest");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toMap());

        Response response = request.post();
        int statusCode = response.getStatusCode();

        String responseBody = response.getBody().asString();
//        System.out.println("Response Body is =>  " + responseBody);
        Assert.assertEquals(statusCode, 200);

        JSONObject obj2 = new JSONObject(responseBody);
        String token = obj2.getString("token");
        mainRestSteps.createAndWriteStringToFile(file, token);

    }

    @Test
    public void statesTest(){
        RequestSpecification request = mainRestSteps.setBaseUrlForStates();
        requestParams.put("token", stringToken);
        request.header("Content-Type", "application/json");
        request.body(requestParams.toMap());
        Response response = request.get();
        int statusCode = response.getStatusCode();

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        Assert.assertEquals(statusCode, 200);

    }
//    @Test
//    public void


}
