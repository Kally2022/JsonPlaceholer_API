package Users;

import static org.assertj.core.api.Assertions.assertThat;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Controller.UserController;
import Model.User;

public class UserTest {
    private UserController userController;

    @BeforeClass
    public void setup() {
        this.userController = new UserController();

    }

    @Test(description = "Retrieve All Users")
    public void When_Retrieve_Users_Then_Users_Listed() {
        User[] users = userController.retrieveUsers();

        assertThat(users).hasSize(10);

    }

    @Test(description = "Retrieve Invalid User")
    public void Given_Invalid_Username_When_Retrieve_Users_Then_Empty_Response_Received() {

        String username = "XZX";// Invalid userdetails

        User[] users = userController.retrieveUsersByUsername(username);

        assertThat(users).hasSize(0);
    }

    @Test(description = "Retrieve Specific User")
    public void Given_Valid_Username_When_Retrieve_Users_Then_Valid_User_Listed() {

        String username = "Delphine";
        User[] users = userController.retrieveUsersByUsername(username);

        assertThat(users).hasSize(1);
        User user = users[0];
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getId()).isEqualTo(9);
        assertThat(user.getName()).isEqualTo("Glenna Reichert");
        assertThat(user.getEmail()).isEqualTo("Chaim_McDermott@dana.io"); 
        assertThat(user.getAddress().getStreet()).isEqualTo("Dayna Park");
        assertThat(user.getAddress().getSuite()).isEqualTo("Suite 449");
        assertThat(user.getAddress().getZipcode()).isEqualTo("76495-3109");
        assertThat(user.getPhone()).isEqualTo("(775)976-6794 x41206");
        assertThat(user.getWebsite()).isEqualTo("conrad.com");
        assertThat(user.getCompany().getName()).isEqualTo("Yost and Sons");
        assertThat(user.getCompany().getBs()).isEqualTo("aggregate real-time technologies");
        assertThat(user.getCompany().getCatchPhrase()).isEqualTo("Switchable contextually-based project");

    }

}

