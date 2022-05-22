package pageActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.ExcelReader;
import Utilities.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
public class baseClass2 {
	//public static ChromeDriver driver;
	public static WebDriver driver;
	Properties prop = new Properties();
	public static String baseUrl;
	InputStream input;
	public static WebElement screenElement = null;
	static ArrayList<WebElement> elementList;
	public static boolean status;
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();
	protected static ExtentTest test;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/excel/testdata1.xlsx");
	
	@BeforeSuite
	public void setup() throws IOException {

		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("pgPosting");
		
		//ChromeOptions option=new ChromeOptions();
		// option.setExperimentalOption("debuggingAddress", "localhost:9898");
		// option.addArguments("--user-data-dir=Users/vikas.verma/AppData/Local/Google/Chrome/User Data/Profile 2");
		// option.addArguments("--profile-directory=Profile 1");
		// option.addArguments("--disable-extensions");
		
		FirefoxOptions option=new FirefoxOptions();
		option.setProfile(myprofile);
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver(option);
		input = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/pageObjects/OR.properties");

		prop.load(input);
		//System.out.println(driver.manage().getCookies());
	/*	String name = "PGSESSIONID";					
        String value = "YzQ5Y2Q4NTQtNDI1OS00YzNjLWE3NWYtZTk2M2U1ZDdiN2Yz";					
        String domain = ".magicbricks.com";					
        String path = "/";
		//Cookie ck = new Cookie(name,value,domain,path);
		 Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);	
        //Cookie ck = new Cookie(name,value,domain);	
		driver.manage().addCookie(ck);*/
		
		
		baseUrl = prop.getProperty("baseUrl");
		System.out.println(baseUrl);
		driver.navigate().to(baseUrl);
		extent = ExtentManager.GetExtent(System.getProperty("user.dir") + "\\test-output\\Extent.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println(driver.manage().getCookies());
		
	}

	@BeforeClass
	public synchronized void beforeClass() {
		ExtentTest test = extent.createTest(getClass().getSimpleName());
		classLevelLog.set(test);

	}

	@BeforeMethod
	public synchronized void beforeMethod(Method method) {
		ExtentTest test = classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);
		testLevelLog.get().log(Status.INFO,
				"<b>" + " Execution of Test Case:- " + method.getName() + " started" + "</b>");
	}

	@AfterSuite
	public void tearDown() {
		// tentSystem.out.println("Token is " + token);
		extent.flush();
	}

	public static void executeScript(String str) {
		((JavascriptExecutor) driver).executeScript(str);
	}

	public static WebElement find(String element) throws InterruptedException, IOException {

		Properties or = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/pageObjects/OR.properties");
		or.load(fis);
		String locatorSelection = or.getProperty(element);
		String[] arrStr = locatorSelection.split("_", 2);

		if (locatorSelection.startsWith("id"))
			screenElement = driver.findElement(By.id(arrStr[1]));
		else if (locatorSelection.startsWith("class"))
			screenElement = driver.findElement(By.className(arrStr[1]));
		else if (locatorSelection.startsWith("name"))
			screenElement = driver.findElement(By.name(arrStr[1]));
		else if (locatorSelection.startsWith("link"))
			screenElement = driver.findElement(By.linkText(arrStr[1]));
		else if (locatorSelection.startsWith("xpath"))
			screenElement = driver.findElement(By.xpath(arrStr[1]));
		else if (locatorSelection.startsWith("tag"))
			screenElement = driver.findElement(By.tagName(arrStr[1]));
		else if (locatorSelection.startsWith("part"))
			screenElement = driver.findElement(By.partialLinkText(arrStr[1]));
		else if (locatorSelection.startsWith("javascript"))
			((JavascriptExecutor) driver).executeScript(arrStr[1]);
		else {
			// addInfo("LocatorTypeMissing in element");
			System.out.println("LocatorTypeMissing in element");
		}
		return screenElement;
	}

