package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;


public class DropDown {

	private static WebDriver driver;
	private static WebElement optionDropDown;
	
	@BeforeClass
	public void browsersetup() 
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/dropdown");
	}

	@AfterClass
    public void teardown() 
	 {
	        if (driver != null) {
	            driver.quit();
	        }
	 }
	
	@BeforeMethod
	public void selectOptionDropDown() {
		optionDropDown = driver.findElement(By.id("dropdown"));
	} 
	
	@Test(priority =1)
	public void selectValueByIndex() {
		Select dropDown = new Select(optionDropDown);
		
		dropDown.selectByIndex(2);
		String isChosenElementSelected = driver.findElement(By.xpath("//option[contains(text(),'Option 2')]")).getAttribute("selected");
		
		Assert.assertEquals("true", isChosenElementSelected);
	}
	
	@Test(priority =2)
	public void selectValueByValue() {
		Select dropDown = new Select(optionDropDown);
		
		dropDown.selectByValue("1");
		String isChosenElementSelected = driver.findElement(By.xpath("//option[contains(text(),'Option 1')]")).getAttribute("selected");
		
		Assert.assertEquals("true", isChosenElementSelected);
	}
	
	@Test(priority =3)
	public void selectValueByVisibleText() {
		Select dropDown = new Select(optionDropDown);
		
		dropDown.selectByVisibleText("Option 2");
		String isChosenElementSelected = driver.findElement(By.xpath("//option[contains(text(),'Option 2')]")).getAttribute("selected");
		
		Assert.assertEquals("true", isChosenElementSelected);
	}
}
