package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AddEmployeePage extends BasePage{
	
	public AddEmployeePage(WebDriver driver) {
	    super(driver);
	      
	}

	@FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
	WebElement pimMenu;
	
	@FindBy(xpath="//a[normalize-space()='Employee List']")
	WebElement emplist;
	
	
	@FindBy(xpath="//a[normalize-space()='Add Employee']")
	WebElement addemp;
	
	@FindBy(xpath = "//input[contains(@placeholder,'First Name')]")
	public
	WebElement firstname;

	@FindBy(xpath = "//input[contains(@placeholder,'Middle Name')]")
	WebElement middlename;

	@FindBy(xpath = "//input[contains(@placeholder,'Last Name')]")
	WebElement lastname;
	
	@FindBy(xpath="//button[normalize-space()='Save']")
	WebElement savebtn;
	
	public void clickPim() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }
	
	public void clickemplist() {
        wait.until(ExpectedConditions.elementToBeClickable(emplist)).click();
    }
	
	public void clickaddemp() {
        wait.until(ExpectedConditions.elementToBeClickable(addemp)).click();
    }
	public void setFirstName(String Fname) {
	    wait.until(ExpectedConditions.visibilityOf(firstname));
	    firstname.sendKeys(Fname);
	}

	public void setMiddleName(String Mname) {
	    wait.until(ExpectedConditions.visibilityOf(middlename));
	    middlename.sendKeys(Mname);
	}

	public void setLastName(String Lname) {
	    wait.until(ExpectedConditions.visibilityOf(lastname));
	    lastname.sendKeys(Lname);
	}

	
	public void clicksavebtn() {
		 wait.until(ExpectedConditions.elementToBeClickable(savebtn)).click();
		   }
}