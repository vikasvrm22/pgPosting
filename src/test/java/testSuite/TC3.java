package testSuite;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageActions.baseClass;

public class TC3 extends baseClass {
	public static String type;

	@DataProvider(name = "pgdata")
	public Object pgData() {

		return new Object[][] { 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","5000","5000","Male" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","87654321","1990","Testing PG","Owner", "Single","2","5000","5000","Male" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","5000","5000","Male" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2019","Testing PG","Owner", "Single","2","5000","5000","Male" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","5000","5000","Male" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","5000","5000","Male" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","5000","5000","Male" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","2000","2000","Male" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","3000","3000","Male" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","6000","12000","Male" },
			
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","5000","5000","Female" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","87654321","1990","Testing PG","Owner", "Single","2","5000","5000","Female" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","5000","5000","Female" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2019","Testing PG","Owner", "Single","2","5000","5000","Female" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","5000","5000","Female" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","5000","5000","Female" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","5000","5000","Female" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","2000","2000","Female" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","3000","3000","Female" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","6000","12000","Female" },
			
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","5000","5000","Both" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","87654321","1990","Testing PG","Owner", "Single","2","5000","5000","Both" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","5000","5000","Both" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2019","Testing PG","Owner", "Single","2","5000","5000","Both" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","5000","5000","Both" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","5000","5000","Both" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","5000","5000","Both" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Agent", "Single","2","2000","2000","Both" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","3000","3000","Both" }, 
			{ "Camorta island, andaman & Nicobar","123 Demo Address","12345678","2020","Testing PG","Owner", "Single","2","6000","12000","Both" },
	

		};
	}

	@Test( dataProvider = "pgdata" )
	public void pgPosting(String Locality, String Address, String pincode, 
			String pgOperational, String NameOfPG, String OwnerType, String bedType, String NumberOfRoom, String MonthlyRent, String Security, String AvailableFor ) throws InterruptedException, IOException {
		try {
			driver.navigate().to(baseUrl);
			driver.manage().window().maximize();
			find("EnterLocality").sendKeys(Locality);
			Thread.sleep(2000);
			click("selectLocality");
			Thread.sleep(2000);
			// Where is your PG Located?
			driver.findElement(By.xpath("//*[@id=\"footer-confirm\"]/div/span")).click();
			driver.findElement(By.xpath("/html/body/footer/div[3]/a")).click();
			Thread.sleep(2000);
			find("Address").sendKeys(Address);
			find("pincode").sendKeys(pincode);
			Thread.sleep(2000);
			find("pgOperational").sendKeys(pgOperational);
			click("SaveAddress");
			Thread.sleep(2000);
			// Name of your PG
			find("NameOfPG").sendKeys(NameOfPG);
			click("Continue");
			Thread.sleep(3000);
			// Name of your PG
			click(OwnerType);
			click("Continue");
			executeScript("mbPgDev.userPopupConfirm()");
			Thread.sleep(3000);
			// Room Categories in your PG
			click(bedType);
			click("Continue");
			Thread.sleep(5000);
			// Rent Details for Single Room
			find("NumberOfRoom").sendKeys(NumberOfRoom);
			find("MonthlyRent").sendKeys(MonthlyRent);
			find("Security").sendKeys(Security);
			click("Continue");
			Thread.sleep(3000);

			// Preferred Gender *

			click(AvailableFor);
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
		
			Thread.sleep(3000);
			click("Continue");
			Thread.sleep(3000);
			find("SingleRoom").sendKeys("C:/Users/vikas.verma/Pictures/error.jpg");
			Thread.sleep(3000);
			click("Continue");
			Thread.sleep(3000);
			click("Continue");
			
			//executeScript("mbPgClaim.selectbutton('Free Pack','0','51','543');");
		//	Thread.sleep(2000);
			//executeScript("mbPgClaim.confirm()");
			//alert.accept();
		//	System.out.println(status);
		//	Assert.assertEquals(find("OrderConfirmation").getSize()!=null, status);

		} catch (Exception e) {
			driver.close();
		}
	}

}
