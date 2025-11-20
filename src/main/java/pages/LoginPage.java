package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name = "username")
	WebElement txtusername;
	
	@FindBy(name = "password")
	WebElement txtpassword;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginbtn;
	
	public void setUsername(String username) {
		 new WebDriverWait(driver, Duration.ofSeconds(10))
         .until(ExpectedConditions.visibilityOf(txtusername));
		 txtusername.sendKeys(username);

	}
	public void setPassword(String pwd) {
		if(pwd == null) pwd = "";
		txtpassword.sendKeys(pwd);
	}
	public void clickLogin() {
		loginbtn.click();
	}
	public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLogin();
    }

}
