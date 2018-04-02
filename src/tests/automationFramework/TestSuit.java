package tests.automationFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;


public abstract class TestSuit {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        Config config = new Config();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        // This line gets chromedriver from project root
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


}
