package tests.pages;

import org.openqa.selenium.*;

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
    private static final String DELETE_WORKPLACE_CSS = "div > ul > li:nth-child(3) > a > span > span";
    private static final String DELETE_RADIO_BUTTON_NAME = "action";
    private static final String CONFIRM_WORK_REMOVING_BUTTON_CLASS = "layerConfirm";
    private static final String WORK_INFO_CSS = "div[class='fsm fwn fcg']";
    private static final String EMPLOYER_TITLE_CSS = "div._2tdc > div > div > div > div:nth-child(2) > div._2lzr._50f5._50f7";
    private static final String DESCRIPTION_CSS = "div > div > div > div > div:nth-child(2) > div._3-8w._50f8";
    private static final String INVALID_EMPLOYER_MESSAGE_TITLE = "Invalid Employer";

    public AboutWorkAndEducation(WebDriver driver) {
        super(driver);
        this.path = "/" + config.getUsername() + "/about?section=education";
    }

    public void clickWork() {
        List<WebElement> elements = getElements(By.className(EDUCATION_LIST_CLASS));
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
        setField(By.name(DESCRIPTION_FIELD_NAME), description);
    }

    public void saveWorkplace() {
        submitForm(By.name(SAVE_BUTTON_NAME));
        waitUntilElementIsNotVisible(By.name(COMPANY_FIELD_NAME));
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

    public void deleteWorkplace() {
        getElements(By.cssSelector(HIDDEN_OPTIONS_CSS)).get(0).click();
        getElements(By.cssSelector(DELETE_WORKPLACE_CSS)).get(0).click();
        List<WebElement> radioButtons = getElements(By.name(DELETE_RADIO_BUTTON_NAME));
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioButtons.get(1));
        submitForm(By.className(CONFIRM_WORK_REMOVING_BUTTON_CLASS));
        waitUntilElementIsNotVisible(By.className(CONFIRM_WORK_REMOVING_BUTTON_CLASS));
        waitUntilElementIsNotVisible(By.cssSelector(WORK_INFO_CSS));
        refresh();
    }

    public String getWorkInfo() {
        return getText(By.cssSelector(WORK_INFO_CSS));
    }

    public String getEmployerTitle() {
        return getText(By.cssSelector(EMPLOYER_TITLE_CSS));
    }

    public String getDescription() {
        return getText(By.cssSelector(DESCRIPTION_CSS));
    }

    private String getText(By by) {
        List<WebElement> elements = getElements(by);
        for (int i = 0; i < elements.size(); i++) {
            if (!elements.get(i).equals("")) {
                WebElement element = elements.get(i);
                return element.getText();
            }
        }
        return "";
    }

    public boolean isInvalidEmployerMessageAppeared() {
        List <WebElement> elements = getElements(By.partialLinkText(INVALID_EMPLOYER_MESSAGE_TITLE));
        if (elements.size() == 0) return false;
        return true;
    }

}
