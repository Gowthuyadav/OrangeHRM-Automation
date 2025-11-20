package utils;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {

	private ExtentReports extent;

	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {

		String reportPath = System.getProperty("user.dir") + "/reports/OrangeHRM.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().setReportName("OrangeHRM Test Execution");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@Override
	public void onTestStart(ITestResult result) {
	    ExtentTest extentTest = extent.createTest(result.getName());
	    test.set(extentTest);  // Thread-safe
	   }


	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		ExtentTest currentTest = test.get();

		if (currentTest == null) {
			System.out.println("ERROR: Test object is NULL!");
			return;
		}

		currentTest.fail(result.getThrowable());

		try {
			WebDriver driver = getDriver(result);

			if (driver != null) {
				String screenshotPath = takeScreenshot(driver, result.getName());
				currentTest.addScreenCaptureFromPath(screenshotPath);
			} else {
				currentTest.fail("Driver was NULL. Screenshot not captured.");
			}

		} catch (Exception e) {
			currentTest.fail("Error capturing screenshot: " + e.getMessage());
		}
	}

	private WebDriver getDriver(ITestResult result) {
		try {
			Object testClass = result.getInstance();
			Field driverField = testClass.getClass().getSuperclass().getDeclaredField("driver");
			driverField.setAccessible(true);
			return (WebDriver) driverField.get(testClass);
		} catch (Exception e) {
			return null;
		}
	}

	private String takeScreenshot(WebDriver driver, String testName) throws IOException {

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String destPath = System.getProperty("user.dir")
				+ "/screenshots/" + testName + "_" + timestamp + ".png";

		File dest = new File(destPath);
		src.renameTo(dest);

		return destPath;
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();

		try {
			File htmlFile = new File(System.getProperty("user.dir") + "/reports/OrangeHRM.html");
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
