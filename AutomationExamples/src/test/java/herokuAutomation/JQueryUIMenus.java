package herokuAutomation;



import java.io.File;
import java.util.Hashtable;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class JQueryUIMenus { 
	
	WebDriver driver;
	
	@BeforeTest
	public void browsersetup() 
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");

		//Using default preferences of chrome class to download file to a default directory 
		Map<String, Object> preferences = new Hashtable<String, Object>();
		preferences.put("download.default_directory", System.getProperty("user.dir"));
		
		//setting desired chrome options 
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", preferences);
		
		//Merging the options to our local web driver
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		
	 	//To handle basic authentication -- passing username and passowrd's values to the link itself
	 	driver.get("https://the-internet.herokuapp.com/jqueryui/menu");
	}
	
	@Test(priority = 1)
	public void jQueryUIActions() throws InterruptedException 
	{
		String  pdfFileName = "menu.pdf";
		WebElement mnuEnabled = driver.findElement(By.linkText("Enabled"));
				
		new Actions(driver).moveToElement(mnuEnabled).click().perform();
			
		WebElement mnudownloads = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.linkText("Downloads")));
		new Actions(driver).moveToElement(mnudownloads).click().perform();
	
		
		WebElement mnuPDF = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.linkText("PDF")));
		
		new Actions(driver).moveToElement(mnuPDF).click().perform();

		File file = new File (System.getProperty("user.dir") + "/" + pdfFileName);
		Thread.sleep(5000);
		
		Assert.assertEquals(file.exists(), true);
	}
	
	
	@AfterTest
	public void endBrowserSetup()
	{
		driver.quit();
	}

}
