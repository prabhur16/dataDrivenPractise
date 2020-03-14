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

public class getWebTableData {
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prabhu\\Desktop\\Prabhu\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://testleaf.herokuapp.com/pages/table.html");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	

	@Test
	public void tabledata() throws IOException
	{
		FileOutputStream output = null;
		List<WebElement> tableRows= driver.findElements(By.xpath("//*[@id=\"contentblock\"]/section/div[1]/table/tbody/tr"));
		int rowsCount = tableRows.size();
		System.out.println("row count: "+ rowsCount);
		
		List<WebElement> tableCols= driver.findElements(By.xpath("//*[@id=\"contentblock\"]/section/div[1]/table/tbody/tr/th"));
		int colCount = tableCols.size();
		System.out.println("Column count: "+ colCount);
		
		File file = new File("C:\\Users\\Prabhu\\Desktop\\Prabhu\\Eclipse\\eclipse\\POM_Practise.xlsx");
		
		FileInputStream excelRead = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(excelRead);	
		XSSFSheet sheet = workbook.getSheet("tableData");	
		
		
		
		
		for (int i = 2; i <= rowsCount; i++) {
			XSSFRow currentRow = sheet.createRow(i-2);
			for (int j = 1; j <= colCount; j++) {
				
				String tableValue = driver.findElement(By.xpath("//*[@id=\"contentblock\"]/section/div[1]/table/tbody/tr["+i+"]/td["+j+"]")).getText();
				currentRow.createCell(j-1).setCellValue(tableValue);
				System.out.println("table value is:" + tableValue);
			}
			
			output = new FileOutputStream(file);
			workbook.write(output);
			output.close();
		}
		
		workbook.close();
	}
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
