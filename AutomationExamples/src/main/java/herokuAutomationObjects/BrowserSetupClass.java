package herokuAutomationObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserSetupClass {
	
	public static WebDriver Browsersetup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Nivedha\\Sel\\chromedriver_win32-Version81\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
}
