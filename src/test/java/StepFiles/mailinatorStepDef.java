package StepFiles;


import Pages.LoginPage;
import Pages.LoginPage2;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import javax.xml.ws.handler.LogicalHandler;

public class mailinatorStepDef {
    public LoginPage loginPage;
    public LoginPage2 loginpage2;
    public mailinatorStepDef(LoginPage loginPageinit, LoginPage2 loginpage2)
    {
        this.loginPage = loginPageinit;
        this.loginpage2 = loginpage2;
    }

    @Given("^I go to the Login Page$")
    public void i_go_to_the_Login_Page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage.load();

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


}
