package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;

public class IFrames {
	
	WebDriver driver;
	
	@BeforeTest
	public void browsersetup() 
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/iframe");
	}

	@AfterClass
    public void teardown() 
	 {
	        if (driver != null) {
	            driver.quit();
	        }
	 }			
	
	@Test
	public void iFrameTest()
	{
		WebElement alignCentre  = driver.findElement(By.cssSelector("div[id='mceu_6']"));
		
		driver.switchTo().frame("mce_0_ifr");
		
		WebElement defaultContent = driver.findElement(By.xpath("//p[contains(text(),'goes here.')]"));
		
		defaultContent.clear();
		defaultContent.sendKeys("My Content !! ");
		
		Actions actions = new Actions(driver);
		
		actions.moveToElement(defaultContent).sendKeys(Keys.CONTROL + "a");
		driver.switchTo().defaultContent();
		alignCentre.click();
	}
}
