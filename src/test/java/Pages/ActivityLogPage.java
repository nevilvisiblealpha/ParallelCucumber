package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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
    By ToDate =By.id("until");
    By pickerMonth = By.className("picker__month");
    By pickerMonthPrev = By.className("picker__nav--prev");
    By pickerMonthNext =By.className("picker__nav--next");
    By pickDate(int date){
        return By.xpath("//table[@id='from_table']//td[@role='presentation']/div[contains(@class,'picker__day picker__day--infocus')][text()='"+date+"']");

    };
    By FilterButton = By.xpath("//input[@value='Filter']");
    By pickerYearValue = By.className("picker__year");
    By datesFromList =By.xpath("//table[@class='table table-hover']//tr/td[1]");
    By starCountFromList = By.xpath("//table[@class='table table-hover']//tr/td[7]/input");
    By attendiesnamesFromList =By.xpath("//table[@class='table table-hover']//tr/td[2]/a");
    By LastPagination =By.xpath("//span[@class='last']/a");
    By NextPagination =By.xpath("//span[@class='next']/a");
    By AnalystDropDown =By.xpath("//div[@id='participant_id_chosen']/a");
    By AnalystAutosuggestText = By.xpath("//div[@id='participant_id_chosen']//div[@class='chosen-search']/input");
    By AnalystAutosuggestResult(String autosuggestValue)  {return By.xpath("//div[@id='participant_id_chosen']//ul[@class='chosen-results']/li/em[text()='"+autosuggestValue+"']");}
    By starFilter(String Ratingcount){return By.xpath("//div[@data-target='#rating_filter_"+Ratingcount+"']");}



    @When("^I select \"([^\"]*)\" days back from date and apply filter$")
    public void
    i_select_days_back_from_date_and_apply_filter(String days) throws Throwable {
        Datepicker(days,FromDate);
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


    private void UntilDatepicker(String days) {
        DateTime dateTime = new DateTime();
        DateTime DateToBePicked = dateTime.plusDays(Integer.parseInt(days));
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

    private void Datepicker(String days,By Locator) {
        DateTime dateTime = new DateTime();
        DateTime DateToBePicked = dateTime.minusDays(Integer.parseInt(days));
        //  ((JavascriptExecutor)driver).executeScript ("document.getElementById('from').removeAttribute('aria-readonly','false');");// Enables the from date box

        ClickOn(Locator);
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
        waitUntilDisappear(pickDate(DateToBePicked.getDayOfMonth()),5);
    }

    @And("^I apply following filter$")
    public void iApplyFollowingFilter(DataTable table) throws Throwable {

        List<FilterData> filterData = new ArrayList<FilterData>();
        filterData = table.asList(FilterData.class);
        for (FilterData filter: filterData)
        {    if(!filter.fromDate.isEmpty())
            {
            Datepicker(filter.fromDate, FromDate);
            }
            if(!filter.analyst.isEmpty())
            {
                SelectFromAutoSuggest(AnalystDropDown, AnalystAutosuggestText, AnalystAutosuggestResult(filter.analyst), filter.analyst);
            }
            if(!filter.starRating.isEmpty())
            {
                ClickOn(starFilter(filter.starRating));
            }

           // Datepicker(filter.toDate,ToDate);
            ClickOn(FilterButton);
        }


    }

    @Then("^I should see Activity log with Internal Attendess as \"([^\"]*)\"$")
    public void iShouldSeeActivityLogWithInternalAttendessAs(String expectedNAme) throws Throwable {
        List<String> Names = new ArrayList<String>();
        int count=1;
        while(true) {
            List<WebElement> Namelist = getWebElements(attendiesnamesFromList);
            for (WebElement name : Namelist) {
                String Date = name.getText();
                Names.add(Date);
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

        for (String name:Names
             ) {

            Assert.assertTrue(name.equalsIgnoreCase(expectedNAme));

        }

    }

    @Then("^I should see Activity log with \"([^\"]*)\" star rating only$")
    public void iShouldSeeActivityLogWithStarRatingOnly(String expectedStarValue) throws Throwable {

        List<String> ListofStarCount = new ArrayList<String>();
        int count=1;
        while(true) {
            List<WebElement> StarCountList = getWebElements(starCountFromList);
            for (WebElement stars : StarCountList) {
                String star = stars.getAttribute("value");
                ListofStarCount.add(star);
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

        for (String star:ListofStarCount
                ) {

            Assert.assertTrue(star.contains(expectedStarValue));

        }


    }


    private class FilterData{
        public String fromDate;
        public String toDate;
          public String team;
         public String analyst;
          public String reasearchProvider;
         public String author;
        public String activityType;
         public String starRating;

        public FilterData(String fromDate, String toDate, String team, String analyst, String reasearchProvider, String author, String activityType, String starRating) {
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.team = team;
            this.analyst = analyst;
            this.reasearchProvider = reasearchProvider;
            this.author = author;
            this.activityType = activityType;
            this.starRating = starRating;
        }
    }



}