	public static ArrayList<WebElement> findGroup(String element) throws InterruptedException, IOException {

		Properties or = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/pageObjects/OR.properties");
		or.load(fis);
		String locatorSelection = or.getProperty(element);
		String[] arrStr = locatorSelection.split("_", 2);

		if (locatorSelection.startsWith("id"))
			elementList = (ArrayList<WebElement>) driver.findElements(By.id(arrStr[1]));
		else if (locatorSelection.startsWith("class"))
			elementList = (ArrayList<WebElement>) driver.findElements(By.className(arrStr[1]));
		else if (locatorSelection.startsWith("name"))
			elementList = (ArrayList<WebElement>) driver.findElements(By.name(arrStr[1]));
		else if (locatorSelection.startsWith("link"))
			elementList = (ArrayList<WebElement>) driver.findElements(By.linkText(arrStr[1]));
		else if (locatorSelection.startsWith("xpath"))
			elementList = (ArrayList<WebElement>) driver.findElements(By.xpath(arrStr[1]));
		else if (locatorSelection.startsWith("tag"))
			elementList = (ArrayList<WebElement>) driver.findElements(By.tagName(arrStr[1]));
		else if (locatorSelection.startsWith("part"))
			elementList = (ArrayList<WebElement>) driver.findElements(By.partialLinkText(arrStr[1]));
		else if (locatorSelection.startsWith("javascript"))
			((JavascriptExecutor) driver).executeScript(arrStr[1]);
		else {
			// addInfo("LocatorTypeMissing in element");
			System.out.println("LocatorTypeMissing in element");
		}
		return elementList;
	}

	public static void click(String element) throws InterruptedException, IOException {
		Properties or = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/pageObjects/OR.properties");
		or.load(fis);
		String locatorSelection = or.getProperty(element); // aid_BUY
		String[] arrStr = locatorSelection.split("_", 2);
		// System.out.println(arrStr[1]);
		if (locatorSelection.startsWith("id")) {
			driver.findElement(By.id(arrStr[1])).click();
			// addInfo("Clicking on " + element);
		} else if (locatorSelection.startsWith("class")) {
			driver.findElement(By.className(arrStr[1])).click();
			// addInfo("Clicking on " + element);
		} else if (locatorSelection.startsWith("name")) {
			driver.findElement(By.name(arrStr[1])).click();
			// addInfo("Clicking on " + element);
		} else if (locatorSelection.startsWith("link")) {
			// addInfo("Clicking on " + element);
			driver.findElement(By.linkText(arrStr[1])).click();
		} else if (locatorSelection.startsWith("xpath")) {
			driver.findElement(By.xpath(arrStr[1])).click();
			// addInfo("Clicking on " + element);
		} else if (locatorSelection.startsWith("tag")) {
			driver.findElement(By.tagName(arrStr[1])).click();
			// addInfo("Clicking on " + element);
		} else {
			System.out.println("LocatorTypeMissing in element");
			// driver.findElement(By.name(arrStr[1])).click();
		}
	}

	public void KeyBoardDone() throws InterruptedException {

		Thread.sleep(2000);

		driver.findElement(By.name("Done")).click();

	}
	
	public void scrollToLast(double count) throws InterruptedException {
		// double count = getCount(s);
		double newCount = count / 5;

		while (newCount > 0) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000)");
			newCount = newCount - 5;
			Thread.sleep(2000);
		}
	}

	public static double getCount(String s) {
		// String s = "24 Results";
		s = s.replaceAll("^\\D+|\\D+$", "").replaceAll("\\D+", ",");
		List<String> numbers = Arrays.asList(s.split(","));
		// System.out.println(numbers);
		double count = Integer.parseInt(s);
		return count;
	}

	public void selectCityPostProperty() throws InterruptedException, IOException {

		click("City");

		find("SearchCity").sendKeys("Andaman & Nicobar");

		click("selectCity");

		click("Locality");

		find("SearcLocality").sendKeys("Camorta");

		click("selectLocality");

	}

}
