package tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import tests.automationFramework.TestBase;
import tests.pages.AboutWorkAndEducation;

public class TestAddWorkplace extends TestBase {
    private AboutWorkAndEducation workAndEducationPage;
    private static final String COMPANY = "Facebook";

    @BeforeSuite
    public void setUpClass() {
        super.setUpClass();
        workAndEducationPage = new AboutWorkAndEducation(driver);
        workAndEducationPage.goTo();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            workAndEducationPage.deleteWorkplace();
        }
        super.tearDown(result);
    }

    @Test
    public void testAddPastWorkplace() {
        workAndEducationPage.clickWork();
        workAndEducationPage.setCompany(COMPANY);
        workAndEducationPage.setCurrentWorkCheckbox(false);
        workAndEducationPage.setStartDate("2000", "January", "1");
        workAndEducationPage.setEndDate("2010", "December", "31");
        workAndEducationPage.saveWorkplace();
    }
}
