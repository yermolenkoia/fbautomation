package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.automationFramework.Config;

import java.util.List;

abstract class Page {
    protected String path = "";
    protected WebDriver driver;
    protected Config config;
    protected WebDriverWait wait;

    protected static final String SUGGESTIONS_LIST_CLASS = "page";
    protected static final String CREATE_NEW_CLASS = "calltoaction";
    protected static final String SELECT_YEAR_CLASS = "yearMenu";
    protected static final String SELECT_MONTH_CLASS = "monthMenu";
    protected static final String SELECT_DAY_CLASS = "dayMenu";
    protected static final String DROPDOWN_MENU_ID = "userNavigationLabel";
    protected static final String LOG_OUT_TEXT = "Log Out";

    Page(WebDriver driver) {
        this.driver = driver;
        this.config = new Config();
        this.wait = new WebDriverWait(driver, 10);

    }

    public void goTo() {
        this.driver.get(config.getBase_url() + path);
    }

    protected void setFieldById(String id, String text) {
        driver.findElement(By.id(id)).sendKeys(text);
    }

    protected void setFieldByName(String name, String text) {
        getElementsByName(name).get(0).sendKeys(text);
    }

    protected void submitFormById(String id) {
        driver.findElement(By.id(id)).submit();
    }

    protected void submitFormByName(String name) {
        getElementsByName(name).get(0).submit();
    }

    protected void submitFormByClass(String className) {
        getElementsByClass(className).get(0).submit();
    }

    protected void setCheckBoxByName(String name, boolean value) {
        WebElement checkBox = getElementsByName(name).get(0);
        if (value && !checkBox.isSelected()) {
            checkBox.click();
        } else if (!value && checkBox.isSelected()) {
            checkBox.click();
        }
    }

    protected void setDatePicker(String className, String year, String month, String day) {
        WebElement date = (WebElement) getElementsByClass(className).get(0);
        WebElement spanYear = date.findElement(By.className(SELECT_YEAR_CLASS));
        WebElement spanMonth = date.findElement(By.className(SELECT_MONTH_CLASS));
        WebElement spanDay = date.findElement(By.className(SELECT_DAY_CLASS));

        selectFromSpan(spanYear, year);
        selectFromSpan(spanMonth, month);
        selectFromSpan(spanDay, day);
    }

    protected void selectFromSpan(WebElement span, String textValue) {
        Select selectYear = new Select(span);
        selectYear.selectByVisibleText(textValue);
    }

    protected List<WebElement> getElementsByClass(String className) {
        return driver.findElements(By.className(className));
    }

    protected List<WebElement> getElementsById(String id) {
        return driver.findElements(By.id(id));
    }

    protected List<WebElement> getElementsByPartialLinktext(String partialLinktext) {
        return driver.findElements(By.partialLinkText(partialLinktext));
    }

    protected List<WebElement> getElementsByXPath(String xPath) {
        return driver.findElements(By.xpath(xPath));
    }

    protected List<WebElement> getElementsByCssSelector(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector));
    }

    protected List<WebElement> getElementsByName(String name) {
        return driver.findElements(By.name(name));
    }

    protected void setFieldWithSuggestionsList(String selector, String value) {
        setFieldByName(selector, value);
        List<WebElement> suggestionsList = getElementsByClass(SUGGESTIONS_LIST_CLASS);
        if (suggestionsList.size() == 0) {
            List<WebElement> create = getElementsByClass(CREATE_NEW_CLASS);
            create.get(0).click();
        } else {
            suggestionsList.get(0).click();
        }
    }

    protected void waitUntilElementIsNotVisible(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void waitUntilElementIsClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void logout() {
        getElementsById(DROPDOWN_MENU_ID).get(0).click();
        getElementsByPartialLinktext(LOG_OUT_TEXT).get(0).click();
    }

    public void refresh(){
        driver.navigate().refresh();
    }
}
