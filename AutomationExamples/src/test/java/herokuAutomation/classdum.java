package herokuAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;

public class classdum {
	private static WebDriver driver;
	
	
	@BeforeClass
	public void browsersetup() 
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/search?q=indium&rlz=1C1CHBF_enIN906IN906&oq=indium&aqs=chrome..69i57j69i61j69i60.2479j0j15&sourceid=chrome&ie=UTF-8");
	}
	
	@Test(priority =1)
	public void dumz() {
		
      List<WebElement> allLinks = driver.findElements(By.xpath("//a/h3"));
		
		int count = 0;
		for(WebElement link : allLinks) {
			if ((link.getText().contains("Indium")))
			{
				count++;
			}
		}
		System.out.println(count);
	}
	
	@Test(priority =2)
	public void check() {
		float fnum = 123.456f;
		float fnum1 =31.78f;
		double i1 = Math.floor(fnum1);
		System.out.println(i1);
				
	}
}
