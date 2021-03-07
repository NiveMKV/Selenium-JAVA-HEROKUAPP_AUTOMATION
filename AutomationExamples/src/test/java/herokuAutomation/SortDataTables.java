package herokuAutomation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;

public class SortDataTables extends BrowserSetupClass{
	
	 WebDriver driver;
	
	@BeforeTest
	public void browsersetup() 
	{
	 	driver = BrowserSetupClass.Browsersetup();
	 	driver.get("https://the-internet.herokuapp.com/tables");
	}
	
	@Test
	public void Sortdatatable()
	{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);		
		
		WebElement tblTableOne = driver.findElement(By.xpath("//table[@id='table1']"));
		WebElement HeaderFirstName = tblTableOne.findElement(By.xpath("//thead/tr/th[2]"));
		
		List<WebElement> FirstNameOriginalList = driver.findElements(By.xpath("//table[@id='table1']//tbody/tr/td[2]"));
		
		ArrayList<String> FirstNameOriginalArray = new ArrayList<String>();
		
		for(int i=0; i<FirstNameOriginalList.size(); i++)
		{
			FirstNameOriginalArray.add(FirstNameOriginalList.get(i).getText());
		}
		
		
		//If the First Name Column is clicked for first time , it will be sorted out in ascending order.
		HeaderFirstName.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	
		
		List<WebElement> FirstNameAsceList = driver.findElements(By.xpath("//table[@id='table1']//tbody/tr/td[2]"));
		
		ArrayList<String> FirstNameAsceArray = new ArrayList<String>();
		
		for(int i=0; i<FirstNameAsceList.size(); i++)
		{
			FirstNameAsceArray.add(FirstNameAsceList.get(i).getText());
		}
		
		Collections.sort(FirstNameOriginalArray);
		
		//Asserting
		Assert.assertEquals(FirstNameOriginalArray, FirstNameAsceArray);
		
		//If the First Name Column is clicked for second time , it will be sorted out in descending order.
		HeaderFirstName.click();
		List<WebElement> FirstNameDescenList = driver.findElements(By.xpath("//table[@id='table1']//tbody/tr/td[2]"));
		
		ArrayList<String> FirstNameDescenArray = new ArrayList<String>();
		
		for(int i=0; i<FirstNameDescenList.size(); i++)
		{
			FirstNameDescenArray.add(FirstNameDescenList.get(i).getText());
		}
		
		Collections.reverse(FirstNameOriginalArray);
		
		//Asserting
		Assert.assertEquals(FirstNameOriginalArray, FirstNameDescenArray);
		
	}
	
	@AfterTest
	public void EndBrowserSetup()
	{
		driver.quit();
	}
}
