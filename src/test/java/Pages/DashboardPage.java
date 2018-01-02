package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Timestamp;


public class DashboardPage extends BasePage {
    public WebDriver driver;
    int commmentCount;
   static String commentValue;
    public DashboardPage(SharedDriver sharedDriver) {
        super( sharedDriver.getDriver());
        this.driver = sharedDriver.getDriver();

    }

    By FeedTab = By.xpath("//ul[@class='nav navbar-nav menu']/li/a[text()='Feed']");
    By ExploreTab = By.xpath("//ul[@class='nav navbar-nav menu']/li/a[text()='Explore']");
    By DashBoardTab(String Value) {return By.xpath("//ul[@class='nav navbar-nav menu']/li/a[text()='"+Value+"']");}
    By feedPosts = By.xpath("//div[contains(@id,'research_report')]");
    By savedSearch(String searchValue)  { return By.xpath("//div[@id='saved-searches-dashboard-list']//p[text()='"+searchValue+"']"); }
    By searchTextBox = By.id("search-text");
    By MultiSelectOptionHeader(String HeaderName)  {return By.xpath("//label[text()='"+HeaderName+"']");}
    By multiSelectCheckbox(String HeaderName,String Option){return By.xpath("//label[text()='"+HeaderName+"']/ancestor::div[@class='row div-link']/following-sibling::div[@class='row collapse in']//label[normalize-space()='"+Option+"']/input");}
    By FeedName = By.xpath("//a[@class='research-report-link']");
    By CommentLink = By.xpath("//a[@aria-controls=\"collapse-comments\"]/span");
    By CommentTextBox = By.id("comment_message");
    By ResearchReportLink =By.className("research-report-link");




    @Then("^verify user landed on Dashboard Page$")
    public void verify_user_landed_on_Dashboard_Page() throws Throwable {
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Then("^verify feed post loaded inside the feed tab$")
    public void verify_feed_post_loaded_inside_the_feed_tab() throws Throwable {

        ClickOn(FeedTab);
        Assert.assertFalse(getWebElements(feedPosts).isEmpty());

    }
    @Then("^verify feed post loaded inside the Explore tab$")
    public void verify_feed_post_loaded_inside_the_Explore_tab() throws Throwable {
        ClickOn(ExploreTab);
        Assert.assertFalse(getWebElements(feedPosts).isEmpty());
    }

    @When("^I click \"([^\"]*)\" from Saved Search list$")
    public void i_click_from_Saved_Search_list(String searchValue) throws Throwable {
        ClickOn(savedSearch(searchValue));
    }

    @Then("^I should direct to the \"([^\"]*)\" tab$")
    public void i_should_direct_to_the_tab(String tabName) throws Throwable {
        Assert.assertTrue(isAttributeValuePresent( DashBoardTab(tabName),"class","active"));
    }

    @Then("^I should see \"([^\"]*)\" in search textbox$")
    public void i_should_see_in_search_textbox(String value) throws Throwable {
       Assert.assertTrue(isAttributeValuePresent(searchTextBox,"Value",value));
    }


    @Then("^I should see \"([^\"]*)\" checkbox selected under \"([^\"]*)\"$")
    public void i_should_see_checkbox_selected_under(String checkboxOption, String multioptionalHeader) throws Throwable {

              ClickOn(MultiSelectOptionHeader(multioptionalHeader));
              Assert.assertTrue(isAttributeValuePresent(multiSelectCheckbox(multioptionalHeader,checkboxOption),"checked","true"));


    }


    @When("^I try to comment on first feed post$")
    public void i_try_to_comment_on_first_feed_post() throws Throwable {

          WebElement firstFeed =  getWebElements(feedPosts).get(0);
          commmentCount = Integer.parseInt(firstFeed.findElement(CommentLink).getText().replaceAll("[^A-Za-z0-9]",""));
          ClickOn(CommentLink);
          Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          commentValue = timestamp.toString()+"test";
          Enter(CommentTextBox,commentValue);
          KeyBoardEnter(CommentTextBox);
          WebDriverWait wait = new WebDriverWait(driver,10);
          WebElement commentlink = firstFeed.findElement(CommentLink);
          wait.until(ExpectedConditions.textToBePresentInElement(commentlink,Integer.toString(   commmentCount+1)));



    }

    @When("^I should see comment count should increase by one of first feed$")
    public void i_should_see_comment_count_should_increase_by_one_of_first_feed() throws Throwable {
        WebElement firstFeed =  getWebElements(feedPosts).get(0);
        int CurrentcommentCount =Integer.parseInt(firstFeed.findElement(CommentLink).getText().replaceAll("[^A-Za-z0-9]",""));
        Assert.assertEquals(CurrentcommentCount,commmentCount+1);
        firstFeed.findElement(ResearchReportLink).click();

    }



}
