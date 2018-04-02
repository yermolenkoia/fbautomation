package tests.automationFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import tests.pages.SignIn;

import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected WebDriver driver;
    protected Config config;

    @BeforeSuite
    public void setUpClass() {
        config = new Config();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        // This line gets chromedriver from project root
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logIn();
    }

    @BeforeMethod
    public void setUp() {

    }

    @AfterMethod
    public void tearDown() {

    }

    @AfterSuite
    public void tearDownClass(){
        try {
            logOut();
        } finally {
            driver.quit();
        }
    }

    private void logIn(){
        SignIn signInPage = new SignIn(driver);
        signInPage.goTo();
        signInPage.login(config.getEmail(), config.getPassword());
    }

    private void logOut(){
        SignIn signInPage = new SignIn(driver);
        signInPage.logout();
    }


}
