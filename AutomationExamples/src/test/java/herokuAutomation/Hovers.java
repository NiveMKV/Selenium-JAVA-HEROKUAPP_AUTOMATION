package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;


public class Hovers {
		
	 WebDriver driver;
		
	@BeforeTest
	public void browsersetup() 
	{
	 	driver = BrowserSetupClass.Browsersetup();
	 	driver.get("https://the-internet.herokuapp.com/hovers");
	}
	
	
	@Test
	public void hoveringAction()
	{
		String captionFig3;
		Actions actions = new Actions(driver);				
		
		WebElement figure3 = driver.findElement(By.xpath("//div[@class='example']/div[@class='figure'][3]"));
		
		actions.moveToElement(figure3).perform();
		captionFig3 = driver.findElement(By.xpath("//h5[contains(text(),'name: user3')]")).getText();
		
		Assert.assertEquals(captionFig3, "name: user3");
	}
	
	@AfterTest
	public void EndBrowserSetup()
	{
		driver.quit();
	}
}
