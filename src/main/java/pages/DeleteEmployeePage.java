package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DeleteEmployeePage extends BasePage{
	public DeleteEmployeePage(WebDriver driver) {
	    super(driver);
	}

	
	@FindBy(xpath="//span[normalize-space()='PIM']")
	WebElement pimMenu;
	
	@FindBy(xpath="//a[normalize-space()='Employee List']")
	WebElement emplist;
	
	@FindBy(xpath="//input[@placeholder='Type for hints...'][1]")
	WebElement empName;
	
	@FindBy(xpath="//button[normalize-space()='Search']")
	WebElement searchbtn;
	
	@FindBy(xpath="//i[@class='oxd-icon bi-trash']")
	WebElement deletebtn;
	
	
	public void clickpim() {
		 wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();

	}
	
	public void clickemplist() {
		  wait.until(ExpectedConditions.elementToBeClickable(emplist)).click();

	}
	
	public void setempName(String employeeName) {
		 wait.until(ExpectedConditions.visibilityOf(empName)).clear();
		    empName.sendKeys(employeeName);

	}
	public void clicksearchbtn() {
		wait.until(ExpectedConditions.elementToBeClickable(searchbtn)).click();

	}
	
	public void clickdeletebthn() {
	    WebElement deleteBtn = wait.until(ExpectedConditions
	            .elementToBeClickable(By.xpath("//i[@class='oxd-icon bi-trash']")));
	    deleteBtn.click();
	}

}