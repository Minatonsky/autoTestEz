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

import static libs.Utils.*;


public class EzlogzAPITest {
    MainRestSteps mainRestSteps = new MainRestSteps();
    JSONObject requestParams = new JSONObject();
    File file = new File("userToken.txt");
    String bearerToken = reedFile("userToken.txt");

    public EzlogzAPITest() throws IOException {
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
        createAndWriteStringToFile(file, token);
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
    @Test
    public void cardsTest() throws ParseException, IOException {
        RequestSpecification request = mainRestSteps.setBaseUrlForDevEzlogzApi("/api/cards/", bearerToken);
        Response response = request.get();
        mainRestSteps.getResponseBody(response);
        Assert.assertEquals(200, response.getStatusCode());
    }
    @Test
    public void registrationTest() throws IOException, ParseException {
        String dateTime = getDateAndTimeFormated();
        RequestSpecification request = mainRestSteps.setBaseUrlForRegistration();
        requestParams.put("type", 7).put("email", "test" + dateTime + "@gmail.com").put("first_name", "testregistration").put("last_name", "test").put("phone", "0676475006")
                .put("password", "testtest").put("password_confirmation", "testtest").put("terms", 1);
        request.header("Content-Type", "application/json").header("Accept", "application/json");
        request.body(requestParams.toMap());
        Response response = request.post();
        mainRestSteps.getResponseBody(response);
        Assert.assertEquals(201, response.getStatusCode());
    }
    @Test
    public void driverLicenseTest() throws IOException, ParseException {
        RequestSpecification request = mainRestSteps.setBaseUrlForDevEzlogzApi("/api/validations/driver-license", bearerToken);
        requestParams.put("country", "USA");
        requestParams.put("state", "AL");
        requestParams.put("license", "4555555");
//        request.header("Content-Type", "application/json");
        request.body(requestParams.toMap());
        Response response = request.post();
        mainRestSteps.getResponseBody(response);
        Assert.assertEquals(200, response.getStatusCode());
    }

}
