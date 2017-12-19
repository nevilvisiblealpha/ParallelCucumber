package FrameWork;





import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;


public class SharedDriver {

    private static boolean startBrowser = false;

    private WebDriver driver;
    public String title = "";
    @Before

    public void init() throws Exception {
        if (!startBrowser) {
            File file=null;
            String OSNAME = System.getProperty("os.name");
            if (OSNAME.equalsIgnoreCase("Linux"))
            {
                File chModFile = new File("src/test/resources/chromedriver");
                String pathChModFile = chModFile.getAbsolutePath();
                Runtime.getRuntime().exec("chmod 777 * "+pathChModFile+"");
              file  = new File("src/test/resources/chromedriver");
            }
            else{
                file  = new File("src/test/resources/chromedriver.exe");
            }

            System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
            driver = new ChromeDriver();
            driver.manage().window().maximize();

            //To stop launching browser after every scenario, assign below variable with true value
            startBrowser = false;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    @After
    public void cleanUp(Scenario scenario) {
        if(scenario.isFailed())
        {
            final byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        driver.close();
        driver.quit();

    }

}
