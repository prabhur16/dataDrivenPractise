package dataDrivenPractise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginApplication
{

WebDriver driver;



	Object[][] data ={ {"Admin","admin123"},
			{"Admin","admin123"},
			{"Admin","admin123"}

	};

	@DataProvider(name="LoginData")
	public Object[][] getData() {
		  
	return data;
	
	}

@BeforeMethod
public void setUp() {

	System.setProperty("webdriver.chrome.driver",
			"C:\\Users\\Prabhu\\Desktop\\Prabhu\\Selenium\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}


@Test(dataProvider = "LoginData")	
public void login(String uName, String pWord) {

	WebElement txtUser = driver.findElement(By.id("txtUsername"));
	txtUser.sendKeys(uName);


	WebElement txtPass = driver.findElement(By.id("txtPassword"));
	txtPass.sendKeys(pWord);

	WebElement btnLogin = driver.findElement(By.id("btnLogin"));
	btnLogin.click();


}
	
@AfterMethod	
	public void tearDown() {
		driver.quit();
	}
}


