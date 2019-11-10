package restTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import restSteps.MainRestSteps;

import java.io.File;
import java.io.IOException;


public class RestAssuredTest {
    MainRestSteps mainRestSteps = new MainRestSteps();
    JSONObject requestParams = new JSONObject();
    File file = new File("userToken.txt");
    String bearerToken = mainRestSteps.reedFile("userToken.txt");

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
        Assert.assertEquals(200, response.getStatusCode());
        String token = mainRestSteps.getValueForKeyFromResponseAsJsonObject(response, "token");
        System.out.println("token = " + token);
        mainRestSteps.createAndWriteStringToFile(file, token);
    }
    @Test
    public void stateTest() throws ParseException {
        RequestSpecification request = mainRestSteps.setBaseUrlForDevEzlogzApi("/api/dictionaries/states", bearerToken);
        Response response = request.get();
        mainRestSteps.getResponseBody(response);
        Assert.assertEquals(200, response.getStatusCode());
    }
    @Test
    public void settingsTest() throws ParseException, IOException {

        RequestSpecification request = mainRestSteps.setBaseUrlForDevEzlogzApi("/api/settings", bearerToken);
        Response response = request.get();
        mainRestSteps.getResponseBody(response);
        Assert.assertEquals(200, response.getStatusCode());
//        System.out.println("Received from Response: " + mainRestSteps.getValueForKeyFromResponseAsJsonObject(response, "user.phone"));
//        mainRestSteps.printJson(response.asString());
    }
}
