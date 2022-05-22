package testSuite;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageActions.baseClass;

public class TC1 extends baseClass {
	public static String type;

	@DataProvider(name = "pgdata")
	public Object pgData() {

		return new Object[][] { { "Single" }, { "Single,Double" }, { "Single,Double,Triple" },
				{ "Single,Double,Triple,Others" },

		};
	}

	// Sample Data for git checking purposes
	
	@Test(/* dataProvider = "pgdata" */)
	public void pgPosting() throws InterruptedException, IOException {
		driver.manage().getCookies();
		System.out.println("Hello Git purpose");
		System.out.println("Hello Git purpose");
		System.out.println("Hello Git purpose");
		try {
			driver.navigate().to(baseUrl);
			driver.manage().window().maximize();
			find("EnterLocality").sendKeys("Camorta island, andaman & Nicobar");

			Thread.sleep(2000);
			click("selectLocality");
			Thread.sleep(2000);
			// Where is your PG Located?
			driver.findElement(By.xpath("//*[@id=\"footer-confirm\"]/div/span")).click();
			driver.findElement(By.xpath("/html/body/footer/div[3]/a")).click();
			// Thread.sleep(5000);
			// driver.navigate().refresh();
			Thread.sleep(2000);
			find("Address").sendKeys("123 Demo Address");
			;
			find("pincode").sendKeys("12345678");
			;
			// find("Landmark").sendKeys("Demo Landmark");
			Thread.sleep(2000);

			find("pgOperational").sendKeys("2020");
			click("SaveAddress");
			Thread.sleep(2000);
			// Name of your PG
			find("NameOfPG").sendKeys("TestingPG");
			click("Continue");
			Thread.sleep(3000);
			// Name of your PG
			click("Owner");
			click("Continue");
			executeScript("mbPgDev.userPopupConfirm()");
			Thread.sleep(3000);
			// Room Categories in your PG
			click("Single");
			click("Continue");
			Thread.sleep(5000);
			// Rent Details for Single Room
			find("NumberOfRoom").sendKeys("2");
			find("MonthlyRent").sendKeys("5000");
			find("Security").sendKeys("5000");
			click("Continue");
			Thread.sleep(3000);

			// Preferred Gender *

			click("Male");
			Thread.sleep(2000);
			scrollToLast(5);
			click("Professionals");
			click("Continue");
			Thread.sleep(3000);
			// PG Rules

			click("VegOnly");
			click("Continue");
			Thread.sleep(3000);
			// Services Available
			click("Laundry");
			click("Continue");
			Thread.sleep(3000);
			// Common Area Amenties
			click("Kitchen");
			click("Continue");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"slide_pg-moredetails\"]/div[1]")).click();
			// scrollToLast(10);
			// Tell us more about your PG
			driver.findElement(By.xpath("//*[@id=\"slide_pg-moredetails\"]/div[1]")).click();
			find("EditNUse").sendKeys(Keys.DOWN);
			find("EditNUse").sendKeys(Keys.DOWN);
			find("EditNUse").sendKeys(Keys.DOWN);
			find("EditNUse").sendKeys(Keys.DOWN);
			find("EditNUse").sendKeys(Keys.DOWN);
			click("EditNUse");
			click("Continue");
			Thread.sleep(3000);
			// Tell us more about your PG
			find("BuildingView").sendKeys("C:/Users/vikas.verma/Pictures/download.jpg");
			// enter the file path onto the file-selection input field
			// uploadElement.sendKeys("C:\\newhtml.html");
			Thread.sleep(3000);
			click("Continue");
			Thread.sleep(3000);
			find("SingleRoom").sendKeys("C:/Users/vikas.verma/Pictures/error.jpg");
			Thread.sleep(3000);
			click("Continue");
			Thread.sleep(3000);
			click("Continue");
			//Alert alert=driver.switchTo().alert();
			//alert.accept();
			
			executeScript("mbPgClaim.selectbutton('Free Pack','0','51','543');");
			Thread.sleep(2000);
			executeScript("mbPgClaim.confirm()");
			//alert.accept();
		//	System.out.println(status);
			Assert.assertEquals(find("OrderConfirmation").getSize()!=null, status);

		} catch (Exception e) {
			driver.close();
		}
	}

}
