package FrameWork;





import cucumber.api.java.Before;
import cucumber.api.java.After;
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

            File file = new File("src\\test\\resources\\chromedriver.exe");
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
    public void cleanUp() {
        driver.close();
        driver.quit();

    }

}
