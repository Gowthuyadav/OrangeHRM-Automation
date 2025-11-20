package testcases;
import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.Assert;
import base.BaseClass;
import pages.AddEmployeePage;
import pages.LoginPage;
import utils.DataProviders;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TC002_AddEmployee extends BaseClass {

    @Test(dataProvider = "addEmployeeData", dataProviderClass = DataProviders.class)
    public void addEmployee(String firstName, String middleName, String lastName) throws IOException {

        logger.info("***************Starting TC002 AddEmployeeTest************");

        try {
        LoginPage lp = new LoginPage(driver);
        lp.login("Admin", "admin123");

        AddEmployeePage addEmp = new AddEmployeePage(driver);
        
        
            addEmp.clickPim();
            addEmp.clickaddemp();
            addEmp.getWait().until(ExpectedConditions.visibilityOf(addEmp.firstname));

            addEmp.setFirstName(firstName);
            addEmp.setMiddleName(middleName);
            addEmp.setLastName(lastName);
            addEmp.clicksavebtn();

            // Wait for profile page after save
            addEmp.getWait().until(ExpectedConditions.urlContains("viewPersonalDetails"));
            Assert.assertTrue(driver.getCurrentUrl().contains("viewPersonalDetails"), 
                    "Employee not added successfully");

            logger.info("Employee added successfully: " + firstName + " " + lastName);

        } catch (Exception e) {
            logger.error("Exception in Add Employee Test: ", e);
            String path = captureScreen("AddEmployeeFailure");
            logger.info("Screenshot saved at: " + path);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***************Finishing TC002 AddEmployeeTest************");
    }
}
