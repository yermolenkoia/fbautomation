package tests.pages;

import org.openqa.selenium.WebDriver;

import java.util.List;

public class AboutWorkAndEducation extends Page {
    private static final String EDUCATION_LIST_CLASS = "fbProfileEditExperiences";
    private static final String COMPANY_NAME_NAME = "employer_name";
    private static final String POSITION_NAME = "position_text";
    private static final String SUGGESTIONS_LIST_CLASS = "page";
    private static final String CREATE_NEW_CLASS = "calltoaction";

    public AboutWorkAndEducation(WebDriver driver) {
        super(driver);
        this.path = "/" + config.getUsername() + "/about?section=education";
    }

    public void clickWork() {
        List elements = getElementsByClass(EDUCATION_LIST_CLASS);
        clickElementFromList(elements, 0);
    }

    public void setCompany(String companyName) {
        setFieldByName(COMPANY_NAME_NAME, companyName);
        List suggestionsList = getElementsByClass(SUGGESTIONS_LIST_CLASS);
        if (suggestionsList.size() == 0) {
            List create = getElementsByClass(CREATE_NEW_CLASS);
            clickElementFromList(create, 0);
        } else{
            clickElementFromList(suggestionsList, 0);
        }
    }

    public void setPositionName(String positionName) {
        setFieldByName(POSITION_NAME, positionName);
        List suggestionsList = getElementsByClass(SUGGESTIONS_LIST_CLASS);
        if (suggestionsList.size() == 0) {
            List create = getElementsByClass(CREATE_NEW_CLASS);
            clickElementFromList(create, 0);
        } else{
            clickElementFromList(suggestionsList, 0);
        }
    }

}
