package com.bankingproject.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bankingproject.actiondriver.Action;
import com.bankingproject.base.BaseClass;

import java.io.File;

import org.openqa.selenium.WebDriver;

public class ExtentManager extends BaseClass {

	private static ExtentReports extent;
	private static ExtentTest test;

	// Define the report directory and file path
	private static final String reportDir = System.getProperty("user.dir") + File.separator + "test-output"
			+ File.separator + "ExtentReports";
	private static final String reportPath = reportDir + File.separator + "Report.html";

	public static ExtentReports getInstance() {
		if (extent == null) {
			File reportDirectory = new File(reportDir);
			if (!reportDirectory.exists()) {
				reportDirectory.mkdirs();
			}

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setDocumentTitle("Test Report");
			sparkReporter.config().setReportName("XYZ Bank Test");
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
		}
		return extent;
	}

	public static void addScreenshot(WebDriver driver, String screenshotName) {
		driver = BaseClass.getDriver();
		String screenshotPath = Action.screenShot(driver, screenshotName);
		if (screenshotPath != null) {
			try {
				test.addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void startTest(String testDescription) {
		test = getInstance().createTest(testDescription);
	}

	public static void endTest() {
		getInstance().flush(); 
	}

	public static void logInfo(String message) {
		test.info(message);
	}

	public static void logFailure(String message) {
		test.fail(message);
	}

	public static void closeReport() {
		if (extent != null) {
			extent.flush(); 
		}
	}

	public static void logFailure(Markup label) {
		// TODO Auto-generated method stub

	}
}
