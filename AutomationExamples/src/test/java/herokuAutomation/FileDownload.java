package herokuAutomation;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FileDownload {
	
	 WebDriver driver;
	 String FileName = "Pic.jpg";
		
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
	 	driver.get("https://admin:admin@the-internet.herokuapp.com/download_secure");
	}
	
	
	@Test(priority = 1)
	public void Filedownload()
	{
		
		WebElement LinkPic =driver.findElement(By.linkText("Pic.jpg"));
		LinkPic.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		File file = new File(System.getProperty("user.dir") + "\\" + FileName);
		Assert.assertTrue(file.exists()); // Assert.assertEquals(file.exists(), true);
	}
		
	@AfterTest
	public void TearDown()
	{
		driver.quit();
	}
}
