package dataDrivenPractise;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dropdownsample {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Prabhu\\Desktop\\Prabhu\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://testleaf.herokuapp.com/pages/Dropdown.html");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}


	@Test
	public void dropDownsample() throws InterruptedException {
		WebElement dropbox1 = driver.findElement(By.id("dropdown1"));
		Select select = new Select(dropbox1);
		select.selectByIndex(1);
		Thread.sleep(2000);

		WebElement dropbox2 = driver.findElement(By.name("dropdown2"));
		Select select1 = new Select(dropbox2);
		select1.selectByVisibleText("Selenium");
		Thread.sleep(2000);

		WebElement dropbox3 = driver.findElement(By.id("dropdown3"));
		Select select3 = new Select(dropbox3);
		select3.selectByValue("2");
		Thread.sleep(2000);



	}

	@AfterMethod	
	public void tearDown() {
		driver.quit();
	}
}

