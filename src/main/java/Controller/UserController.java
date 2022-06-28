package Controller;

import Constants.UrlConst;
import Model.User;
import io.qameta.allure.restassured.AllureRestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class UserController {
    public RequestSpecification requestSpec;

    public UserController() {
    	requestSpec = new RequestSpecBuilder()
                .setBaseUri(UrlConst.BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public User[] retrieveUsers (){

        User[] users = given(requestSpec)
                .when()
                .get(UrlConst.USERS_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(User[].class);
        return users;
    }

    public User[] retrieveUsersByUsername (String username){

        User[] users = given(requestSpec)
                .queryParam("username",username)
                .when()
                .get(UrlConst.USERS_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(User[].class);
        return users;
    }
}

