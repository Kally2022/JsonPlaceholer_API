package Comments;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Controller.CommentController;
import Controller.PostController;
import Controller.UserController;
import Model.Comment;

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
import Util.EmailUtil;

import static org.assertj.core.api.Assertions.assertThat;


public class CommentsTest {
	
    public UserController userController;
    public PostController postController;
    public CommentController commentController;


    @BeforeClass
    public void setup() {

        userController = new UserController();
        commentController = new CommentController();
        postController = new PostController();
    }


    @Test(description = "Retrieve Comments")
    public void When_Retrieve_Commands_Then_All_Comments_Listed() {

        Comment[] comments = commentController.retrieveComments();

        assertThat(comments).hasSize(500);

    }

    @Test(description = "Retrieve Comments With Specific PostId")
    public void Given_PostId_When_Retrieve_Comments_Then_Comments_PerPost_Listed() {

        String username = "Delphine";

        User[] users = userController.retrieveUsersByUsername(username);
        Integer userId = users[0].getId();

        Post[] posts = postController.retrievePostsByUserId(userId);

        for(int i=0; i< posts.length; i++){
            Integer postId = posts[i].getId();
            Comment[] comments = commentController.retrieveCommentsByPostId(postId);
            assertThat(comments).hasSize(5);


            for(int j=0;j<comments.length; j++){
                assertThat(EmailUtil.isValidEmail(comments[j].getEmail())).isTrue();
                assertThat(comments[j].getPostId()).isEqualTo(postId);

            }

        }


    }
   }
