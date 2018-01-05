package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BriefCasePage extends BasePage{
    public WebDriver driver;
    public BriefCasePage(SharedDriver sharedDriver) {
        super( sharedDriver.getDriver());
        this.driver = sharedDriver.getDriver();
    }

By ReportName(String nameOfReport)  {return By.xpath("//h4/a[text()='"+nameOfReport+"']");};
    By Tab(String Option){return By.xpath("//ul[@class='nav navbar-nav menu']/li/a[text()='"+Option+"']");}
    By Loader = By.xpath("//h2[text()='Loading research reports...']");


    @Then("^I should see same report listed in My personal brief case$")
    public void i_should_see_same_report_listed_in_My_personal_brief_case() throws Throwable {
       Assert.assertTrue( isDisplayed(ReportName(DashboardPage.ReportName),20));

    }

    @Then("^I should see same report listed in Company Research$")
    public void i_should_see_same_report_listed_in_Company_Research() throws Throwable {
        ClickOn(Tab("Company Research"));
        waitUntilDisappear(Loader,20);
        Assert.assertTrue( isDisplayed(ReportName(DashboardPage.ReportName),10));


    }
}
