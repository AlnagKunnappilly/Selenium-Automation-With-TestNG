package com.bankingproject.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bankingproject.base.BaseClass;
import com.bankingproject.dataprovider.Dataprovider;
import com.bankingproject.pageobjects.BankMangerLogin;
import com.bankingproject.utility.ExtentManager;
import com.bankingproject.utility.Log;

public class BankManagerTestCases extends BaseClass {

	public BankMangerLogin BankMangerLogin = null;
	public static boolean enabled;
	public static int row;
	public static int col;
	public static String url;

	@Test(dependsOnGroups = { "HomePageTestCases" }, groups = { "BankManagerTestCases" })
	public void validateAddCustomerURL() throws Throwable {
		Log.startTestCase("Validating add customer login URL");
		ExtentManager.logInfo("Verify the banking manager loads correctly.");
		BankMangerLogin = PageFactory.initElements(getDriver(), BankMangerLogin.class);
		try {
			url = BankMangerLogin.addCustomerUrl();
			Assert.assertEquals(url, prop.getProperty("addcustomerurl"));
			Log.info("Landed on add customer screen");
			ExtentManager.logInfo("Customer screen url: " + url);

		} catch (Exception e) {
			// generic exception handling
			e.printStackTrace();
		}
	}

	@Test(dependsOnGroups = { "HomePageTestCases" }, groups = { "BankManagerTestCases" }, dependsOnMethods = {
			"validateAddCustomerURL" })
	public void addCustomerFeildsDisplayed() {
		Log.startTestCase("All the fields are displayed/enabled for adding customer ");
		enabled = BankMangerLogin.enabledfirstNameText();
		Assert.assertTrue(enabled);
		enabled = BankMangerLogin.enabledlastNameText();
		Assert.assertTrue(enabled);
		enabled = BankMangerLogin.enabledPostCodeText();
		Assert.assertTrue(enabled);
		ExtentManager.logInfo("All the fields are enabled");
		Log.info("All the fields are enabled");

	}

	@Test(dataProvider = "exceldata", dataProviderClass = Dataprovider.class, groups = {
			"BankManagerTestCases" }, dependsOnMethods = { "validateAddCustomerURL",
					"addCustomerFeildsDisplayed" }, dependsOnGroups = { "HomePageTestCases" })
	public void addCustomers(String firstname, String LastName, String Postcode) throws InterruptedException {
		Log.startTestCase("Add a new customer ");
		String customerID = BankMangerLogin.addcustomer(firstname, LastName, Postcode);
		ExtentManager.logInfo("The customer " + firstname + " added successfully with customerID: " + customerID);
		Log.info("The customer " + firstname + " added successfully with customerID: " + customerID);
		ExtentManager.endTest();

	}

	@Test(dataProvider = "fetchCustomerName", dataProviderClass = Dataprovider.class, groups = {
			"BankManagerTestCases" }, dependsOnMethods = { "validateAddCustomerURL", "addCustomerFeildsDisplayed",
					"addCustomers" }, dependsOnGroups = { "HomePageTestCases" })
	public void openAccount(String customerName) throws InterruptedException {

		Log.startTestCase("Open account for newly added customers ");
		String accountNumber = BankMangerLogin.openAccount(customerName);
		Log.info("The customer " + customerName + " added successfully with account number: " + accountNumber);
		ExtentManager
				.logInfo("The customer " + customerName + " added successfully with account number: " + accountNumber);

	}

	@Test(dataProvider = "fetchFirstName", dataProviderClass = Dataprovider.class, groups = {
			"BankManagerTestCases" }, dependsOnGroups = { "HomePageTestCases" }, dependsOnMethods = {
					"validateAddCustomerURL", "addCustomerFeildsDisplayed", "addCustomers", "openAccount" })
	public void deleteCustomer(String customerName) throws InterruptedException {
		ExtentManager.logInfo("Fetching the firstname for checking delete button functionality");
		Log.startTestCase("Fetching the firstname for checking delete button functionality");
		BankMangerLogin.listCustomers(customerName);

	}

	@Test(dependsOnMethods = { "validateAddCustomerURL", "addCustomerFeildsDisplayed" }, groups = {
			"BankManagerTestCases" }, dependsOnGroups = { "HomePageTestCases" })
	public void addSingleCustomer() throws InterruptedException {

		Log.startTestCase("Add a single customer for checking the complete bank functionality ");
		BankMangerLogin.addCustomerUrl();
		BankMangerLogin.addcustomer(prop.getProperty("firstName"), prop.getProperty("lastName"),
				prop.getProperty("postCode"));
		String singleName = prop.getProperty("firstName") + prop.getProperty("lastName");
		BankMangerLogin.openAccount(singleName);
		Log.info("Successfully added the customer " + singleName);
		ExtentManager.logInfo("Successfully added the customer " + singleName);
		ExtentManager.endTest();
	}

}
