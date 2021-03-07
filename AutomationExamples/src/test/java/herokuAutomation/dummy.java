package herokuAutomation;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class dummy {
	
	WebDriver driver;
	//static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/chromedriver";

	@BeforeTest
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");

		Map<String, Object> preferences = new Hashtable<String, Object>();
		preferences.put("download.default_directory", System.getProperty("user.dir"));

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", preferences);

		driver = new ChromeDriver(options);
		driver.get("https://the-internet.herokuapp.com/download");
	}

	//@AfterTest
	public void quit() {
		driver.quit();
	}

	@Test(priority = 1)
	public void downlodFile() {
		driver.findElement(By.xpath("//a[contains(text(),'some-file.txt')]")).click();
	}

	@Test(priority = 1)
	public void verifyFileExists() throws InterruptedException {
		String filename = driver.findElement(By.xpath("//a[contains(text(),'some-file.txt')]")).getText();
		System.out.println(filename);	
		File file = new File(System.getProperty("user.dir") + "/" + filename);
		//Thread.sleep(5000);

		Assert.assertEquals(file.exists(), true);
	}
}
