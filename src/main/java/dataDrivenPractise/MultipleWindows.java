package dataDrivenPractise;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultipleWindows {
WebDriver driver;
	
	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Prabhu\\Desktop\\Prabhu\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://testleaf.herokuapp.com/pages/Window.html");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void MultiWindow() throws InterruptedException 
	{
		WebElement winButton =driver.findElement(By.id("home"));
		String parentWindow = driver.getWindowHandle();
		System.out.println("the parent window" + parentWindow);
		winButton.click();
		
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String childWin : allWindows) {
			driver.switchTo().window(childWin);
				}
		WebElement editBox = driver.findElement(By.xpath("//*[@id=\"post-153\"]/div[2]/div/ul/li[1]/a/img"));
				editBox.click();
		Thread.sleep(2000);
		driver.close();
		
		driver.switchTo().window(parentWindow);
		
		WebElement multiWin = driver.findElement(By.xpath("//*[@id=\"contentblock\"]/section/div[2]/div/div/button"));
		
		multiWin.click();
		Set <String> openWind = driver.getWindowHandles();
		int openCount = openWind.size();
		System.out.println("openCount s " + openCount);
		
		driver.switchTo().window(parentWindow);
		
		WebElement dontClose = driver.findElement(By.id("color"));
		dontClose.click();
		Set<String> dontCloseWindows = driver.getWindowHandles();
		for (String allWind : dontCloseWindows) {
			if (!allWind.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(allWind);
				driver.close();
			}
		}
	}

	@AfterMethod	
	public void tearDown() {
		driver.quit();
	}
}
	
	
	

