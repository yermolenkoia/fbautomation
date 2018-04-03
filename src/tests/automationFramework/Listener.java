package tests.automationFramework;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        log(tr.getName()+ " -- failed\n");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log(tr.getName()+ " -- skipped\n");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log(tr.getName()+ " -- success\n");
    }

    private void log(String string) {
        System.out.print(string);
    }
}
