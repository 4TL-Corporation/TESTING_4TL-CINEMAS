package test.Han;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import test.TaiNguyen.UIMap;

public class TestLogin {
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
	public static String dataPath = "\\Resources\\Data_Han\\datafile.properties";
	public static String locatorPath = "\\Resources\\Data_Han\\locator.properties";
	public static String FileNameExcel = "HanResult";

	@Test(description = "Open chrome and Open Website", priority = 1)
	public void lauchWebsite() throws Exception {
		try {
			driver.get(datafile.getData("url"));
			driver.manage().window().maximize();
			// Show form login
			WebElement ShowFormLogin = driver.findElement(uiMap.getLocator("ShowFormLogin_button"));
			ShowFormLogin.click();
			TestNGResults.put("2", new Object[] { 1d, "Open chrome and Open Website", "Login opened", "Pass" });

		} catch (Exception e) {
			TestNGResults.put("2", new Object[] { 1d, "Open chrome and Open Website", "Login didn't opened", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}

	@Parameters({ "username", "password" })
	@Test(description = "Fill data to form login", priority = 2)
	public void FillLoginDetails_DN1(String username, String password) throws Exception {
		try {
			// Get the username element
			WebElement username_field = driver.findElement(uiMap.getLocator("Username_field"));
			username_field.sendKeys(username);

			// Get the password element
			WebElement password_field = driver.findElement(uiMap.getLocator("Password_field"));
			password_field.sendKeys(password);

			Thread.sleep(1000);

			TestNGResults.put("3",
					new Object[] { 2d, "Fill data to form login (username-password)", "data has been filled", "Pass" });
		} catch (Exception e) {
			TestNGResults.put("3",
					new Object[] { 2d, "Fill data to form login (username-password)", "data has been filled", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}

	@Test(description = "Action", priority = 3)
	public void DoLogin() throws Exception {
		try {
			// Click on the login button
			WebElement login = driver.findElement(uiMap.getLocator("Login_button"));
			login.click();

			Thread.sleep(2000);

			WebElement Hello_username = driver.findElement(uiMap.getLocator("HelloUsername_field"));
			TestNGResults.put("4",
					new Object[] { 3d, "click login and verify 'Hello' + name", "Login successfully", "Pass" });

			Thread.sleep(2000);
		} catch (Exception e) {
			TestNGResults.put("4", new Object[] { 3d, "click login and verify 'Hello' + name", "Login successfully", "Fail" });
			AssertJUnit.assertTrue(false);
		}
	}

	@Test(description = "Check Role", priority = 4)
	public void isAdmin() throws Exception {
		try {
			WebElement Hello_username = driver.findElement(uiMap.getLocator("HelloUsername_field"));
			// Check showed tab pages for admin Role
			WebElement navadmin = driver.findElement(uiMap.getLocator("NavPageAdmin_NavDropdown"));

			if (Hello_username.getText().equals("Hello! admin")) {
				TestNGResults.put("4", new Object[] { 3d, "This is admin", "admin", "Pass" });
			} else {
				TestNGResults.put("4", new Object[] { 3d, "This is admin", "user", "Fail" });
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			TestNGResults.put("4", new Object[] { 3d, "verify login", "Login successfully", "Fail" });
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
			throw new IllegalStateException("can't start chrome web driver", e);
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
