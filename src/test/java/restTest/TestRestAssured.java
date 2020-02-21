package restTest;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import libs.ResponseStructureForRestAssuredTest;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import restSteps.MainRestSteps;

import static libs.Utils.getDateAndTimeFormated;

public class TestRestAssured {
    MainRestSteps mainRestSteps = new MainRestSteps();
    @Test
    @Ignore
    public void getCursDetails() throws ParseException {

        Response response = mainRestSteps.getRequestToPrivatApiAndVerifyStatusCode();
        ResponseBody responseBody = mainRestSteps.getResponseBody(response);

//        System.out.println("Privat Curse = " +responseBody.toString());
//        String testString = responseBody.asString();
//        String [] testString1 = testString.split("\"");

//        for (int i = 0; i < testString1.length; i++) {
//            System.out.println(i + " -> " + testString1 [i]);
//        }
//        System.out.println(" Kust " + testString1[3] + " = " + testString1[11] + " and " + testString1[15]);

//        CurrencyValues[] responseStructure = responseBody.as(CurrencyValues[].class);
//
//        for (CurrencyValues currencyElement : responseStructure ) {
//            System.out.println("Cur " + currencyElement.monthIOSXTariffId + " to " + currencyElement.base_ccy + " has for buy " + currencyElement.buy + " and for sale " + currencyElement.sale);
//        }
    }



    @Test
    public void getWeatherDetails() throws ParseException {
        Response response = mainRestSteps.getRequestAndVerifyStatusCode("/London");
        System.out.println("City received from Response " + mainRestSteps.getValueForKeyFromResponseAsJsonObject(response, "City"));
        ResponseBody responseBody = mainRestSteps.getResponseBody(response);
        ResponseStructureForRestAssuredTest responseStructure = responseBody.as(ResponseStructureForRestAssuredTest.class);
        System.out.println("City from json " + responseStructure.City);
        System.out.println("City from json " + responseStructure.Humidity);
    }

    @Test
    @Ignore
    public void registrationSuccessful() {
        String dateTime = getDateAndTimeFormated();
        RequestSpecification request = mainRestSteps.setBaseUrlForCustomer();

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Virender" + dateTime); // Cast
        requestParams.put("LastName", "Singh" + dateTime);
        requestParams.put("UserName", "sdimpleuser2dd20111" + dateTime);
        requestParams.put("Password", "password");
        requestParams.put("Email", "sample" + dateTime + "@gmail.com");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");
        request.body(requestParams.toMap());
        Response response = request.post("/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        ResponseBody responseBody = response.getBody();
        System.out.println(responseBody);
        System.out.println(response.asString());
        if (response.jsonPath().get("SuccessCode") == null) {
            String failCode = response.jsonPath().get("FaultId");
            Assert.fail(failCode);
        } else {
            Assert.assertEquals("Correct Success code was returned", response.jsonPath().get("SuccessCode"), "OPERATION_SUCCESS");
        }
    }
}
