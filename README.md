# Selenium Automation with TestNG

[![Selenium](https://img.shields.io/badge/Selenium-WebAutomation-blue)](https://www.selenium.dev/) 
[![TestNG](https://img.shields.io/badge/TestNG-Testing%20Framework-brightgreen)](https://testng.org/)

## 🧑‍💻 Project Overview

This project is an automated testing framework using **Selenium WebDriver** and **TestNG**, designed to perform end-to-end testing for XYZ demo banking website. The primary goal of this framework is to automate the functional testing of websites and ensure they behave as expected in different browsers. 

The framework supports **cross-browser** testing, **parallel test execution**, **data-driven testing**, and integrates well with other tools for reporting and CI/CD pipelines.

Built using Java, **TestNG** is used for managing and executing tests, while **Selenium WebDriver** drives the browser interactions. The framework follows the **Page Object Model (POM)** design pattern to ensure clean and maintainable code.

---

## ✨ Key Features

- **Cross-Browser Testing**: Supports multiple browsers such as Chrome, Firefox, and Edge.
- **Parallel Test Execution**: Run multiple tests simultaneously for faster execution.
- **Page Object Model (POM)**: Encapsulates page elements and actions for better maintainability.
- **Data-Driven Testing**: Leverages TestNG’s DataProvider for running tests with different input sets.
- **Custom Reporting**: The reporting mechansim adopted is **ExtentReports** for advanced HTML reports.
- **CI/CD Integration**: Ready for integration with Jenkins, GitLab CI, or any other CI/CD platform.
- **Support for Selenium Grid**: Ability to run tests on remote browsers via Selenium Grid or cloud providers like **BrowserStack** or **Sauce Labs**.

---

## 📦 Prerequisites

Before you start using this project, ensure you have the following installed:

- **Java Development Kit (JDK) 8 or above** (JDK 17 recommended).
- **Apache Maven** for dependency management and project building.
- **Selenium WebDriver** libraries (managed by Maven).
- **TestNG** testing framework.
- **IDE (Optional)**: IntelliJ IDEA, Eclipse, or any preferred Java IDE.

### Installing Java and Maven

1. **Install Java**:  
   Download the JDK from [Oracle JDK Downloads](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use an open-source version like [OpenJDK](https://openjdk.java.net/).

   Verify installation by running:
   ```bash
   java -version
   ```

2. **Install Maven**:  
   Maven is used to handle dependencies and build the project. Download Maven from [Maven's website](https://maven.apache.org/download.cgi).

   After installation, verify by running:
   ```bash
   mvn -version
   ```

---

## 🚀 Getting Started

### 1. Clone the Repository

Clone the project repository to your local machine:

```bash
git clone https://github.com/AlnagKunnappilly/Selenium-Automation-With-TestNG.git
cd Selenium-Automation-With-TestNG
```

### 2. Install Project Dependencies

Run the following Maven command to download all necessary dependencies:

```bash
mvn clean install
```

This will automatically download and install Selenium WebDriver, TestNG, and other required libraries.

### 3. Configure WebDriver

Selenium WebDriver requires specific drivers (e.g., ChromeDriver, GeckoDriver). You can either:

- **Manually download WebDriver** executables from the official sites (e.g., [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/)).
- **Use WebDriverManager** for automatic driver setup (recommended).

Example of setting up WebDriverManager in your tests:

```java
WebDriverManager.chromedriver().setup();  // Automatically sets up ChromeDriver
WebDriver driver = new ChromeDriver();
```

Alternatively, set the path manually in your test code:

```java
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
WebDriver driver = new ChromeDriver();
```

---

## 🧪 Running Tests

### 1. Run Tests with Maven

You can execute all tests in the project using Maven. Run the following command:

```bash
mvn test
```

This will trigger TestNG to run all the tests specified in the `testng.xml` file. TestNG will execute the tests and generate detailed results in the `test-output` directory.

### 2. Running Tests in Parallel

TestNG supports running tests in parallel. To do this, configure your `testng.xml` file like this:

```xml
<suite name="Suite">
	<listeners>
		<listener class-name="com.bankingproject.utility.ListenerClass" />
	</listeners>
	<test name="Test" parallel="tests" thread-count="4">
		<classes>
			<class name="com.bankingproject.testcases.HomePageTestCases" />
			<class name="com.bankingproject.testcases.BankManagerTestCases" />
			<class name="com.bankingproject.testcases.CustomerLoginTestCases" />
		</classes>
	</test> 
</suite>
```

This will execute `LoginTest` and `RegistrationTest` in parallel using 4 threads.

### 3. Running Tests from IDE

If you prefer running tests from an IDE:

1. Open the project in IntelliJ IDEA or Eclipse.
2. Right-click on the `testng.xml` file.
3. Select **Run** to execute the tests.

---

## 📄 Test Reports

TestNG generates reports in the `test-output` directory. These reports include:

- **HTML Report**: Basic overview of passed, failed, and skipped tests.
  
The reporting mechanism used is **Extent Report** it generates html reports for the tests run.

Example of ExtentReports integration:

```java
ExtentReports extent = new ExtentReports();
ExtentTest test = extent.createTest("LoginTest");
test.pass("Login test passed!");
```

---

## 🧩 Project Structure

Here’s an overview of the project structure:

```
selenium-automation-testng/
├── src/
│   ├── main/
|   |   ├── java/
│   |   |   └── com/
│   |   |       └── bankingProject/
│   |   |           └── actiondriver  # common actions class like for waits,clicks etc
│   |   |               └── action.java
│   |   |           └── Base     # Base class that contains all setup
│   |   |               └── BaseClass.java
│   |   |           └── DataProvider   # Data provider class for tests
│   |   |               └── DataProvider.java 
|   |   |           └── PageObjects        # Page Objects
│   |   |               └── BankManagerLogin.java
│   |   |               └── Customerlogin.java
│   |   |               └── HomePage.java
|   |   |           └── Utility            # Classes for extent reports,Excel reading/writing ,Listener class ,log4j
|   |   |               └── Excel.java       
|   |   |               └── ExtentManager.java        
|   |   |               └── ListenerClass.java
|   |   |               └── Log.java
|   |   |   
|   |   ├── resources/          # Resources like testng.xml, Test data
│   |       └── TestData       # Test Data
|   |           └──Customers.xlsx       # Test Data excel containg customers data.
│   |       └── testng.xml      # TestNG suite configuration
│   └── test/
│       ├── java/                 # Test classes
│           └── com/
│               └── bankingProject/
│                   └── testcases/             # Test classes
│                       └── BankManagerTestCases.java     # Tests for Bank Manager.
│                       └── CustomerLoginTestCases.java   # Tests for Customer. 
|                       └── HomePageTestCases.java        # Tests for Home page.
|       
|
├── Configuration  #Configuration files for browser as well as some static data
|   └── Config.properties
|
├── Logs #log4j logging file
|   └──log4j.log
|
├──test-output  #All the kinds of outputs - reports as well as excel file
|  └──OutputExcels
|     └──Transactions.xlsx
|  └──ExtentReports
|     └──Report.html
|
├── Screenshots #screenshots of failed tests
|
├── pom.xml                       # Maven project file
└── README.md                     # This file
```

### Key Components

1. **`testng.xml`**: Defines test suites, test groups, and parallel execution settings.
2. **Test Classes**: Contains the individual test cases (e.g., `LoginTest`, `RegistrationTest`).
3. **Page Object Model**: A clean, modular design where each web page is represented by a Java class, keeping the tests readable and maintainable.

---

## 📝 Example of Page Object Model (POM)

The **Page Object Model (POM)** pattern is used to separate test scripts from the page elements. Each page in your application is represented as a Java class.

Example of a `BankMangerLogin` class:

```java
public class BankMangerLogin extends BaseClass {

	@FindBy(xpath = "/html/body/div/div/div[2]/div/div[1]/button[1]")
	WebElement addCustomerBtn;

	@FindBy(xpath = "//*[@placeholder='First Name']")
	WebElement firstNameText;

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

}
```

### Test Class Using Page Object Model:

```java
 public class BankManagerTestCases extends BaseClass {

	public BankMangerLogin BankMangerLogin = null;
	public static boolean enabled;
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

}
```

---

## ⚙️ CI/CD Integration

This framework is ready for integration into **CI/CD pipelines**. You can easily integrate it with tools like **Jenkins**, **GitLab CI**, **CircleCI**, etc.

1. **Jenkins**: Use the `mvn test` command in a Jenkins pipeline to run your tests.
2. **GitLab CI**: Add Maven commands to the `.gitlab-ci.yml` file for test execution.

Example of integrating with Jenkins:

```bash
node('master') {
    stage('Checkout') {
        checkout scm
    }
    stage('Run Tests') {
        sh 'mvn clean test'
    }
}
```

