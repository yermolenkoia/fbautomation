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

    protected void setField(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    protected void submitForm(By by) {
        driver.findElement(by).submit();
    }

    protected void setCheckBoxByName(String name, boolean value) {
        WebElement checkBox = getElements(By.name(name)).get(0);
        if (value && !checkBox.isSelected()) {
            checkBox.click();
        } else if (!value && checkBox.isSelected()) {
            checkBox.click();
        }
    }

    protected void setDatePicker(String className, String year, String month, String day) {
        WebElement date = (WebElement) getElements(By.className(className)).get(0);
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

    protected List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

    protected void setFieldWithSuggestionsList(String selector, String value) {
        setField(By.name(selector), value);
        List<WebElement> suggestionsList = getElements(By.className(SUGGESTIONS_LIST_CLASS));
        if (suggestionsList.size() == 0) {
            List<WebElement> create = getElements(By.className(CREATE_NEW_CLASS));
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
        getElements(By.id(DROPDOWN_MENU_ID)).get(0).click();
        getElements(By.partialLinkText(LOG_OUT_TEXT)).get(0).click();
    }

    public void refresh() {
        driver.navigate().refresh();
    }
}
