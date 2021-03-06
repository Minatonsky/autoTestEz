package api.restSteps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import javax.json.*;
import java.io.IOException;
import java.io.StringReader;

public class MainRestSteps {

    final private String baseUrl = "https://dev.api.ezlogz.com";
    final private String baseUrlFlipFlop = "http://lipflop";



    public void checkResponseCode(Response response, int expectedStatusCode) {
        // Get the status code from the Response. In case of
        // a successfull interaction with the web service, we
        // should get a status code of 200.
        int statusCode = response.getStatusCode();
        // Assert that correct status code is returned.
        Assert.assertEquals("Correct status code returned", statusCode /*actual value*/, expectedStatusCode /*expected value*/);
    }

    public RequestSpecification setBaseUrlWithToken(String baseUrl, String addToUrl, String stringToken) {
        RestAssured.baseURI = baseUrl + addToUrl;
        return RestAssured.given().accept("application/json").contentType("application/json").header("Authorization", "Bearer "+ stringToken);
    }

    public RequestSpecification setBaseUrl(String baseUrl, String addToUrl) {
        RestAssured.baseURI = baseUrl + addToUrl;
        return RestAssured.given().accept("application/json").contentType("application/json");
    }

    public ResponseBody getResponseBody(Response response) throws ParseException {
        // Now let us print the body of the message to see what response
        // we have recieved from the server
        ResponseBody responseBody = response.getBody();
        System.out.println("Response Body is =>  " + responseBody.asString());
        return responseBody;
    }

    public String getValueForKeyFromResponseAsJsonObject(Response response, String key) {
        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        String value = jsonPathEvaluator.get(key);
        if (value == null) {
            Assert.fail("There is no key '" + key + "' in Json response ");
        }
        return value;
    }

    public String prettyPrintJson(JsonObject jsonObject, int indent) {

        String indentStr = getIndentStr(indent);
        String prettyJson = indentStr + "{";

        for (String key : jsonObject.keySet()) {
            prettyJson += "\n";
            prettyJson += indentStr + "  \"" + key + "\": ";
            try {
                JsonArray jsonArray = jsonObject.get(key).asJsonArray();
                prettyJson += "\n" + indentStr + "  [";
                for (JsonValue element : jsonArray) {
                    prettyJson += "\n" + prettyPrintJson(element.asJsonObject(), indent + 4);
                    prettyJson += ",";
                }
                prettyJson = prettyJson.substring(0, prettyJson.length() - 1);
                prettyJson += "\n" + indentStr + "  ]";
            } catch (Exception e) {
                try {
                    prettyJson += "\n" + prettyPrintJson(jsonObject.get(key).asJsonObject(), indent + 2);
                } catch (Exception ee) {
                    prettyJson += jsonObject.get(key).toString();
                }
            }
            prettyJson += ",";
        }
        prettyJson = prettyJson.substring(0, prettyJson.length() - 1);
        prettyJson += "\n" + indentStr + "}";
        return prettyJson;
    }

    public String getIndentStr(int indent) {
        String indentStr = "";
        for (int i = 0; i < indent; i++) {
            indentStr += " ";
        }
        return indentStr;
    }
    public void printJson(String responseString) throws IOException {
        JsonReader reader  =  Json.createReader(new StringReader(responseString));
        JsonObject jsonObject = reader.readObject();
        System.out.println(prettyPrintJson(jsonObject, 0));
    }


    ///////////////
    public RequestSpecification setBaseUrlForAuthorization() {
        RestAssured.baseURI = baseUrl + "/api/login";
        return RestAssured.given();
    }
    public RequestSpecification setBaseUrlForRegistration() {
        RestAssured.baseURI = baseUrl + "/api/registration";
        return RestAssured.given();
    }
    public RequestSpecification setBaseUrlForDevEzlogzApi(String addToUrl, String stringToken) {
        RestAssured.baseURI = baseUrl + addToUrl;
        return RestAssured.given().accept("application/json").contentType("application/json").header("Authorization", "Bearer "+ stringToken);
    }


}
