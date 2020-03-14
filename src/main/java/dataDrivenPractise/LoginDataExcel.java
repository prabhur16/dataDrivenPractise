package dataDrivenPractise;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginDataExcel {
	WebDriver driver;
	Object[][] data =null;

	@DataProvider(name="LoginData")
	public Object[][] getData() throws IOException {
		 data= getExcelData();
	return data;
	
	}
	
	public String[][] getExcelData() throws IOException {
		
		try {
			FileInputStream excelData = new FileInputStream("C:\\Users\\Prabhu\\Desktop\\Prabhu\\Eclipse\\eclipse\\POM_Practise.xlsx");
			
			Workbook workbook = new XSSFWorkbook(excelData);
			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();
			System.out.println("No of rows: "+ rowCount);
			
			String testData[][] = new String[rowCount][2];
			
			for (int i = 1; i <= rowCount; i++) {
				for (int j = 0; j < 2; j++) {
					
					testData[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
					System.out.println("Test data is" + testData);
					workbook.close();
				}
			}
		
			return testData;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
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