package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

import static java.lang.System.*;

public class LoginPage extends
        BasePage{
    public WebDriver driver;
    public LoginPage(SharedDriver sharedDriver) {
        super( sharedDriver.getDriver());
        this.driver = sharedDriver.getDriver();
      // PageFactory.initElements(driver, this);
    }

    By emailTextBox= By.id("user_email");
    By loggedInUserName(String usersName) {
        return By.xpath(String.format("//a[@class='profile dropdown-toggle']//span[@class='visible-lg-inline'][text()='%s']", usersName));
    }
    By passWordTextBox = By.id("user_password");
    By submitButton =By.xpath("//input[@type='submit']");
    By signupLink = By.partialLinkText("Sign up");
    By userFirstNameTextBox =By.id("user_first_name");
    By userLastNameTextBox =By.id("user_last_name");
    By userEmailTextBox = By.id("user_email");
    By user_phone_numberTextBox = By.id("user_phone_number");
    By user_job_titleTextBox = By.id("user_job_title");
    By entity_typeTextBox = By.id("entity_type");
    By search_companyDropdown = By.id("select2-search_company-container");
    //  By search_companyDropdown = By.id("search_company");
    By user_password_Textbox = By.id("user_password");
    By user_password_confirmation = By.id("user_password_confirmation");
    By signUpButton = By.xpath("//input[@value='Sign up']");
    By CompanyAutosuggestTextBox        = By.xpath("//input[@class='select2-search__field']");
    By AutosuggestDropdownArea(String Value){
        return By.xpath("//ul[@id='select2-search_company-results']/li[text()='"+Value+"']");
    };
     By MessagePopUP = By.xpath("//div[@role='alert']");



    @Given("^I go to the Login Page of Alpha Exchange$")
    public void i_go_to_the_Login_Page_of_Alpha_Exchange() throws Throwable {
        String URL = getenv("URL");
        if(URL!=null)
        {
            driver.get(URL);

        }else{
            driver.get("https://braai-dev.alpha-exchange.com/users/sign_in");
        }
    }

    @When("^I enter Username as \"([^\"]*)\"  and Password as \"([^\"]*)\" in Login Page$")
    public void i_enter_Username_as_and_Password_as_in_Login_Page(String emailAddress, String Password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Enter(emailTextBox,emailAddress).Enter(passWordTextBox,Password).ClickOn(submitButton);
    }

    @When("^I try to signup with \"([^\"]*)\" as firstname and \"([^\"]*)\" as lastname$")
    public void i_try_to_signup_with_as_firstname_and_as_lastname(String FirstName, String Lastname) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Random rand = new Random(System.currentTimeMillis());
        int value=rand.nextInt(4000);
        ClickOn(signupLink).
                Enter(userFirstNameTextBox,"Automation").
                Enter(userLastNameTextBox,"User").

                Enter(emailTextBox,""+FirstName+"."+Lastname+value+"@mailinator.com").
                SelectFromDropDown(entity_typeTextBox,"Buy Side").
                SelectFromAutoSuggest(search_companyDropdown,CompanyAutosuggestTextBox,AutosuggestDropdownArea("apple"),"apple").
                Enter(user_job_titleTextBox,"Analyst").
                Enter(passWordTextBox,"Password123").
                Enter(user_password_confirmation,"Password123").
                ClickOn(signUpButton);
        ;

    }


    @Then("^I should be able to see Message as \"([^\"]*)\"$")
    public void i_should_be_able_to_see_Message_as(String ErrorMessage) throws Throwable {
        String ErrorMessageFromPage =   getText(MessagePopUP);
        Assert.assertTrue(ErrorMessageFromPage.contains(ErrorMessage));
    }






    @Then("^I should be able to see logged in user as \"([^\"]*)\"$")
    public void i_should_be_able_to_see_logged_in_user_as(String Name) throws Throwable {
        boolean isDisplayed = isDisplayed(loggedInUserName(Name),10);
        Assert.assertTrue(isDisplayed);
    }


    @Given("^I login as buyer side user$")
    public void i_login_as_buyer_side_user() throws Throwable {
        i_go_to_the_Login_Page_of_Alpha_Exchange();
        i_enter_Username_as_and_Password_as_in_Login_Page("scott+345@alpha-exchange.com","%\\;7CX!]");

    }


}
