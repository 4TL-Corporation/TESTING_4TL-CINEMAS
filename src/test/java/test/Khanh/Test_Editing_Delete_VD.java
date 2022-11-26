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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import test.TaiNguyen.UIMap;

public class Test_Editing_Delete_VD {
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

	@Test(description = "Open chrome and Open Website", priority = 1)
	public void OpenWebsite() throws Exception {
		try {
			driver.get(datafile.getData("url_adminPage"));
			driver.manage().window().maximize();
			Thread.sleep(1000);
			TestNGResults.put("2", new Object[] { 1d, "Open chrome and Open Website", "Site gets opened", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("2", new Object[] { 1d, "Open chrome and Open Website", "Site gets opened", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}
	
	@Parameters({ "VideoID", "Title", "View", "Description", "Image" })
	@Test(description = "Fill data to form Video Editing", priority = 2)
	public void Fill_VideoEdit_Detail(String VideoID, String Title,String View, String Description,String Image) throws Exception {
		try {
			// Get and fill the videoID element
			WebElement youtubeID_field = driver.findElement(By.xpath("//*[@id=\"videoID\"]"));
			youtubeID_field.sendKeys(VideoID);

			// Get and fill the title element
			WebElement videoTitle_field = driver.findElement(By.xpath("//*[@id=\"title\"]"));
			videoTitle_field.sendKeys(Title);

			// Get and fill the view count element
			WebElement viewCount_field = driver.findElement(By.xpath("//*[@id=\"views\"]"));
			viewCount_field.sendKeys(View);

			// Get and fill the status element
			WebElement status = driver.findElement(By.xpath("//input[@id= 'active']"));
			if (!(status.isSelected())) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", status);
			}

			// Get and fill the description element
			WebElement description_field = driver.findElement(By.xpath("//*[@id=\"description\"]"));
			description_field.sendKeys(Description);

			// Get and fill the image element
			WebElement img = driver.findElement(By.xpath("//*[@id= 'cover']"));
			img.sendKeys(System.getProperty("user.dir") + Image);

			TestNGResults.put("3",
					new Object[] { 2d, "Fill data to form Video Editing ", "data has been filled", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("3",
					new Object[] { 2d, "Fill data to form Video Editing ", "data has been filled", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}

	@Test(description = "Insert Video", priority = 3)
	public void DoCreate() throws Exception {
		try {
			Thread.sleep(1000);
			// Click on the create button
			WebElement create_btn = driver.findElement(By.xpath("//*[@id=\"create\"]"));
			create_btn.click();
			String rs_notice = driver.findElement(uiMap.getLocator("alert")).getAttribute("id");
			if (rs_notice.equals("error")) {
				AssertJUnit.assertTrue(false);
			}
			
			TestNGResults.put("4", new Object[] { 3d, "Click create and show notice 'Video inserted' ",
					"Create successfully", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("4", new Object[] { 3d, "Click create and show notice 'Video inserted' ",
					"Create successfully", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}

	@Test(description = "Delete Video", priority = 4)
	public void DeleteVideoOnTable() throws Exception {
		try {
			Thread.sleep(1000);
			// Click on the tab button
			WebElement videoList = driver.findElement(By.xpath("//*[@id=\"videoList-tab\"]"));
			videoList.click();
			Thread.sleep(1000);
			// Click on the delete button
			WebElement delete_btn = driver.findElement(By.xpath("//*[@id=\"delete/123\"]"));
			delete_btn.click();

			String rs_notice = driver.findElement(uiMap.getLocator("alert")).getAttribute("id");
			if (rs_notice.equals("error")) {
				AssertJUnit.assertTrue(false);
			}

			TestNGResults.put("5", new Object[] { 4d, "Click create and show notice 'Video inserted' ",
					"Create successfully", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("5", new Object[] { 4d, "Click create and show notice 'Video inserted' ",
					"Create successfully", "Fail" });
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
