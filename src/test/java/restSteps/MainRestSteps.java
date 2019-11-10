package restSteps;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import javax.json.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainRestSteps {

    final private String baseTestUrl = "http://restapi.demoqa.com";
    final private String baseUrlPrivat = "http://api.privatbank.ua";
    final private String baseUrl = "https://dev.api.ezlogz.com";

    public void createAndWriteStringToFile(File file, String data) throws IOException {
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.flush();
        writer.close();
    }
    public String reedFile(String bearerToken) throws IOException {
        String content;
        content = new String(Files.readAllBytes(Paths.get(bearerToken)));
        return content;
    }
    public RequestSpecification setBaseUrlForAuthorization() {
        RestAssured.baseURI = baseUrl + "/api/login";
        return RestAssured.given();
    }
    public Response getRequestAndVerifyStatusCode(String addToUrl) {
        Response response = getRequest(addToUrl);
        checkResponseCode(response, 200);
        return response;
    }
    public void checkResponseCode(Response response, int expectedStatusCode) {
        // Get the status code from the Response. In case of
        // a successfull interaction with the web service, we
        // should get a status code of 200.
        int statusCode = response.getStatusCode();
        // Assert that correct status code is returned.
        Assert.assertEquals("Correct status code returned", statusCode /*actual value*/, expectedStatusCode /*expected value*/);
    }
    public RequestSpecification setBaseUrlForDevEzlogzApi(String addToUrl, String stringToken) {
        RestAssured.baseURI = baseUrl + addToUrl;
        return RestAssured.given().accept("application/json").contentType("application/json").header("Authorization", "Bearer "+ stringToken);
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









///////////////////////////////////////////////////////////////////////////////
    public RequestSpecification setBaseUrlForDemoQaWetherCity() {
       // Specify the base URL to the RESTful web service
        RestAssured.baseURI = baseTestUrl + "/utilities/weather/city";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        return RestAssured.given();
    }
    public RequestSpecification setBaseUrlForPrivatApi() {
         RestAssured.baseURI = baseUrlPrivat + "/p24api/pubinfo";
        return RestAssured.given().queryParam("json").queryParam("exchange").queryParam("coursid", 5);
    }
    public RequestSpecification setBaseUrlForCustomer() {
        RestAssured.baseURI = baseTestUrl + "/api/customer";
        return RestAssured.given();
    }
    public Response getRequest(String addToUrl) {
        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        return setBaseUrlForDemoQaWetherCity().request(Method.GET, addToUrl);
    }
    public Response getRequestToPrivatApi() {
        return setBaseUrlForPrivatApi().request(Method.GET);
    }
    public Response getRequestToPrivatApiAndVerifyStatusCode() {
        Response response = getRequestToPrivatApi();
        checkResponseCode(response, 200);
        return response;
    }





}
