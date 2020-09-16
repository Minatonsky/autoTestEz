package api.restSteps;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestStepsFlipFlop {
    final private String baseUrl = "http://lipflop";


//auth Registration and authorization

    public RequestSpecification setBaseUrlForAuthorization() {
        RestAssured.baseURI = baseUrl + "/api/auth/login";
        return RestAssured.given();
    }

    public RequestSpecification setBaseUrlForRegistration() {
        RestAssured.baseURI = baseUrl + "/api/auth/register";
        return RestAssured.given();
    }


    public RequestSpecification  setBaseUrlForGetUserInfo(int userId){
        RestAssured.baseURI = baseUrl + "/api/auth/userId";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForLogOutCurrentUser(){
        RestAssured.baseURI = baseUrl + "/api/auth/logout";
        return RestAssured.given();
    }
//    public RequestSpecification  setBaseUrlFor(){
//        RestAssured.baseURI = baseUrl + "";
//        return RestAssured.given();
//    }
//    public RequestSpecification  setBaseUrlFor(){
//        RestAssured.baseURI = baseUrl + "";
//        return RestAssured.given();
//    }
//    public RequestSpecification  setBaseUrlFor(){
//        RestAssured.baseURI = baseUrl + "";
//        return RestAssured.given();
//    }
//    public RequestSpecification  setBaseUrlFor(){
//        RestAssured.baseURI = baseUrl + "";
//        return RestAssured.given();
//    }
//    public RequestSpecification  setBaseUrlFor(){
//        RestAssured.baseURI = baseUrl + "";
//        return RestAssured.given();
//    }
}
