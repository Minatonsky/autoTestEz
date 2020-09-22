package api.restSteps;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;

import static libs.Utils.reedFile;

public class RestStepsFlipFlop {
    final private String baseUrl = "http://lipflop";
    MainRestSteps mainRestSteps = new MainRestSteps();
    File file = new File("userToken.txt");
    String bearerToken = reedFile("userToken.txt");

    public RestStepsFlipFlop() throws IOException {
    }


//    dash

    public RequestSpecification setBaseUrlForDashboard() {
        RestAssured.baseURI = baseUrl + "/api/dash";
        return RestAssured.given();
    }


//auth Registration and authorization

    public RequestSpecification setBaseUrlForAuthorization() {
        RestAssured.baseURI = baseUrl + "/api/auth/login";
        return RestAssured.given();
    }

    public RequestSpecification setBaseUrlForRegistration() {
        RestAssured.baseURI = baseUrl + "/api/auth/register";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForAuthorizationFacebook(){
        RestAssured.baseURI = baseUrl + "/api/auth/facebook";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForAuthorizationApple(){
        RestAssured.baseURI = baseUrl + "/api/auth/apple";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForLogIn(){
        RestAssured.baseURI = baseUrl + "/api/auth/login";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForGetUserInfo(int userId){
        RestAssured.baseURI = baseUrl + "/api/auth/" + userId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForLogOutCurrentUser(){
        RestAssured.baseURI = baseUrl + "/api/auth/logout";
        return RestAssured.given();
    }
    public RequestSpecification getCurrentUserInfo(){
        return mainRestSteps.setBaseUrl(baseUrl,"/api/auth/me", bearerToken);
    }

    public RequestSpecification  updateCurrentUserInfo(String stringToken){
        return mainRestSteps.setBaseUrl(baseUrl,"/api/auth/me", stringToken);
    }


//    video
    public RequestSpecification  setBaseUrlForCreateOrGetVideo(){
        RestAssured.baseURI = baseUrl + "/api/video";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForGetVideoByAudio(int audioId){
        RestAssured.baseURI = baseUrl + "/api/video/audio/" + audioId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForVideoCurrentUser(){
        RestAssured.baseURI = baseUrl + "/api/video/my";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForLikedVideoOfCurrentUser(){
        RestAssured.baseURI = baseUrl + "/api/video/liked";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForVideosUserIsSubscribed(){
        RestAssured.baseURI = baseUrl + "/api/video/subscribed";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForVideosHasSuchTagName(String tagName){
        RestAssured.baseURI = baseUrl + "/api/video/tag/" + tagName + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForVideosOfSpecifiedUser(int userId){
        RestAssured.baseURI = baseUrl + "/api/video/user/" + userId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForFullInformationByOneVideo(int videoId){
        RestAssured.baseURI = baseUrl + "/api/video/" + videoId + "";
        return RestAssured.given();
    }

//    audio
    public RequestSpecification  setBaseUrlForAllAudio(){
        RestAssured.baseURI = baseUrl + "/api/audio";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForCreateAudio(){
        RestAssured.baseURI = baseUrl + "/api/audio";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForAllAudioOfCurrentUser(){
        RestAssured.baseURI = baseUrl + "/api/audio/my";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForAudiosOfSpecifiedUser(int userId){
        RestAssured.baseURI = baseUrl + "/api/audio/user/" + userId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForAllInformationAboutOneAudio(int audioId){
        RestAssured.baseURI = baseUrl + "/api/audio/" + audioId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForUnlinkAudioFromUser(int audioId){
        RestAssured.baseURI = baseUrl + "/api/audio/" + audioId + "/uncall";
        return RestAssured.given();
    }

//    user
    public RequestSpecification  setBaseUrlForAllUsersByRate(){
        RestAssured.baseURI = baseUrl + "/api/user";
        return RestAssured.given();
    }

//    search
    public RequestSpecification  setBaseUrlForSearchAudioInCurrentUsersAudioByNameAndAuthor(){
        RestAssured.baseURI = baseUrl + "/api/search/audio/my";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForSearchAudioInUsersAudiosByNameAndAuthor(int userId){
        RestAssured.baseURI = baseUrl + "/api/search/audio/user/" + userId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForSearchAudioInAllAudiosByNameAndAuthor(){
        RestAssured.baseURI = baseUrl + "/api/search/audio/all";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForSearchVideoInCurrentUsersVideoByDescription(){
        RestAssured.baseURI = baseUrl + "/api/search/video/my";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForSearchVideoInUsersVideosByDescription(){
        RestAssured.baseURI = baseUrl + "/api/search/video/user/{userId}";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForSearchVideoInAllVideosByDescription(){
        RestAssured.baseURI = baseUrl + "/api/search/video/all";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForSearchUserByNameSurnameNickname(){
        RestAssured.baseURI = baseUrl + "/api/search/user";
        return RestAssured.given();
    }

//    like
    public RequestSpecification  setBaseUrlForLikeTheVideo(int videoId){
        RestAssured.baseURI = baseUrl + "/api/video/" + videoId + "/like";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForRemoveLikeOfTheVideo(int videoId){
        RestAssured.baseURI = baseUrl + "/api/video/" + videoId + "/unlike";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForLikeTheComment(int commentId){
        RestAssured.baseURI = baseUrl + "/api/comment/" + commentId + "/like";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForRemoveLikeOfTheComment(int commentId){
        RestAssured.baseURI = baseUrl + "/api/comment/" + commentId + "/unlike";
        return RestAssured.given();
    }

//    subscribe

    public RequestSpecification  setBaseUrlForSubscribeOnUserWithSuchUserId(int userId){
        RestAssured.baseURI = baseUrl + "/api/user/" + userId + "/subscribe";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForRemoveSubscriptionOnUserWithSuchUserId(int userId){
        RestAssured.baseURI = baseUrl + "/api/user/" + userId + "/unsubscribe";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForUsersWhoFollowedTheSpecifiedUserByDateOfSubscription(int userId){
        RestAssured.baseURI = baseUrl + "/api/user/" + userId + "/subscribers";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForUsersOnWhichTheSpecifiedUserIsSubscribedToByDateOfSubscription(int userId){
        RestAssured.baseURI = baseUrl + "/api/user/" + userId + "/subscribed";
        return RestAssured.given();
    }

//    comment

    public RequestSpecification  setBaseUrlForAllCommentsAboutTheSpecifiedVideoByDate(int videoId){
        RestAssured.baseURI = baseUrl + "/api/video/" + videoId + "/comments";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForMakeCommentToVideo(int videoId){
        RestAssured.baseURI = baseUrl + "/api/video/" + videoId + "/comment";
        return RestAssured.given();
    }

//    destroy

    public RequestSpecification  setBaseUrlForDestroyUserAndAllHerInformation(int userId){
        RestAssured.baseURI = baseUrl + "/api/auth/" + userId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForDestroyVideoAndAllRelatedData(int videoId){
        RestAssured.baseURI = baseUrl + "/api/video/" + videoId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForDestroyAudioAndAllRelatedData(int audioId){
        RestAssured.baseURI = baseUrl + "/api/audio/" + audioId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForDestroyCommentAndAllRelatedData(int commentId){
        RestAssured.baseURI = baseUrl + "/api/comment/" + commentId + "";
        return RestAssured.given();
    }

//    media

    public RequestSpecification  setBaseUrlForViewVideoFile(int videoId){
        RestAssured.baseURI = baseUrl + "/video/" + videoId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForViewVideoThumbGif(int videoId){
        RestAssured.baseURI = baseUrl + "/thumb/" + videoId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForListenAudioMusic(int audioId){
        RestAssured.baseURI = baseUrl + "/audio/" + audioId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForViewAudioCoverImage(int audioId){
        RestAssured.baseURI = baseUrl + "/cover/" + audioId + "";
        return RestAssured.given();
    }
    public RequestSpecification  setBaseUrlForViewUserAvatarImage(int userId){
        RestAssured.baseURI = baseUrl + "/avatar/" + userId + "";
        return RestAssured.given();
    }
}
