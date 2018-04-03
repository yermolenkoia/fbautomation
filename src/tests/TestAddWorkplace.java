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
    private static final String LOCATION = "Kyiv";
    private static final String POSITION = "test automation engineer";
    private static final String DESCRIPTION = "Amazing job!";

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
    public void testAddCurrentWorkplace() {
        workAndEducationPage.clickWork();
        workAndEducationPage.setCompany(COMPANY);
        workAndEducationPage.saveWorkplace();
        String actualCompany = workAndEducationPage.getEmployerTitle();
        Assert.assertEquals(actualCompany, COMPANY, "Wrong company name! Expected: " + COMPANY + " \n Actual: "
                + actualCompany);
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
        String actualCompany = workAndEducationPage.getEmployerTitle();
        Assert.assertEquals(actualCompany, COMPANY, "Wrong company name! Expected: " + COMPANY + " \n Actual: "
                + actualCompany);
        Assert.assertTrue(actualDates[0].contains(expectedStartDate), "Wrong star date! Expected: "
                + expectedStartDate + " \n Actual: " + actualDates[0]);
        Assert.assertTrue(actualDates[1].contains(expectedEndDate), "Wrong end date! Expected: " + expectedEndDate +
                " \n Actual: " + actualDates[1]);
    }

    @Test
    public void testAddCurrentWorkplaceWithFullInfo() {
        workAndEducationPage.clickWork();
        workAndEducationPage.setCompany(COMPANY);
        workAndEducationPage.setLocation(LOCATION);
        workAndEducationPage.setPosition(POSITION);
        workAndEducationPage.setDescription(DESCRIPTION);
        workAndEducationPage.saveWorkplace();
        String actualInfo = workAndEducationPage.getWorkInfo();
        String actualCompany = workAndEducationPage.getEmployerTitle();
        String actualDescription = workAndEducationPage.getDescription();
        Assert.assertEquals(actualCompany, COMPANY, "Wrong company name! Expected: " + COMPANY + " \n Actual: "
                + actualCompany);
        Assert.assertTrue(actualInfo.contains(LOCATION), "Wrong location! " + LOCATION + " is not in " + actualInfo);
        Assert.assertTrue(actualInfo.contains(POSITION), "Wrong position! " + POSITION + " is not in " + actualInfo);
        Assert.assertTrue(actualDescription.contains(DESCRIPTION), "Wrong description! Expected: " + DESCRIPTION
                + " \n Actual: " + actualDescription);
    }

    @Test
    public void testAddWorkplaceCreateCompany() {
        String newCompany = "blablabla333";
        workAndEducationPage.clickWork();
        workAndEducationPage.setCompany(newCompany);
        workAndEducationPage.setLocation(LOCATION);
        workAndEducationPage.saveWorkplace();
        String actualCompany = workAndEducationPage.getEmployerTitle();
        Assert.assertEquals(actualCompany, newCompany, "Wrong company name! Expected: " + newCompany + " \n Actual: "
                + actualCompany);
        String workInfo = workAndEducationPage.getWorkInfo();
        Assert.assertTrue(workInfo.contains(LOCATION), "Wrong location! " + LOCATION + " is not in " + workInfo);
    }
}
