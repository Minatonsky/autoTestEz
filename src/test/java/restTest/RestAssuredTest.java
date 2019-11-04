package restTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;



public class RestAssuredTest {

    @Test
    public void restTest(){
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET,"/Hyderabad");

        System.out.println("Body " + response.getBody().asString());

    }
}
