package com.bankingproject.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.bankingproject.actiondriver.Action;
import com.bankingproject.base.BaseClass;

public class BankMangerLogin extends BaseClass {

	@FindBy(xpath = "/html/body/div/div/div[2]/div/div[1]/button[1]")
	WebElement addCustomerBtn;

	@FindBy(xpath = "//*[@placeholder='First Name']")
	WebElement firstNameText;

	@FindBy(xpath = "//*[@placeholder='Last Name']")
	WebElement lastNameText;

	@FindBy(xpath = "//*[@placeholder='Post Code']")
	WebElement postCodeText;

	@FindBy(xpath = "//*[text()='Add Customer']")
	WebElement addBtn;

	@FindBy(xpath = "//div[@class='center']//button[2]")
	WebElement openAccountBtn;

	@FindBy(id = "userSelect")
	WebElement selectCustomer;

	@FindBy(id = "currency")
	WebElement selectCurrency;

	@FindBy(xpath = "//*[text()='Process']")
	WebElement processBtn;

	@FindBy(xpath = "//div[@class=\"center\"]//button[3]")
	WebElement customersBtn;

	@FindBy(xpath = "//*[@placeholder='Search Customer']")
	WebElement searchCustomer;

	@FindBy(xpath = "//*[text()='Delete']")
	WebElement deleteCustomerBtn;

	public BankMangerLogin() {
		PageFactory.initElements(getDriver(), this);
	}

	public String addCustomerUrl() throws InterruptedException {
		Action.waitVisivilbilty(addCustomerBtn,getDriver());
		Action.findElement(getDriver(), addCustomerBtn);
		Action.click(getDriver(), addCustomerBtn);
		Thread.sleep(600);
		String url = Action.getCurrentURL(getDriver());
		return url;
	}

	public Boolean enabledfirstNameText() {
		Boolean flag = Action.findElement(getDriver(), firstNameText);
		return flag;

	}

	public Boolean enabledlastNameText() {
		Boolean flag = Action.findElement(getDriver(), lastNameText);
		return flag;

	}

	public Boolean enabledPostCodeText() {
		Boolean flag = Action.findElement(getDriver(), postCodeText);
		return flag;

	}

	public String addcustomer(String firstName, String lastName, String postCode) throws InterruptedException {
		Action.sendKeys(firstNameText, firstName);
		Action.sendKeys(lastNameText, lastName);
		Action.sendKeys(postCodeText, postCode);
		Action.click(getDriver(), addBtn);
		Thread.sleep(600);
		String customerID = Action.alertPresent(getDriver());
		Thread.sleep(300);
		return customerID;

	}

	public String openAccount(String customerName) throws InterruptedException {
		Action.click(getDriver(), openAccountBtn);
		Thread.sleep(600);
		Action.click(getDriver(), openAccountBtn);
		Action.findElement(getDriver(), selectCustomer);
		Action.click(getDriver(), selectCustomer);
		Action.selectByVisibleText(selectCustomer, customerName);
		Action.findElement(getDriver(), selectCurrency);
		Action.click(getDriver(), selectCurrency);
		Action.selectByVisibleText(selectCurrency, "Dollar");
		Action.click(getDriver(), processBtn);
		String acNumber = Action.alertPresent(getDriver());
		Action.implicitWait(getDriver(), 20);
		return acNumber;
	}

	public void listCustomers(String customerName) throws InterruptedException {
		Action.findElement(getDriver(), customersBtn);
		Action.click(getDriver(), customersBtn);
		Thread.sleep(600);
		Action.sendKeys(searchCustomer, customerName);
		Thread.sleep(600);
		Action.findElement(getDriver(), deleteCustomerBtn);
		Action.click(getDriver(), deleteCustomerBtn);
		Action.implicitWait(getDriver(), 20);

	}

}
