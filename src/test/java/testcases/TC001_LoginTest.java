package testcases;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import utils.DataProviders;

public class TC001_LoginTest extends BaseClass {

    @Test(dataProvider = "logindata", dataProviderClass = DataProviders.class)
    public void verify_login(String username, String password, String expectedResult) {
        logger.info("***************Starting TC001 LoginTest************");
        LoginPage lp = new LoginPage(driver);
        lp.setUsername(username);
        lp.setPassword(password);
        lp.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            if (expectedResult.equalsIgnoreCase("valid")) {
                boolean dashboardLoaded = wait.until(ExpectedConditions.urlContains("dashboard"));
                Assert.assertTrue(dashboardLoaded, "Dashboard did not load for valid login");
                logger.info("Valid login successful for user: " + username);

                WebElement profileDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("p.oxd-userdropdown-name")));
                profileDropdown.click();

                WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[normalize-space()='Logout']")));
                logoutLink.click();

                wait.until(ExpectedConditions.urlContains("login"));
                Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                        "Failed to return to login page after logout");
                logger.info("Logout successful, returned to login page");

            } else {
                WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.oxd-alert-content-text")));
                Assert.assertTrue(message.isDisplayed(), "Error message not displayed for invalid login");
                logger.info("Invalid login message displayed: " + message.getText());

                Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                        "Invalid login did not stay on login page");
            }
        } catch (Exception e) {
            logger.error("Exception occurred during login test: ", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***************Finishing TC001 LoginTest************");
    }
}
