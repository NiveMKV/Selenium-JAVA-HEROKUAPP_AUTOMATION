package herokuAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import herokuAutomationObjects.BrowserSetupClass;


public class FileUploader {
	
	private static WebDriver driver;
	
	@BeforeClass
	public void browsersetup() 
	{
		driver = BrowserSetupClass.Browsersetup();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/upload");
	}

	@AfterClass
    public void teardown() 
	 {
	        if (driver != null) {
	            driver.quit();
	        }
	 }	
	
	//@Test(priority =1)
	public void uploadFileByClicking() {
		
		String fileName = "some-file.txt";
		WebElement btnChooseFile = driver.findElement(By.id("file-upload"));
		WebElement btnUpload = driver.findElement(By.id("file-submit"));
		
		btnChooseFile.sendKeys(System.getProperty("user.dir") + "/" + fileName);
		btnUpload.click();
		
		WebElement uploadedFile = driver.findElement(By.id("uploaded-files"));
		Assert.assertEquals(fileName, uploadedFile.getText());
		
	}
	
	@Test(priority = 2)
	public void uploadFileByDragandDrop() {
		
		/*WebElement dropArea = driver.findElement(By.id("drag-drop-upload"));
		
		String script = "document.addEventListener('dragover', (event)=> {"
				+ "event.preventDefault();"
				+ "event.dataTransfer.setData('file',some-file.txt)"
				+ "});"
				+ "document.addEventListener('drop', (event)=> {"
				+ "event.preventDefault();"
				+ "var data = event.dataTransfer.getData('file')"
				+ "event.(document.getElementById('drag-drop-upload')).appendChild(data)});";
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript(script); */
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//WebElement dropArea = document.getElementById('drag-drop-upload');

		jse.executeScript(  " 		( document.getElementById('drag-drop-upload').addEventListener('dragover', (event) => {\r\n" + 
				"		  event.stopPropagation();\r\n" + 
				"		  event.preventDefault();\r\n" + 
				"		 \r\n" + 
				"		  event.dataTransfer.dropEffect = 'copy';\r\n" + 
				"		});\r\n" + 
				"\r\n" + 
				"		document.getElementById('drag-drop-upload').addEventListener('drop', (event) => {\r\n" + 
				"		  event.stopPropagation();\r\n" + 
				"		  event.preventDefault();\r\n" + 
				"		  const fileList = event.dataTransfer.files;\r\n" + 
				"		  console.log(fileList);\r\n" + 
				"		});"		);
	}

}
