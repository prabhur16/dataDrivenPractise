package dataDrivenPractise;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class getDataExcel {
	FileInputStream readExcel;
	Object[][] data =null;
	
	@DataProvider(name="Login Data")
	 public Object[][] getData() throws IOException {
		  data = excelData();
		 return data;
	 }
	
	public String[][] excelData() throws IOException {
		File file = new File("C:\\Users\\Prabhu\\Desktop\\Prabhu\\Eclipse\\eclipse\\POM_Practise.xlsx");
		readExcel = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(readExcel);
		XSSFSheet sheet = workbook.getSheet("data");
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		
		int colCount = sheet.getRow(0).getLastCellNum();
		System.out.println(colCount);
		String[][] testData = new String [rowCount-1][colCount];
		for (int i = 1; i < rowCount; i++) {
			XSSFRow currentrow = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
			
				testData[i-1][j]= currentrow.getCell(j).getStringCellValue();
				System.out.println("Test data is" + testData);
				workbook.close();
			}
		}
		return testData;
	
	}

	
	
	
WebDriver driver;
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


	@Test(dataProvider ="Login Data")
	public void login(String uName, String pWord) throws IOException {

		WebElement txtUser = driver.findElement(By.id("txtUsername"));
		txtUser.sendKeys(uName);


		WebElement txtPass = driver.findElement(By.id("txtPassword"));
		txtPass.sendKeys(pWord);

		WebElement btnLogin = driver.findElement(By.id("btnLogin"));
		btnLogin.click();

		TakesScreenshot takeScreen = (TakesScreenshot) driver;
		File srcFile = takeScreen.getScreenshotAs(OutputType.FILE);
		File targetFile = new File("C:\\Users\\Prabhu\\Desktop\\Prabhu\\Eclipse\\eclipse\\sample1.png");
		FileHandler.copy(srcFile, targetFile);
	}
		
	@AfterMethod	
		public void tearDown() {
			driver.quit();
		}
	}
	

