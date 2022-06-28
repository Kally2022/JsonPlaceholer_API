package Posts;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Controller.PostController;
import Controller.UserController;
import static io.restassured.RestAssured.given;
import Model.Comment;
import Model.Post;
import Model.User;
import static org.assertj.core.api.Assertions.assertThat;


public class PostsTest {
    private static final String POSTS_URL = null;
	public PostController postController;
    public UserController userController;

    @BeforeClass
    public void setup() {

        postController = new PostController();
        userController = new UserController();

    }

    @Test(description = "Retrieve All Posts")
    public void When_Retrieve_Posts_Then_All_Posts_Listed() {

       Post[] posts = postController.retrievePosts();

        assertThat(posts).hasSize(100);
    }


    @Test(description = "Retrieve Posts per Specific User")
    public void Given_Valid_UserId_When_Retrieve_Posts_Then_Posts_Per_User_Listed() {

        String username = "Delphine";

        User[] users= userController.retrieveUsersByUsername(username);
        Integer userId = users[0].getId();
        Post[] posts = postController.retrievePostsByUserId(userId);

        assertThat(posts).hasSize(10);
        assertThat(posts[0].getUserId()).isEqualTo(userId);
        assertThat(posts[0].getId()).isEqualTo(81);
        assertThat(posts[0].getBody()).isEqualTo("facere qui nesciunt est voluptatum voluptatem nisi\nsequi eligendi necessitatibus ea at rerum itaque\nharum non ratione velit laboriosam quis consequuntur\nex officiis minima doloremque voluptas ut aut");
        assertThat(posts[0].getTitle()).isEqualTo("tempora rem veritatis voluptas quo dolores vero");

    }

    @Test(description = "Retrieve Posts per Invalid User")
    public void Given_Invalid_UserId_When_Retrieve_Posts_Then_Empty_Response_Listed() {

        Integer invalidUserId = -1;
        Post[] posts = postController.retrievePostsByUserId(invalidUserId);

        assertThat(posts).hasSize(0);

    }

    @Test(description = "Retrieve Posts Invalid Id")
    public void Given_Invalid_PostId_When_Retrieve_Posts_Then_NotFound_Error_Displayed() {

        Integer invalidPostId = 0;
         given(postController.requestSpec)

                .pathParam("invalidPostId", invalidPostId)
                .when()
                .get(POSTS_URL + "/{invalidPostId}")
                .then()
                .assertThat()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));

    }


    @Test(description = "Retrieve Comments for Specific Post Id")
    public void Given_Valid_PostId_When_Retrieve_Comments_Then_Comments_Per_PostId_Retrieved() {

        Integer postId = 88;
        Comment[] comments = postController.retrieveCommentsByPostId(postId);

        assertThat(comments).hasSize(5);
        assertThat(comments[0].getId()).isEqualTo(436);
        assertThat(comments[0].getPostId()).isEqualTo(postId);
        assertThat(comments[0].getName()).isEqualTo("ea illo ab et maiores eaque non nobis");
        assertThat(comments[0].getEmail()).isEqualTo("Selena.Quigley@johan.co.uk");


    }

}

