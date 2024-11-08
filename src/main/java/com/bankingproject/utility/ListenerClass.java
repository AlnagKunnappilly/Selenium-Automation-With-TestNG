package com.bankingproject.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.bankingproject.actiondriver.Action;
import com.bankingproject.base.BaseClass;

public class ListenerClass implements ITestListener {

	Action action = new Action();

	// Called when a test starts
	public void onTestStart(ITestResult result) {
		// Start the test in Extent report
		ExtentManager.startTest( "Test Description: " + result.getName());
	}

	// Called when a test succeeds
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentManager.logInfo("Test passed: " + result.getName());
		}
	}

	// Called when a test fails
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			// Log the failure message and the exception
			ExtentManager
					.logFailure(MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			ExtentManager.logFailure(
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			// Capture screenshot on failure
			String imgPath = Action.screenShot(BaseClass.getDriver(), result.getName());
			ExtentManager.logFailure("Screenshot: " + imgPath);

		}
	}

	// Called when a test is skipped
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			ExtentManager.logInfo("Test skipped: " + result.getName());
		}
	}

	// Called when a test fails but within a success percentage
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// This method is generally used for parameterized tests where some of the tests
		// can fail but still pass overall
	}

	// Called before any tests are run
	public void onStart(ITestContext context) {
		// Initialize any resources if needed before tests start
	}

	// Called after all tests are run
	public void onFinish(ITestContext context) {
		// End the current test and generate the final report
		ExtentManager.endTest();
		ExtentManager.closeReport(); // Ensure everything is written to the report
	}
}
