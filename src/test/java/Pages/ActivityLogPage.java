package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;

public class ActivityLogPage extends BasePage {
    public WebDriver driver;
    public ActivityLogPage(SharedDriver sharedDriver) {
        super( sharedDriver.getDriver());
        this.driver = sharedDriver.getDriver();
    }

    By FromDate = By.id("from");
    By pickerMonth = By.className("picker__month");
    By pickerMonthPrev = By.className("picker__nav--prev");
    By pickerMonthNext =By.className("picker__nav--next");
    By pickDate(int date){
        return By.xpath("//table[@id='from_table']//td[@role='presentation']/div[contains(@class,'picker__day picker__day--infocus')][text()='"+date+"']");

    };
    By FilterButton = By.xpath("//input[@value='Filter']");
    By pickerYearValue = By.className("picker__year");
    By datesFromList =By.xpath("//table[@class='table table-hover']//tr/td[1]");
    By LastPagination =By.xpath("//span[@class='last']/a");
    By NextPagination =By.xpath("//span[@class='next']/a");



    @When("^I select \"([^\"]*)\" days back from date and apply filter$")
    public void
    i_select_days_back_from_date_and_apply_filter(String days) throws Throwable {
        FromDatepicker(days);
        ClickOn(FilterButton);
    }

    @Then("^I should see from \"([^\"]*)\" days back activity logs$")
    public void i_should_see_from_days_back_activity_logs(String Days) throws Throwable {

        List<String> Dates = new ArrayList<String>();
        int count=1;
        while(true){
            List<WebElement> Datelist = getWebElements(datesFromList);
        for (WebElement date : Datelist) {
            String Date = date.getText();
            Dates.add(Date);
        }
        if(isDisplayed(LastPagination,3)) {
            ClickOn(NextPagination);
            WebDriverWait wait = new WebDriverWait(driver,10);
            count++;
            try {
                wait.until(ExpectedConditions.urlContains("page=" + count + ""));
            }catch(Exception e){}
        }
        else{
            break;
        }
    }

        DateTime dateTime = new DateTime();
        DateTime uptoDate = dateTime.minusDays(Integer.parseInt(Days));

        int year = uptoDate.getYear();
        Month month =Month.of(uptoDate.getMonthOfYear());
        int day = uptoDate.getDayOfMonth();
         String expDate = ""+month+", "+day+" "+year+"";

        for(String date: Dates)
        {
            SimpleDateFormat dateformat = new SimpleDateFormat("MMM, d yy", Locale.US);
            Date formatedupToDate = dateformat.parse(String.valueOf(expDate));
            Date formatedDate = dateformat.parse(String.valueOf(date));
            Assert.assertTrue(formatedDate.compareTo(formatedupToDate)>=0);
        }

    }

    private void FromDatepicker(String days) {
        DateTime dateTime = new DateTime();
        DateTime DateToBePicked = dateTime.minusDays(Integer.parseInt(days));
        //  ((JavascriptExecutor)driver).executeScript ("document.getElementById('from').removeAttribute('aria-readonly','false');");// Enables the from date box

        ClickOn(FromDate);
        String currentYear = getText(pickerYearValue);
        int j=0;
        while(DateToBePicked.getYear()<Integer.parseInt(currentYear))
        {
            ClickOn(pickerMonthPrev);
            j++;
            currentYear = getText(pickerYearValue);

        }

        String currentMonth = getText(pickerMonth);
        int currentMonthvalue = Month.valueOf(currentMonth.toUpperCase()).getValue();
        int expectedMonthvalue = DateToBePicked.getMonthOfYear();
        if(currentMonthvalue>expectedMonthvalue)
        {
           int valueToMoveLeftMonth = currentMonthvalue-expectedMonthvalue;
           for(int i=0;i<valueToMoveLeftMonth;i++)
            {

                ClickOn(pickerMonthPrev);
                String month = Month.of(dateTime.minusMonths(i+j+1).getMonthOfYear()).toString();
               String ExpectedMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
                waitUntilTextValuePresent(pickerMonth,ExpectedMonth,10);
            }

        }
        ClickOn(pickDate(DateToBePicked.getDayOfMonth()));
    }


}
