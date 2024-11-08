package com.bankingproject.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bankingproject.base.BaseClass;
import com.bankingproject.pageobjects.customerLogin;
import com.bankingproject.utility.ExtentManager;
import com.bankingproject.utility.Log;

public class CustomerLoginTestCases extends BaseClass {

	public customerLogin customerLogin = null;
	public static boolean enabled;
	public static String name;
	public static String sheetName;
	SoftAssert softAssert = new SoftAssert();

	@Test(dependsOnGroups = { "BankManagerTestCases" }, groups = { "CustomerLoginTestCases" })
	public void validateLogin() throws Throwable {
		ExtentManager.logInfo("Verifying Customer login process.");
		customerLogin = PageFactory.initElements(getDriver(), customerLogin.class);
		customerLogin.homeBtnClick();
		Log.startTestCase("Verify that the customer " + name + " was able to login successfully ");
		name = prop.getProperty("firstName") + prop.getProperty("lastName");
		String loggedName = customerLogin.validateCustomer(name);
		softAssert.assertEquals(loggedName, name);
		Log.info("The customer " + loggedName + " logged in successfully");
		ExtentManager.logInfo("The customer " + loggedName + " logged in successfully");
	}

	@Test(dependsOnGroups = { "BankManagerTestCases" }, dependsOnMethods = { "validateLogin" }, groups = {
			"CustomerLoginTestCases" })
	public void deposit() throws Throwable {
		Log.startTestCase("Verify that the customer " + name + " was able to deposit amount ");
		String depositValidation = customerLogin.depositAmount(prop.getProperty("amount"));
		Assert.assertEquals(depositValidation, "Deposit Successful");
		Log.info("The customer " + name + " deposited " + prop.getProperty("amount") + " successfully");
		ExtentManager.logInfo("The customer " + name + " deposited " + prop.getProperty("amount") + " successfully");
	}

	@Test(dependsOnGroups = { "BankManagerTestCases" }, dependsOnMethods = { "validateLogin", "deposit" }, groups = {
			"CustomerLoginTestCases" })
	public void withdrawl() throws Throwable {

		Log.startTestCase(
				"Verify that the customer " + name + " was able to withdraw amount: " + prop.getProperty("amount"));
		String withdrawalValidation = customerLogin.withdrawalAmount(prop.getProperty("amount"));
		Assert.assertEquals(withdrawalValidation, "Transaction successful");
		Log.info("The customer " + name + " withdrwan " + prop.getProperty("amount") + " successfully");
		ExtentManager.logInfo("The customer " + name + " withdrwan " + prop.getProperty("amount") + " successfully");
	}

	@Test(dependsOnGroups = { "BankManagerTestCases" }, dependsOnMethods = { "validateLogin", "deposit",
			"withdrawl" }, groups = { "CustomerLoginTestCases" })
	public void transactionExcel() throws InterruptedException, IOException {
		sheetName = name + "-Trans";
		Log.startTestCase("Verify that the customer " + name + " transactions can be viewed and exported to excel");
		customerLogin.viewTransactions(prop.getProperty("amount"), sheetName);
		Log.info("The customer " + name + " transactions " + " was exported successfully");
		ExtentManager.logInfo("The customer " + name + " transactions " + " was exported successfully");
	}

	@Test(dependsOnGroups = { "BankManagerTestCases" }, dependsOnMethods = { "validateLogin", "deposit", "withdrawl",
			"transactionExcel" }, groups = { "CustomerLoginTestCases" })
	public void resetTransaction() throws Throwable {

		boolean resetValue;
		sheetName = name + "-Trans";
		Log.startTestCase("Verify that the customer " + name + " transactions could be resetted");
		resetValue = customerLogin.resetTransactions(prop.getProperty("amount"), sheetName);
		Assert.assertTrue(resetValue);
		Log.info("Successfully resetted the " + name + " transactions");
		ExtentManager.logInfo("Successfully resetted the " + name + " transactions");
	}

	@Test(dependsOnGroups = { "BankManagerTestCases" }, dependsOnMethods = { "validateLogin", "deposit", "withdrawl",
			"transactionExcel", "resetTransaction" }, groups = { "CustomerLoginTestCases" })
	public void validations() throws Throwable {
		Log.startTestCase("Verify that the customer " + name + " is able to withdraw amount greater than deposited");
		String exceedValidation = customerLogin.withdrawalAmount(prop.getProperty("exceedAmount"));
		Assert.assertEquals(exceedValidation, "Transaction Failed. You can not withdraw amount more than the balance.");
		Log.info("The customer " + name + " couldn't withdraw a amount that exceeded the deposited amount");
		ExtentManager
				.logInfo("The customer " + name + " couldn't withdraw a amount that exceeded the deposited amount");

	}

	@Test(dependsOnGroups = { "BankManagerTestCases" }, dependsOnMethods = { "validateLogin", "deposit", "withdrawl",
			"transactionExcel", "resetTransaction", "validations" }, groups = { "CustomerLoginTestCases" })
	public void logout() throws Throwable {
		Log.startTestCase("Verify that the customer " + name + " was able to successfully logout");
		boolean logoutValue;
		logoutValue = customerLogin.customerLogout();
		Assert.assertTrue(logoutValue);
		Log.info("Successfully logged out the customer " + name);
		ExtentManager.logInfo("Successfully logged out the customer " + name);
		Log.endTestCase("Customers testcases has ended");
		ExtentManager.endTest();
	}

}
