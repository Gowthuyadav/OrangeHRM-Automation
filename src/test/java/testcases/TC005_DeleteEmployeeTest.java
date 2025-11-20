package testcases;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.DeleteEmployeePage;
import pages.LoginPage;
import utils.DataProviders;

public class TC005_DeleteEmployeeTest extends BaseClass {

    

	@Test(dataProvider = "deleteEmployeeData", dataProviderClass = DataProviders.class)
	public void deleteEmployeeTest(String empName) {
	    logger.info("*************** Starting TC005 DeleteEmployeeTest ************");

	    LoginPage lp = new LoginPage(driver);
	    lp.login("Admin", "admin123");

	    DeleteEmployeePage delEmp = new DeleteEmployeePage(driver);

	    try {
	        delEmp.clickpim();
	        delEmp.clickemplist();

	        delEmp.setempName(empName);
	        delEmp.clicksearchbtn();

	        delEmp.clickdeletebthn();

	        logger.info("Employee deleted successfully: " + empName);

	    } catch (Exception e) {
	        logger.error("Exception in Delete Employee Test: ", e);
	        try {
	            String path = captureScreen("DeleteEmployeeFailure");
	            logger.info("Screenshot saved at: " + path);
	        } catch (IOException io) {
	            io.printStackTrace();
	        }
	        Assert.fail("Test failed due to exception: " + e.getMessage());
	    }

	    logger.info("*************** Finishing TC005 DeleteEmployeeTest ************");
	}
}