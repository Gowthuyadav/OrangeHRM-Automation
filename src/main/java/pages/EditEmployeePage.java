package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditEmployeePage extends BasePage{
	

	public EditEmployeePage(WebDriver driver) {
	    super(driver);
	       
	}


    // PIM menu
    @FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
    private WebElement pimMenu;

    // Employee List
    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeList;

    // Employee Name input
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameInput;

    // Search button
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    // Edit button (for first employee in results)
    @FindBy(xpath = "(//button[contains(@class,'oxd-icon-button')])[1]")
    private WebElement editButton;

    // Save button
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;


    // Methods

    public void clickPimMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

    public void clickEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeList)).click();
    }

    public void enterEmployeeName(String name) {
        wait.until(ExpectedConditions.visibilityOf(employeeNameInput)).clear();
        employeeNameInput.sendKeys(name);
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickEdit() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }
}
