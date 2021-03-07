package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;

public class MouseSliding {
	
	 WebDriver driver;
	 WebElement slider, rangeIndicator;
	 Actions actions;
	
	
		
	@BeforeTest
	public void browsersetup() 
	{
	 	driver = BrowserSetupClass.Browsersetup();
	 	driver.get("https://the-internet.herokuapp.com/horizontal_slider");
	 	slider = driver.findElement(By.xpath("//input[@type='range']"));
	 	rangeIndicator =  driver.findElement(By.id("range"));
	 	actions = new Actions(driver);
	}
	
	@Test(priority = 1)
	public void slideByClickandHold()
	{
		actions.clickAndHold(slider).moveByOffset(50, 0).release().build().perform();
		Assert.assertEquals(rangeIndicator.getText(), "4.5");
	}
	
	
	@Test(priority = 2)
	public void slideByDragandDrop()
	{
		actions.dragAndDropBy(slider, -25, 0).perform();
		Assert.assertEquals(rangeIndicator.getText(), "1.5");
	}
	
	
	@Test(priority = 3)
	public void slideByKeyboardKeys()
	{
		actions.moveToElement(slider).sendKeys(Keys.ARROW_RIGHT).build().perform();
		Assert.assertEquals(rangeIndicator.getText(), "2");
	}
	
	 @AfterClass
     public void teardown() 
	 {
	        if (driver != null) {
	            driver.quit();
	        }
	 }
}
