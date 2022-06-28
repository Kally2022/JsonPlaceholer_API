package Controller;

import Constants.UrlConst;
import Model.Comment;
import Model.Post;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class PostController {
    public RequestSpecification requestSpec;

    public PostController() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(UrlConst.BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();

    }

    public Post[] retrievePosts() {
        Post[] posts = given(requestSpec)
                .when()
                .get(UrlConst.POSTS_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Post[].class);
        return posts;
    }

    public Post[] retrievePostsByUserId(Integer userId) {
        Post[] posts = given(requestSpec)
                .queryParam("userId", userId)
                .when()
                .get(UrlConst.POSTS_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Post[].class);
        return posts;
    }

    public Comment[] retrieveCommentsByPostId(Integer postId) {
        Comment[] comments = given(requestSpec)

                .pathParam("postId", postId)
                .when()
                .get(UrlConst.POSTS_URL + "/{postId}/comments")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Comment[].class);
        return comments;
    }
}

