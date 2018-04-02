package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import tests.automationFramework.Config;

import java.util.List;

abstract class Page {
    protected String path = "";
    protected WebDriver driver;
    protected Config config;

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
    }

    public void goTo() {
        this.driver.get(config.getBase_url() + path);
    }

    protected void setFieldById(String id, String text) {
        driver.findElement(By.id(id)).sendKeys(text);
    }

    protected void setFieldByName(String name, String text) {
        driver.findElement(By.name(name)).sendKeys(text);
    }

    protected void submitFormById(String id) {
        driver.findElement(By.id(id)).submit();
    }

    protected void submitFormByName(String name) {
        driver.findElement(By.name(name)).submit();
    }

    protected void setCheckBoxByName(String name, boolean value) {
        WebElement checkBox = driver.findElement(By.name(name));
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

    protected List<WebElement> getElementsByPartialLinktext(String partialLinktext){
        return driver.findElements(By.partialLinkText(partialLinktext));
    }

    protected void clickElementFromList(List<WebElement> elements, Integer elementNum) {
        elements.get(elementNum).click();
    }

    protected void setFieldWithSuggestionsList(String selector, String value) {
        setFieldByName(selector, value);
        List suggestionsList = getElementsByClass(SUGGESTIONS_LIST_CLASS);
        if (suggestionsList.size() == 0) {
            List create = getElementsByClass(CREATE_NEW_CLASS);
            clickElementFromList(create, 0);
        } else {
            clickElementFromList(suggestionsList, 0);
        }
    }

    public void logout(){
        getElementsById(DROPDOWN_MENU_ID).get(0).click();
        getElementsByPartialLinktext(LOG_OUT_TEXT).get(0).click();
    }
}
