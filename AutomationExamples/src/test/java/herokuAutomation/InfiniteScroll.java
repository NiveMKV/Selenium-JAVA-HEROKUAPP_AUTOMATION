package herokuAutomation;


import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;

public class InfiniteScroll {

	WebDriver driver;
	
	@BeforeTest
	public void browsersetup() 
	{
	 	driver = BrowserSetupClass.Browsersetup();
	 	driver.get("https://the-internet.herokuapp.com/infinite_scroll");
	}
	

	@Test
	public void infiniteScrolling()
	{
	
		JavascriptExecutor jsExecutor =  (JavascriptExecutor) driver;

		String infiniteScroll = "window.scrollBy(0,document.body.scrollHeight);";
		WebElement footerMessage = driver.findElement(By.linkText("Elemental Selenium"));
		
		//Since its a infinite scroll, closing the browser after 10 secs using timertask.
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			@Override
			public void run()
			{ 		driver.quit(); }
		}, 10000);
		
		
		while( footerMessage.isDisplayed())
		{
			jsExecutor.executeScript(infiniteScroll);
		} 
		
	}
	
	@AfterTest
	public void EndBrowserSetup()
	{
		driver.quit();
	}
}

