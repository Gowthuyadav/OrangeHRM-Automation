package testcases;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import pages.SearchEmployeePage;
import utils.DataProviders;

public class TC004_SearchEmployeeTest extends BaseClass {

   

	@Test(dataProvider = "searchEmployeeData", dataProviderClass = DataProviders.class)
    public void searchEmployeeTest(String employeeName) {

        
        logger.info("*************** Starting TC004 SearchEmployeeTest ************");

        LoginPage lp = new LoginPage(driver);
        lp.login("Admin", "admin123");
        SearchEmployeePage searchEmp = new SearchEmployeePage(driver);
       
    
        try {
            // Navigate to Employee List
            searchEmp.clickpim();
            searchEmp.clickemplist();

            // Search for employee
            searchEmp.setempName(employeeName);
            searchEmp.clicksearchbtn();

           
            logger.info("Employee search completed successfully: " + employeeName);

        } catch (Exception e) {
            logger.error("Exception in Search Employee Test: ", e);
            try {
                String path = captureScreen("SearchEmployeeFailure");
                logger.info("Screenshot saved at: " + path);
            } catch (IOException io) {
                io.printStackTrace();
            }
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

	}
	}      
