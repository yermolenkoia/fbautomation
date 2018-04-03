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
    private static final String COMPANY = "SoftServe";

    @BeforeSuite
    public void setUpClass() {
        super.setUpClass();
        workAndEducationPage = new AboutWorkAndEducation(driver);
        workAndEducationPage.goTo();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            workAndEducationPage.deleteWorkplace();
        } finally {
            super.tearDown(result);
        }
    }

    @Test
    public void testAddPastWorkplace() {
        String startYear = "2000";
        String startMonth = "January";
        String startDay = "1";
        String endYear = "2010";
        String endMonth = "December";
        String endDay = "31";
        workAndEducationPage.clickWork();
        workAndEducationPage.setCompany(COMPANY);
        workAndEducationPage.setCurrentWorkCheckbox(false);
        workAndEducationPage.setStartDate(startYear, startMonth, startDay);
        workAndEducationPage.setEndDate(endYear, endMonth, endDay);
        workAndEducationPage.saveWorkplace();
        String expectedStartDate = startMonth + " " + startDay + ", " + startYear;
        String expectedEndDate = endMonth + " " + endDay + ", " + endYear;
        String[] actualDates = workAndEducationPage.getWorkInfo().split(" to ");
        Assert.assertTrue(actualDates[0].contains(expectedStartDate), "Wrong star date! Expected: " + expectedStartDate +
                " \n Actual: " + actualDates[0]);
        Assert.assertTrue(actualDates[1].contains(expectedEndDate), "Wrong end date! Expected: " + expectedEndDate +
                " \n Actual: " + actualDates[1]);
    }
}
