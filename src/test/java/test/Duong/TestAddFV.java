package test.Duong;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.TaiNguyen.UIMap;

public class TestAddFV {
	public WebDriver driver;
	public UIMap uiMap;
	public UIMap datafile;
	public String workingDir;

	// Declare An Excel Work Book
	HSSFWorkbook workbook;
	// Declare An Excel Work Sheet
	HSSFSheet sheet;
	// Declare A Map Object To Hold TestNG Results
	Map<String, Object[]> TestNGResults;
	public static String driverPath = "\\Resources\\chromedriver.exe";
	public static String dataPath = "\\Resources\\Data_Duong\\datafile.properties";
	public static String locatorPath = "\\Resources\\Data_Duong\\locator.properties";
	public static String FileNameExcel = "DuongResult";

	@Test(description = "Open TestNG Demo Website for Login Test", priority = 1)
	public void lauchWebsite() throws Exception {
		try {
			driver.get(datafile.getData("url"));
			driver.manage().window().maximize();
			// Show form login
			WebElement ShowFormLogin = driver.findElement(uiMap.getLocator("ShowFormLogin_button"));
			ShowFormLogin.click();
			Thread.sleep(1000);
			// Get the username element
			WebElement username_field = driver.findElement(uiMap.getLocator("Username_field"));
			username_field.sendKeys("admin");

			// Get the password element
			WebElement password_field = driver.findElement(uiMap.getLocator("Password_field"));
			password_field.sendKeys("admin123");

			WebElement login = driver.findElement(uiMap.getLocator("Login_button"));
			login.click();

			Thread.sleep(2000);

			driver.get(datafile.getData("url2"));
			Thread.sleep(2000);

			TestNGResults.put("2", new Object[] { 1d, "Open Movie Detail Page", "Get into the page", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("2", new Object[] { 1d, "Open Movie Detail Page", "Get into the page", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}

	@Test(description = "ADD FAVORITE BUTTON", priority = 2)
	public void AddFavoriteDetails() throws Exception {
		try {
			// btn_like
			Thread.sleep(1000);
			WebElement like = driver.findElement(By.xpath("//*[@id=\"like\"]"));
			String btn_like = like.getAttribute("id").toString(); //doi String
			if (!btn_like.equals("like")) {
				AssertJUnit.assertTrue(false);
			}
			like.click();
			
			//btn_dislike
			Thread.sleep(2000);
			WebElement dislike = driver.findElement(By.xpath("//*[@id=\"remove\"]"));
			String btn_dislike = dislike.getAttribute("id").toString(); //doi String
			if (!btn_dislike.equals("remove")) {
				AssertJUnit.assertTrue(false);
			}
			Thread.sleep(1000);
			dislike.click(); //reset btn add favorite
			
			TestNGResults.put("3",
					new Object[] { 2d, "Succesful Add Movie to Favorite", "Button Add Success", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("3",
					new Object[] { 2d, "Succesful Add Movie to Favorite", "Button Add Success", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}

	
	@BeforeClass(alwaysRun = true)
	public void suiteSetUp() throws Exception {
		// create a new work book
		workbook = new HSSFWorkbook();
		// create a new work sheet
		sheet = workbook.createSheet("TestNG Result Sumary");
		TestNGResults = new LinkedHashMap<String, Object[]>();
		// add test result excel file column header
		// write the header in the first row
		TestNGResults.put("1", new Object[] { "Test Step No", "Action", "Expected Output", "Actual Output" });

		try {
			// Get current working directory and load the data file
			workingDir = System.getProperty("user.dir");
			datafile = new UIMap(workingDir + dataPath);
			// Get the object map file
			uiMap = new UIMap(workingDir + locatorPath);
			// Setting up chrome driver path
			System.setProperty("webdriver.chrome.driver", workingDir + driverPath);
			// Lauching chrome browser
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new IllegalStateException("Can't start the Firefox web driver", e);
		}
	}

	@AfterClass
	public void suiteTearDown() {
		// write excel and file name is SaveTestResultToExcel.xls
		Set<String> keyset = TestNGResults.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = TestNGResults.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
				} else if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
				}
			}
		}

		try {
			FileOutputStream out = new FileOutputStream(FileNameExcel + ".xls");
			workbook.write(out);
			out.close();
			System.out.println("Successfully saved selenium WebDriver TestNG result to Excel File!!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// close the browser
		driver.close();
		driver.quit();
	}
}
