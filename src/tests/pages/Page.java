package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.automationFramework.Config;

import java.util.List;

abstract class Page {
    protected String path = "";
    protected WebDriver driver;
    protected Config config;

    Page(WebDriver driver) {
        this.driver = driver;
        this.config = new Config();
    }

    public void goTo() {
        this.driver.get(config.getBase_url() + path);
    }

    protected void setFieldById(String id, String text){
        driver.findElement(By.id(id)).sendKeys(text);
    }

    protected void setFieldByName(String name, String text){
        driver.findElement(By.name(name)).sendKeys(text);
    }

    protected void submitFormById(String id){
        WebElement element = driver.findElement(By.id(id));
        driver.findElement(By.id(id)).submit();
    }

    protected List getElementsByClass(String className){
        return driver.findElements(By.className(className));
    }

    protected void clickElementFromList(List <WebElement> elements, Integer elementNum){
        elements.get(elementNum).click();
    }
}
