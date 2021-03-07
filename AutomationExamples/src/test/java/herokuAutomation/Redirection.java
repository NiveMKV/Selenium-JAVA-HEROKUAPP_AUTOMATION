package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.Assert;


public class Redirection {
	
	WebDriver driver;
	static final String Chrome_Path = System.getProperty("user.dir") + "\\" + "chromedriver.exe";
	
	@BeforeTest
	public void browsersetup() 
	{
		System.setProperty("webdriver.chrome.driver", Chrome_Path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	 	driver.get("https://the-internet.herokuapp.com/redirector");
	}
	
	@Test
	public void RedirectTest()
	{
		
		WebElement Link_Clickhere = driver.findElement(By.id("redirect"));
		Link_Clickhere.click();
				
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[class='example'] h3"))));
		
		String confirmationText = driver.findElement(By.xpath("//div[@class='example']/h3")).getText();
 	
		Assert.assertEquals("Status Codes", confirmationText);		
	}
	
	@AfterTest
	public void EndBrowserSetup()
	{
		driver.quit();
	}
	

}
