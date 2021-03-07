package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;

public class LoginPage {
		
	
	WebDriver driver;
	WebElement  txtUsername, txtPassword, btnLogin, errorMessage, successMessage;
	
	@BeforeTest
	public void browsersetup() 
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/login");
	}

	@AfterClass
    public void teardown() 
	 {
	        if (driver != null) {
	            driver.quit();
	        }
	 }	
	
	@BeforeMethod
	public void basicTests() {
		txtUsername = driver.findElement(By.id("username"));
		txtPassword = driver.findElement(By.id("password"));
		btnLogin = driver.findElement(By.cssSelector("button[type='submit']"));
		errorMessage = driver.findElement(By.xpath("//[@class= 'flash error')]")); //flash error
		successMessage= driver.findElement(By.xpath("//['success')]"));
	}
	
	
	@Test(priority = 1)
	public void incorrectUserName_expectsErrorMessage()
	{
		txtUsername.sendKeys("WrongUserName");
		txtPassword.sendKeys("SuperSecretPassword!");
		btnLogin.click();
		
		String errorString = errorMessage.getText();
		Assert.assertTrue(errorString.equals("Your username is invalid!"));
	
	}
	
	@Test(priority = 2)
	public void incorrectPassword_expectsErrorMessage()
	{
		txtUsername.sendKeys("tomsmith ");
		txtPassword.sendKeys("incorrectPassword!");
		btnLogin.click();
		
		String errorString = errorMessage.getText();
		Assert.assertTrue(errorString.equals("Your password is invalid!"));
	
	}

	@Test(priority = 3)
	public void correctUserNameandPassword_expectsSuccessfullLogin()
	{
		txtUsername.sendKeys("tomsmith");
		txtPassword.sendKeys("SuperSecretPassword!");
		btnLogin.click();
		
		String successString = errorMessage.getText();
		Assert.assertEquals("You logged into a secure area!", successString);
	}

}
