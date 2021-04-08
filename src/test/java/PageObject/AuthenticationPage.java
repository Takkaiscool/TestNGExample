package PageObject;

import Utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationPage {
    WebDriver driver;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "passwd")
    public WebElement password;

    @FindBy(id = "SubmitLogin")
    public WebElement sumitLogin;

    public AuthenticationPage(){
        driver= WebDriverManager.getDriver();
        PageFactory.initElements(driver,this);
    }

    public void login(String emailID,String pwd){
        email.sendKeys(emailID);
        password.sendKeys(pwd);
        sumitLogin.click();
    }
}
