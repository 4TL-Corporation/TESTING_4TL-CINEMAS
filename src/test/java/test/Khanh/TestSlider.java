package test.Khanh;

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

public class TestSlider {
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
	public static String dataPath = "\\Resources\\Data_Khanh\\datafile.properties";
	public static String locatorPath = "\\Resources\\Data_Khanh\\locator.properties";
	public static String FileNameExcel = "KhanhResult";

	@Test(description = "Open TestNG Demo Website", priority = 1)
	public void lauchWebsite() throws Exception {
		try {
			driver.get(datafile.getData("url"));
			driver.manage().window().maximize();
			TestNGResults.put("2", new Object[] { 1d, "Navigate to demo website", "Site gets opened", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("2", new Object[] { 1d, "Navigate to demo website", "Site gets opened", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}

	@Test(description = "Clik the button on slider", priority = 2)
	public void Button_Slide() throws Exception {
		try {
			// Click on the slide button
			WebElement btn_next = driver.findElement(uiMap.getLocator("next_button"));
			WebElement btn_prev = driver.findElement(uiMap.getLocator("prev_button"));
			
			String a = driver.findElement(By.xpath("//div[contains(@class,'sliderv2')]/div/div/div/div/div/div[contains(@class,'slick-active')]")).getAttribute("data-slick-index");
			int slide_next = Integer.parseInt(a);
			Thread.sleep(2000);
			btn_next.click();
			
			if (slide_next != slide_next++) {
				AssertJUnit.assertTrue(false);
			}

			String b = driver.findElement(By.xpath("//div[contains(@class,'sliderv2')]/div/div/div/div/div/div[contains(@class,'slick-active')]")).getAttribute("data-slick-index");
			int slide_prev = Integer.parseInt(b);
			btn_prev.click();
			
			if (slide_prev != slide_prev--) {
				AssertJUnit.assertTrue(false);
			}

			TestNGResults.put("3", new Object[] { 2d, "Click button next and prev on slider", "Click button next and prev success", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("3", new Object[] { 2d, "Click button next and prev on slider", "Click button next and prev fail", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}
	
	@Test(description = "Clik the button more detail on slider", priority = 3)
	public void Button_Detail() throws Exception {
		try {
			// Click on the slide button
			WebElement btn_moreDetail = driver.findElement(By.xpath("//div[contains(@class, 'sliderv2')]/div/div/div/div/div/div[contains(@class, 'slick-active')]/div/div/div/div[contains(@class,'btn-transform')]/div/a[contains(@class,'item-1')]"));
			String videoID = btn_moreDetail.getAttribute("id");
			
			btn_moreDetail.click();
			
			String mvID  = driver.findElement(By.xpath("//*[@id=\"videoID\"]")).getAttribute("href");

			if (!videoID.equals(mvID)) {
				AssertJUnit.assertTrue(false);
			}

			TestNGResults.put("4", new Object[] { 3d, "Click button more detail", "Click more detail button success", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("4", new Object[] { 3d, "Click button more detail", "Click more detail button fail", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}

//	@Test(description = "Fill the Login Details", priority = 2)
//	public void FillLoginDetails() throws Exception {
//		try {
//			// Get the username element
//			WebElement username = driver.findElement(uiMap.getLocator("Username_field"));
//			username.sendKeys(datafile.getData("username"));
//			
//			// Get the password element
//			WebElement password = driver.findElement(uiMap.getLocator("Password_field"));
//			password.sendKeys(datafile.getData("passsword"));
//			
//			Thread.sleep(1000);
//			
//			TestNGResults.put("3", new Object[] {2d, "Fill Login Form Data (username/password)", "Login details gets filled", "Pass"});
//		} catch (Exception e) {
//			TestNGResults.put("3", new Object[] {2d, "Fill Login Form Data (username/password)", "Login details gets filled", "Fail"});
//			AssertJUnit.assertTrue(false);
//		}
//	}

//	@Test(description = "Perform Login", priority = 3)
//	public void DoLogin() throws Exception {
//		try {
//			// Click on the login button
//			WebElement login = driver.findElement(uiMap.getLocator("Login_button"));
//			login.click();
//			
//			Thread.sleep(1000);
//			
//			TestNGResults.put("4", new Object[] {3d, "Click Login and verify welcome message", "Login success", "Pass"});
//		} catch (Exception e) {
//			TestNGResults.put("4", new Object[] {3d, "Click Login and verify welcome message", "Login success", "Fail"});
//			AssertJUnit.assertTrue(false);
//		}
//	}

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
