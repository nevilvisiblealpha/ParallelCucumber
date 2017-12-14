package StepFiles;


import Pages.LoginPage;
import Pages.LoginPage2;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import javax.xml.ws.handler.LogicalHandler;

public class LoginSteps {
    public LoginPage loginPage;
    public LoginPage2 loginpage2;
    public LoginSteps(LoginPage loginPageinit, LoginPage2 loginpage2)
    {
        this.loginPage = loginPageinit;
        this.loginpage2 = loginpage2;
    }

    @Given("^I go to the Login Page$")
    public void i_go_to_the_Login_Page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage.load();

    }




    @Given("^I go to the Login Page of Alpha Exchange$")
    public void i_go_to_the_Login_Page_of_Alpha_Exchange() throws Throwable {
        loginPage.aeLoad();// Write code here that turns the phrase above into concrete actions
        }

    @When("^I enter Username as \"([^\"]*)\"  and Password as \"([^\"]*)\" in Login Page$")
    public void i_enter_Username_as_and_Password_as_in_Login_Page(String userName, String passWord) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
          loginPage.loginWithUserNameAndPassword(userName,passWord);
    }

    @Then("^I should be able to see ErrorMessage as \"([^\"]*)\"$")
    public void i_should_be_able_to_see_ErrorMessage_as(String ErrorMessage) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
loginPage.isErrorMessageDisplayed(ErrorMessage);
    }


    @When("^I enter \"(.*?)\" email address$")
    public void i_enter_email_address(String emailid) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginpage2.EnterEmailAddress(emailid);


    }
    @Then("^verify page is loaded for me$")
    public void verify_page_is_loaded_for_me() throws Throwable {
       //c
        System.out.println("OK tested");
    }


    @Then("^I should be able to see logged in user as \"([^\"]*)\"$")
    public void i_should_be_able_to_see_logged_in_user_as(String Name) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
loginPage.verifyUserNameIsDisplayed(Name);
    }


}
