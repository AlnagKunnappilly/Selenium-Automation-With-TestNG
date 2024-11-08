package com.bankingproject.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.bankingproject.actiondriver.Action;
import com.bankingproject.utility.ExtentManager;
import com.bankingproject.utility.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static Properties prop;
	public static FileInputStream fis;
	public static ExtentReports extent;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	@BeforeClass
	public void Loadconfig() throws IOException {
		extent = ExtentManager.getInstance();
		System.out.println("Loading configuration...");

		prop = new Properties();

		String configFilePath = System.getProperty("user.dir") + "/Configuration/Config.properties";
		System.out.println("Config file path: " + configFilePath);

		fis = new FileInputStream(configFilePath);
		prop.load(fis);

		System.out.println("Browser property: " + prop.getProperty("browser"));
		System.out.println("URL property: " + prop.getProperty("url"));
	}

	@BeforeSuite
	public static void beforeSuite() {
		try {
			// Configure log4j settings
			PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/log4j.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public void LaunchApp() throws IOException {
		if (prop == null) {
			System.out.println("prop is null. Calling Loadconfig() method...");
			Loadconfig();
		}

		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("internet explorer")) {
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver());
		}

		String url = prop.getProperty("url");
		System.out.println("Launching URL: " + url);

		getDriver().get(url);
		getDriver().manage().window().maximize();
		Action.implicitWait(getDriver(), 10);
	}

	@AfterTest
	public void tearDown() {

		getDriver().quit();
		Log.info("The browser successfully closed");
		ExtentManager.closeReport();
	}


}
