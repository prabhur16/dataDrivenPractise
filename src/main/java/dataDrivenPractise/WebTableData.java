package dataDrivenPractise;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebTableData {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prabhu\\Desktop\\Prabhu\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void tabledata() throws IOException {
		FileOutputStream dataWrite = null;
		//WebElement custtable = driver.findElement(By.xpath("//*[@id=\"customers\"]"));

		List<WebElement> rowsCount = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr"));
		int rows = rowsCount.size();
		System.out.println("Number of rows in table : " + rows);
		List<WebElement> colsCount =driver.findElements(By.xpath("//*[@id=\"customers\"]/tbody/tr[1]/th"));
		int cols = colsCount.size();
		System.out.println("Number of col in table is: " + cols);

		File file = new File("C:\\Users\\Prabhu\\Desktop\\Prabhu\\Eclipse\\eclipse\\POM_Practise.xlsx");
		FileInputStream dataExcel = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(dataExcel);
		XSSFSheet sheet = workbook.getSheet("tableData");
		
		//List<String> tableList = new ArrayList<String>();
		
		for (int i = 2; i <=rows; i++) {
			XSSFRow currentRow = sheet.createRow(i);
			for (int j = 1; j <= cols ; j++) {

				String tableCompany=driver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[" +i+ "]/td[" + j+ "]")).getText();
				System.out.print(tableCompany);
				currentRow.createCell(j-1).setCellValue(tableCompany);
				//tableList.add(tableCompany);
			}

		}
		//System.out.println("The table list is:" + tableList);
		
		dataWrite = new FileOutputStream(file);
		workbook.write(dataWrite);
		dataWrite.close();
		workbook.close();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
