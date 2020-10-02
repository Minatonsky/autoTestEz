package api.flipflopTest;

import api.restSteps.MainRestSteps;
import api.restSteps.RestStepsFlipFlop;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static libs.Utils.createAndWriteStringToFile;
import static libs.Utils.reedFile;

public class FlipFlopAllTest {
    RestStepsFlipFlop restStepsFlipFlop = new RestStepsFlipFlop();
    MainRestSteps mainRestSteps = new MainRestSteps();
    JSONObject requestParams = new JSONObject();
    File file = new File("userToken.txt");
    String bearerToken = reedFile("userToken.txt");

    RequestSpecification request;
    Response response;
    protected Faker faker = new Faker();

    public FlipFlopAllTest() throws IOException {
    }

    @Test
    public void registrationTest() throws ParseException, IOException {
        request = restStepsFlipFlop.setBaseUrlForRegistration();
        requestParams.put("name", faker.name().firstName());
        requestParams.put("website", "https://" + faker.internet().domainName());
        requestParams.put("email", faker.internet().emailAddress());
        requestParams.put("password", "testtest");
        requestParams.put("password_confirmation", "testtest");
        requestParams.put("nickname", faker.beer().name().replaceAll(" ", ""));
        requestParams.put("phone_number", faker.phoneNumber().subscriberNumber(10));
        request.body(requestParams.toMap());
        Response response = request.post();
        mainRestSteps.getResponseBody(response);
        mainRestSteps.checkResponseCode(response, 200);
        String token = mainRestSteps.getValueForKeyFromResponseAsJsonObject(response, "data.access_token");
        createAndWriteStringToFile(file, token);

    }

    @Test
    public void authorizationTest() throws ParseException, IOException {
        request = restStepsFlipFlop.setBaseUrlForAuthorization();
        requestParams.put("email", "ezra57@gmail.com");
        requestParams.put("password", "testtest");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toMap());
        Response response = request.post();
        mainRestSteps.getResponseBody(response);
        mainRestSteps.checkResponseCode(response, 200);
        String token = mainRestSteps.getValueForKeyFromResponseAsJsonObject(response, "data.access_token");
        createAndWriteStringToFile(file, token);

    }
    @Test
    public void infoCurrentUser() throws ParseException {
        request = restStepsFlipFlop.setBaseUrlForGetCurrentUserInfo();
        response = request.get();
        mainRestSteps.getResponseBody(response);
        mainRestSteps.checkResponseCode(response, 200);
    }
}
