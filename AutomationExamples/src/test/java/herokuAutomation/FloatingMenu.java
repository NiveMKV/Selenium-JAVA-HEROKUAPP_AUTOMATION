package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;


public class FloatingMenu {

	WebDriver driver;
		
	@BeforeTest
	public void browsersetup() 
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/floating_menu");
	}

	@AfterClass
    public void teardown() 
	 {
	        if (driver != null) {
	            driver.quit();
	        }
	 }
	
	@Test(priority = 1)
	public void clickFloatingMenu()
	{
		// Scrolling to footer
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		WebElement mnuContact = driver.findElement(By.linkText("Contact"));
		mnuContact.click();
		
		String contactMenuURL = driver.getCurrentUrl();
		Assert.assertEquals(contactMenuURL, "https://the-internet.herokuapp.com/floating_menu#contact");
	}
}
