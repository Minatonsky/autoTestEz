package api.flipflopTest;

import api.restSteps.MainRestSteps;
import api.restSteps.RestStepsFlipFlop;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static libs.Utils.reedFile;

public class AuthorizationTest {
    RestStepsFlipFlop restStepsFlipFlop = new RestStepsFlipFlop();
    MainRestSteps mainRestSteps = new MainRestSteps();
    JSONObject requestParams = new JSONObject();
    File file = new File("userToken.txt");
    String bearerToken = reedFile("userToken.txt");

    public AuthorizationTest() throws IOException {
    }

    @Test
    public void authorizationTest() throws IOException {
        RequestSpecification request = restStepsFlipFlop.setBaseUrlForAuthorization();
        request.body("elyse.macejkovic74@hotmail.com");
        request.body("testtest");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toMap());

        Response response = request.post();
        Assert.assertEquals(200, response.getStatusCode());
//        String token = mainRestSteps.getValueForKeyFromResponseAsJsonObject(response, "token");
//        System.out.println("token = " + token);
//        createAndWriteStringToFile(file, token);
    }
}
