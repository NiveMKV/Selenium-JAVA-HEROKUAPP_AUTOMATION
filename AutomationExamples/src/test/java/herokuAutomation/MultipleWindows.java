package herokuAutomation;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;

public class MultipleWindows {

	WebDriver driver;
	
	@BeforeTest
	public void browserSetup()
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/windows");
	}

	@Test
	public void multiWindow()
	{
		WebElement linkClickHere = driver.findElement(By.linkText("Click Here"));
		linkClickHere.click();
		
		Set<String> windowsID = driver.getWindowHandles();
		Iterator<String> window = windowsID.iterator();
		String parentWindow = window.next();
		String childWindow = window.next();
		
		driver.switchTo().window(childWindow);
		String confirmationText  = driver.getTitle();
		
		Assert.assertEquals(confirmationText, "New Window");
		
		driver.switchTo().window(parentWindow);
		driver.close();
	}
	
	@AfterTest
	public void endBrowserSetup()
	{
		driver.quit();
	}

}
