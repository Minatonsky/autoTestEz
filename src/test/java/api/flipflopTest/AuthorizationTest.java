package api.flipflopTest;

import api.restSteps.MainRestSteps;
import api.restSteps.RestStepsFlipFlop;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static libs.Utils.createAndWriteStringToFile;
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
    public void authorizationTest() throws ParseException, IOException {
        RequestSpecification request = restStepsFlipFlop.setBaseUrlForAuthorization();
        requestParams.put("email", "elyse.macejkovic74@hotmail.com");
        requestParams.put("password", "testtest");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toMap());
        Response response = request.post();
        mainRestSteps.getResponseBody(response);
        mainRestSteps.checkResponseCode(response, 200);
        String token = mainRestSteps.getValueForKeyFromResponseAsJsonObject(response, "data.access_token");
        System.out.println("token = " + token);
        createAndWriteStringToFile(file, token);

    }
    @Test
    public void infoCurrentUser() throws ParseException {
        RequestSpecification request = restStepsFlipFlop.getCurrentUserInfo();
        Response response = request.get();
        mainRestSteps.getResponseBody(response);
        mainRestSteps.checkResponseCode(response, 200);
    }
}
