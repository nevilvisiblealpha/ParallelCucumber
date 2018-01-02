package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResearchReportsPage extends BasePage {


    public WebDriver driver;
    public ResearchReportsPage(SharedDriver sharedDriver) {
        super( sharedDriver.getDriver());
        this.driver = sharedDriver.getDriver();
    }


    By commentsList = By.xpath("//div[@class='row do-not-follow-scroll']//div/p");


    @Then("^I should see same comment shows up when opening research report by clicking on the title$")
    public void i_should_see_same_comment_shows_up_when_opening_research_report_by_clicking_on_the_title() throws Throwable {

      boolean  commentFound= false;
        List<WebElement> comments=  getWebElements(commentsList);
               for(WebElement comment: comments)
               {

                  if( comment.getText().equalsIgnoreCase(DashboardPage.commentValue))
                  {
                      commentFound= true;
                      break;
                  }

               }

        Assert.assertTrue(commentFound);

    }


}
