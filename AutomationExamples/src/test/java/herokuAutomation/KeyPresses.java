package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;


public class KeyPresses {
	
	WebDriver driver;
	
	@BeforeTest
	public void browsersetup() 
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/key_presses");
	}
	
	@Test
	public void keyPressEvents()
	{
			
		WebElement txtInput = driver.findElement(By.id("target"));
		WebElement displayResult = driver.findElement(By.id("result"));
		String result = "";
		
		
		txtInput.sendKeys(Keys.TAB);
		result = displayResult.getText();
		Assert.assertEquals("You entered: TAB", result);
		
		txtInput.sendKeys(Keys.ARROW_RIGHT);
		result = displayResult.getText();
		Assert.assertEquals("You entered: RIGHT", result);
		
		txtInput.sendKeys(Keys.NUMPAD7);
		result = displayResult.getText();
		Assert.assertEquals("You entered: NUMPAD7", result);
		
	}
	
	@AfterTest
	public void endBrowserSetup()
	{
		driver.quit();
	}
}
