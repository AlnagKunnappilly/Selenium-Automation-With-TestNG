package com.bankingproject.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bankingproject.base.BaseClass;
import com.bankingproject.pageobjects.HomePage;
import com.bankingproject.utility.ExtentManager;
import com.bankingproject.utility.Log;

public class HomePageTestCases extends BaseClass {

	public HomePage HomePage = null;
	public static boolean enabled;
	SoftAssert softAssert = new SoftAssert();

	@BeforeTest(alwaysRun = true)
	public void setUp() throws IOException {
		LaunchApp();
		HomePage = PageFactory.initElements(getDriver(), HomePage.class);

	}

	@Test(groups = { "HomePageTestCases" })
	public void validateURL() {
		ExtentManager.logInfo("Validating XYZ Bank application URL .");
		Log.startTestCase("Validating XYZ Bank application URL");
		String url = HomePage.landUrl();
		Assert.assertEquals(url, prop.getProperty("url"));
		Log.info("XYZ bank url found is " + url);
		ExtentManager.logInfo("XYZ bank url found is " + url);

	}

	@Test(groups = { "HomePageTestCases" }, dependsOnMethods = { "validateURL" })
	public void checkHomeButtonFunctionality() {
		enabled = HomePage.returnHomeBtn();
		softAssert.assertTrue(enabled);
		String url = HomePage.landUrl();
		softAssert.assertEquals(url, prop.getProperty("url"));
		Log.info("Home url found is " + url);
		ExtentManager.logInfo("Home url found is " + url);
	}

	@Test(groups = { "HomePageTestCases" }, dependsOnMethods = { "validateURL", "checkHomeButtonFunctionality" })
	public void checkCustomerButtonFunctionality() throws InterruptedException {
		Log.startTestCase("Verify Customer button functionality");
		enabled = HomePage.CustomerBtn();
		softAssert.assertTrue(enabled);
		String url = HomePage.CustomerBtnUrl();
		softAssert.assertEquals(url, prop.getProperty("customerurl"));
		Log.info("Customer url found is " + url);
		ExtentManager.logInfo("Customer url found is " + url);

	}

	@Test(groups = { "HomePageTestCases" }, dependsOnMethods = { "validateURL", "checkHomeButtonFunctionality",
			"checkCustomerButtonFunctionality" })
	public void checkBankMangerButtonFunctionality() throws InterruptedException {
		Log.startTestCase("Verify Bank Manager button functionality");
		enabled = HomePage.BankManagerBtn();
		softAssert.assertTrue(enabled);
		String url = HomePage.BankManagerUrl();
		softAssert.assertEquals(url, prop.getProperty("bankmanagerurl"));
		HomePage.returnBankMangaer();
		Log.info("Bank Manager url found is " + url);
		Log.endTestCase("HomePage testcases has ended");
		ExtentManager.logInfo("HomePage testcases has ended");
		ExtentManager.endTest();

	}

}
