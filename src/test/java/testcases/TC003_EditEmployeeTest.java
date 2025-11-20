package testcases;
import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.Assert;
import base.BaseClass;
import pages.EditEmployeePage;
import pages.LoginPage;
import utils.DataProviders;

public class TC003_EditEmployeeTest extends BaseClass {

   

	@Test(dataProvider ="editEmployeeData", dataProviderClass = DataProviders.class)
    public void editEmployeeTest(String searchName, String firstName, String middleName, String lastName) {

        logger.info("*************** Starting TC003 EditEmployeeTest ************");

        LoginPage lp = new LoginPage(driver);
        lp.login("Admin", "admin123");
        
        EditEmployeePage editEmp = new EditEmployeePage(driver);
        
        try {
            // Navigate to Employee List
            editEmp.clickPimMenu();
            editEmp.clickEmployeeList();

            // Search employee
            editEmp.enterEmployeeName(firstName);
            editEmp.clickSearch();

            // Edit employee details
            editEmp.clickEdit();
            editEmp.clickSave();

            // Optional validation: Check URL or success message
            Assert.assertTrue(driver.getCurrentUrl().contains("viewEmployee"), "Employee details not updated");
            logger.info("Employee edited successfully: " + firstName + " " + lastName);

        } catch(Exception e) {
            logger.error("Exception in Edit Employee Test: ", e);
            try {
                String path = captureScreen("EditEmployeeFailure");
                logger.info("Screenshot saved at: " + path);
            } catch (IOException io) {
                io.printStackTrace();
            }
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("*************** Finishing TC003 EditEmployeeTest ************");
    }
}
