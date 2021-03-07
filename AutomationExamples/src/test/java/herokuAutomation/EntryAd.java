package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;


public class EntryAd {

	private static WebDriver driver;
	
	@BeforeClass
	public void browsersetup() 
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/entry_ad");
	}

	@AfterClass
    public void teardown() 
	 {
	        if (driver != null) {
	            driver.quit();
	        }
	 }	
	
	@Test(priority =1)
	public void closeEntryAd() {
		
		WebElement windowTitle = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal-title"))) ;
		Assert.assertEquals(windowTitle.getText(), "THIS IS A MODAL WINDOW");
	
		WebElement btnClose = driver.findElement(By.cssSelector("div.modal-footer"));
		btnClose.click();
	}
}
