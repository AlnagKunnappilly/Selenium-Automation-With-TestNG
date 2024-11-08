package com.bankingproject.actiondriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bankingproject.base.BaseClass;
import com.bankingproject.utility.Excel;

public class Action extends BaseClass {

	public static void click(WebDriver driver, WebElement ele) throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();

	}

	public static void waitVisivilbilty(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}

	public static boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = true;
		try {

			if (!ele.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
			flag = true;
		} finally {
			if (flag) {
				System.out.println("Element is found");
				highlightElements(driver, ele);
			} else {
				System.out.println("Element is not found");

			}

		}

		return flag;
	}

	public static boolean isEnabled(WebDriver driver, WebElement ele) {

		boolean flag = findElement(driver, ele);
		try {
			ele.isEnabled();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Element is enabled");
			} else {
				System.out.println("Element is not enabled");

			}
		}

		return flag;
	}

	public static boolean sendKeys(WebElement ele, String data) {

		boolean flag = true;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(data);
		} catch (Exception e) {

			System.out.println("Element not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered the value");
			} else {
				System.out.println("Unable to enter the value");
			}
		}
		return flag;
	}

	public static void implicitWait(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

	}

	public static boolean selectByVisibleText(WebElement ele, String value) {

		boolean flag = true;
		try {
			ele.isDisplayed();
			Select options = new Select(ele);
			options.selectByVisibleText(value);
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully selected the value");
			} else {
				System.out.println("Coudn't select the value");
			}
		}
		return flag;
	}

	public static String alertPresent(WebDriver driver) {

		Boolean alertPresent = false;
		Alert alert = null;
		String value = null;

		try {
			alert = driver.switchTo().alert();
			String alertValue = alert.getText();
			value = alertValue.split(":")[1];
			alertPresent = true;
			alert.accept();
		} catch (NoAlertPresentException e) {
			alertPresent = false;
		} finally {
			if (alertPresent) {
				System.out.println("Alert has been handled successfully");
			} else {
				System.out.println("There was no Alert to handle ");
			}
		}
		return value;
	}

	public static void explictWait(WebDriver driver, WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public static String getText(WebDriver driver, WebElement ele) {

		String text = ele.getText();
		return text;
	}

	public static String getCurrentURL(WebDriver driver) {

		String currentURL = driver.getCurrentUrl();
		return currentURL;
	}

	public static void pageLoadTimeOut(WebDriver driver, int time) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
	}

	public static void highlightElements(WebDriver driver, WebElement ele) {
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", ele);
		}
	}

	public static boolean webTableHandling(WebDriver driver, String name, WebElement table) throws IOException {
		Excel Excel = new Excel("\\test-output\\OutputExcels\\Transactions.xlsx");
		Map<Integer, Object[]> contentP = new TreeMap<Integer, Object[]>();
		int headCols = table.findElements(By.xpath("//thead//tr//td")).size();
		int row = table.findElements(By.xpath("//tbody//tr")).size();
		contentP.put(0, new Object[] { "Date-Time", "Amount", "Transaction-Type" });
		if (row != 0) {
			for (int i = 1; i <= row; i++) {
				Object[] content = new Object[headCols];
				int cell = table.findElements(By.xpath("//tbody//tr[" + i + "]//td")).size();
				for (int j = 1; j <= cell; j++) {
					content[j - 1] = table.findElement(By.xpath("//tbody//tr[" + i + "]/td[" + j + "]")).getText();
				}
				contentP.put(i, content);
			}

			Excel.setCellData(name, contentP);

			return false;
		} else
			return true;
	}

	public static String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destinationDirectory = System.getProperty("user.dir") + "\\Screenshots\\";
		String destinationFile = destinationDirectory + filename + "_" + dateName + ".png";
		File directory = new File(destinationDirectory);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		try {
			FileUtils.copyFile(source, new File(destinationFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destinationFile;
	}
}
