package tests.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AboutWorkAndEducation extends Page {
    private static final String EDUCATION_LIST_CLASS = "fbProfileEditExperiences";
    private static final String COMPANY_FIELD_NAME = "employer_name";
    private static final String POSITION_FIELD_NAME = "position_text";
    private static final String LOCATION_FIELD_NAME = "location_text";
    private static final String DESCRIPTION_FIELD_NAME = "description";
    private static final String SAVE_BUTTON_NAME = "__submit__";
    private static final String CHECKBOX_CURRENT_NAME = "date[current]";
    private static final String START_DATE_DATEPICKER_CLASS = "startDate";
    private static final String END_DATE_DATEPICKER_CLASS = "endDate";
    private static final String HIDDEN_OPTIONS_CSS = "span._2pih._50f8";
    private static final String DELETE_WORKPLACE_XPATH = "//div[@id='u_0_2b']/div/ul/li[3]/a/span/span";
    private static final String DELETE_RADIO_BUTTON_NAME = "action";
    private static final String CONFIRM_WORK_REMOVING_BUTTON_CLASS = "layerConfirm";

    public AboutWorkAndEducation(WebDriver driver) {
        super(driver);
        this.path = "/" + config.getUsername() + "/about?section=education";
    }

    public void clickWork() {
        List <WebElement> elements = getElementsByClass(EDUCATION_LIST_CLASS);
        elements.get(0).click();
    }

    public void setCompany(String company) {
        setFieldWithSuggestionsList(COMPANY_FIELD_NAME, company);
    }

    public void setPosition(String position) {
        setFieldWithSuggestionsList(POSITION_FIELD_NAME, position);
    }

    public void setLocation(String location) {
        setFieldWithSuggestionsList(LOCATION_FIELD_NAME, location);
    }

    public void setDescription(String description) {
        setFieldByName(DESCRIPTION_FIELD_NAME, description);
    }

    public void saveWorkplace() {
        submitFormByName(SAVE_BUTTON_NAME);
    }

    public void setCurrentWorkCheckbox(boolean value) {
        setCheckBoxByName(CHECKBOX_CURRENT_NAME, value);
    }

    public void setStartDate(String year) {
        setDatePicker(START_DATE_DATEPICKER_CLASS, year, "--", "--");
    }

    public void setStartDate(String year, String month) {
        setDatePicker(START_DATE_DATEPICKER_CLASS, year, month, "--");
    }

    public void setStartDate(String year, String month, String day) {
        setDatePicker(START_DATE_DATEPICKER_CLASS, year, month, day);
    }

    public void setEndDate(String year) {
        setDatePicker(END_DATE_DATEPICKER_CLASS, year, "--:", "Day:");
    }

    public void setEndDate(String year, String month) {
        setDatePicker(END_DATE_DATEPICKER_CLASS, year, month, "Day:");
    }

    public void setEndDate(String year, String month, String day) {
        setDatePicker(END_DATE_DATEPICKER_CLASS, year, month, day);
    }

    public void deleteWorkplace(){
        getElementsByCssSelector(HIDDEN_OPTIONS_CSS).get(0).click();
        getElementsByXPath(DELETE_WORKPLACE_XPATH).get(0).click();
        List<WebElement> radioButtons = getElementsByName(DELETE_RADIO_BUTTON_NAME);
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioButtons.get(1));
        submitFormByClass(CONFIRM_WORK_REMOVING_BUTTON_CLASS);
    }

}
