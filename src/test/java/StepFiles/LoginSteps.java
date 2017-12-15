package StepFiles;


import Pages.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
    public LoginPage loginPage;
    public LoginSteps(LoginPage loginPageinit)
    {
        this.loginPage = loginPageinit;
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






    @Then("^I should be able to see logged in user as \"([^\"]*)\"$")
    public void i_should_be_able_to_see_logged_in_user_as(String Name) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
loginPage.verifyUserNameIsDisplayed(Name);
    }


}
