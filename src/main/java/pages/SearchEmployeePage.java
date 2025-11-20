package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchEmployeePage extends BasePage {

    public SearchEmployeePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
    WebElement pimMenu;

    @FindBy(xpath = "//a[normalize-space()='Employee List']")
    WebElement emplist;

    @FindBy(xpath = "//input[@placeholder='Type for hints...'][1]")
    WebElement empName;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement searchbtn;

    public void clickpim() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

    public void clickemplist() {
        wait.until(ExpectedConditions.elementToBeClickable(emplist)).click();
    }

    public void setempName(String employeeName) {
        wait.until(ExpectedConditions.visibilityOf(empName)).sendKeys(employeeName);
    }

    public void clicksearchbtn() {
        wait.until(ExpectedConditions.elementToBeClickable(searchbtn)).click();
    }
}
