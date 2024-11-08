package com.bankingproject.pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bankingproject.actiondriver.Action;
import com.bankingproject.base.BaseClass;

public class customerLogin extends BaseClass {

	public static String customerName;

	@FindBy(xpath = "//*[text()='Home']")
	WebElement homeBtn;

	@FindBy(xpath = "//*[text()='Customer Login']")
	WebElement customerBtn;

	@FindBy(id = "userSelect")
	WebElement userSelect;

	@FindBy(xpath = "//*[text()='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//div[contains(@class,'borderM')]//div//strong//span")
	WebElement welcomeTxt;

	@FindBy(xpath = "//div[@ng-hide='noAccount']//button[1]")
	WebElement transactionBtn;

	@FindBy(xpath = "//table[contains(@class,'table')]")
	WebElement webTable;

	@FindBy(xpath = "//div[@ng-hide='noAccount']//button[2]")
	WebElement depositBtn;

	@FindBy(xpath = "//*[@placeholder='amount']")
	WebElement amountTxt;

	@FindBy(xpath = "//*[text()='Deposit']")
	WebElement depositedBtn;

	@FindBy(xpath = "//span[text()='Deposit Successful']")
	WebElement depositMessage;

	@FindBy(xpath = "//div[@ng-hide='noAccount']//button[3]")
	WebElement withdrawalBtn;

	@FindBy(xpath = "//*[@placeholder='amount']")
	WebElement withdrawAmountTxt;

	@FindBy(xpath = "//*[text()='Withdraw']")
	WebElement withdrawnBtn;

	@FindBy(xpath = "//div[@class='ng-scope']//span[@ng-show='message']")
	WebElement withdrawMessage;

	@FindBy(xpath = "//*[text()='Reset']")
	WebElement resetBtn;

	@FindBy(xpath = "//*[text()='Back']")
	WebElement navigateBackBtn;

	@FindBy(xpath = "//*[text()='Logout']")
	WebElement accountLogOutBtn;

	public customerLogin() {
		PageFactory.initElements(getDriver() , this);
	}

	public void homeBtnClick() throws InterruptedException {
		Action.click(getDriver() , homeBtn);
		Thread.sleep(600);
		Action.click(getDriver(), customerBtn);
		Action.waitVisivilbilty(userSelect,getDriver());

	}

	public String validateCustomer(String customerName) throws InterruptedException, IOException {
		Action.findElement(getDriver(), userSelect);
		Action.selectByVisibleText(userSelect, customerName);
		Thread.sleep(600);
		Action.findElement(getDriver(), loginBtn);
		Action.click(getDriver(), loginBtn);
		Thread.sleep(600);
		String welcome = Action.getText(getDriver(), welcomeTxt);
		return welcome;

	}

	public String depositAmount(String amount) throws InterruptedException {
		Action.findElement(getDriver(), depositBtn);
		Action.click(getDriver(), depositBtn);
		Action.sendKeys(amountTxt, amount);
		Thread.sleep(1000);
		Action.findElement(getDriver(), depositBtn);
		Action.click(getDriver(), depositedBtn);
		Action.waitVisivilbilty(depositMessage,getDriver());
		String text = Action.getText(getDriver(), depositMessage);
		Action.implicitWait(getDriver(), 20);
		return text;

	}

	public String withdrawalAmount(String amount) throws InterruptedException {
		Thread.sleep(1000);
		Action.findElement(getDriver(), withdrawalBtn);
		Action.click(getDriver(), withdrawalBtn);
		Thread.sleep(1000);
		Action.sendKeys(withdrawAmountTxt, amount);
		Action.findElement(getDriver(), withdrawnBtn);
		Action.click(getDriver(), withdrawnBtn);
		Action.waitVisivilbilty(withdrawMessage,getDriver());
		String text = Action.getText(getDriver(), withdrawMessage);
		Action.implicitWait(getDriver(), 10);
		return text;

	}

	public void viewTransactions(String amount, String sheetName) throws InterruptedException, IOException {
		Action.click(getDriver(), transactionBtn);
		Action.waitVisivilbilty(webTable,getDriver());
		Action.implicitWait(getDriver(), 10);
		Action.webTableHandling(getDriver(), sheetName, webTable);
	}

	public boolean resetTransactions(String amount, String sheetName) throws InterruptedException, IOException {
		Action.findElement(getDriver(), resetBtn);
		Action.click(getDriver(), resetBtn);
		Thread.sleep(1000);
		Action.waitVisivilbilty(webTable,getDriver());
		Boolean reset = Action.webTableHandling(getDriver(), sheetName, webTable);
		Action.findElement(getDriver(), navigateBackBtn);
		Action.click(getDriver(), navigateBackBtn);
		Action.waitVisivilbilty(welcomeTxt,getDriver());
		depositAmount(amount);
		return reset;
	}

	public boolean customerLogout() throws InterruptedException {
		Action.click(getDriver(), accountLogOutBtn);
		Action.waitVisivilbilty(userSelect,getDriver());
		Boolean logOutSucess = Action.findElement(getDriver(), userSelect);
		Action.implicitWait(getDriver(), 20);
		homeBtnClick();
		return logOutSucess;
	}

}
