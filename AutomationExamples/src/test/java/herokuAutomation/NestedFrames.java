package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;

public class NestedFrames {

	WebDriver driver;
	
	@BeforeTest
	public void browserSetup()
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/nested_frames");
	}
	
	@Test
	public void switchFrames() throws InterruptedException
	{
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-right");
		String txtRight = driver.findElement(By.tagName("body")).getText();
		Assert.assertEquals(txtRight, "RIGHT");
		
		
		driver.switchTo().parentFrame();
		driver.switchTo().frame("frame-left"); 
		String txtLeft = driver.findElement(By.tagName("body")).getText();
		Assert.assertEquals(txtLeft, "LEFT");
		
		
		driver.switchTo().parentFrame();
		driver.switchTo().frame("frame-middle");
		String txtMiddle = driver.findElement(By.id("content")).getText();
		Assert.assertEquals(txtMiddle, "MIDDLE");	//--Switching between child frames 
		
		driver.switchTo().defaultContent(); 	//--switching back to default webPage first
		driver.switchTo().frame("frame-bottom");
		String txtBottom = driver.findElement(By.tagName("body")).getText();
		Assert.assertEquals(txtBottom, "BOTTOM");
	
	}
	
	@AfterTest
	public void EndBrowserSetup()
	{
		driver.quit();
	}

}
