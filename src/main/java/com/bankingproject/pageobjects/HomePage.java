package com.bankingproject.pageobjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bankingproject.actiondriver.Action;
import com.bankingproject.base.BaseClass;

public class HomePage extends BaseClass {

	@FindBy(xpath = "//*[text()='Home']")
	WebElement homeBtn;

	@FindBy(xpath = "//*[text()='Customer Login']")
	WebElement customerBtn;

	@FindBy(xpath = "//*[text()='Bank Manager Login']")
	WebElement bankMangerBtn;

	public HomePage() {
		PageFactory.initElements(getDriver(), this);
		

	}

	public String landUrl() {
		String url = Action.getCurrentURL(getDriver());
		return url;

	}

	public boolean BankManagerBtn() throws InterruptedException {
		Action.click(getDriver(), homeBtn);
		Thread.sleep(800);
		Boolean flag = Action.findElement(getDriver(), bankMangerBtn);
		return flag;
	}

	public String BankManagerUrl() throws InterruptedException {
		Action.click(getDriver(), bankMangerBtn);
		Thread.sleep(1000);
		String url = Action.getCurrentURL(getDriver());
		return url;

	}

	public BankMangerLogin returnBankMangaer() {
		return new BankMangerLogin();
	}

	public boolean CustomerBtn() {
		Boolean flag = Action.findElement(getDriver(), customerBtn);
		return flag;
	}

	public String CustomerBtnUrl() throws InterruptedException {

		Action.click(getDriver(), customerBtn);
		Thread.sleep(1000);
		String url = Action.getCurrentURL(getDriver());
		return url;

	}

	public boolean returnHomeBtn() {
		Boolean flag = Action.findElement(getDriver(), homeBtn);
		return flag;
	}

	public void homeBtnClick() throws InterruptedException {
		Action.click(getDriver(), homeBtn);
		Thread.sleep(1000);

	}

	public String homeUrl() throws InterruptedException {
		Action.click(getDriver(), homeBtn);
		Action.implicitWait(getDriver(), 10);
		String url = Action.getCurrentURL(getDriver());
		return url;
	}
}
