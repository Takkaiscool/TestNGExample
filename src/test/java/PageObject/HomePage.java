package PageObject;

import Utils.ElementControls;
import Utils.PageControls;
import Utils.WebDriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage {
    @FindBy(className ="login")
    public WebElement signIn;

    @FindBy(xpath = "//img[contains(@class,'logo')]")
    public WebElement logo;

    @FindBy(id = "search_query_top")
    public WebElement searchQuery;

    @FindBy(className = "ajax_cart_no_product")
    public WebElement cartIsEmpty;

    @FindBy(xpath = "//span[@class='shop-phone']/strong")
    public WebElement phoneNumber;

    @FindBy(xpath = "//ul[contains(@class,'menu-content')]/li/a")
    public List<WebElement> menus;

    @FindBy(xpath = "(//a[@title='Summer Dresses'])[2]")
    public WebElement summerDressesUnderDresses;

    @FindBy(xpath = "(//a[@title='Dresses'])[2]")
    public WebElement dresses;

    @FindBy(className = "logout")
    public WebElement logout;
    @FindBy(className = "logout")
    public List<WebElement> logoutPresent;
    WebDriver driver;

    PageControls pageControls;
    ElementControls elementControls;


    public HomePage(){
        pageControls=new PageControls();
        elementControls=new ElementControls();
        driver= WebDriverManager.getDriver();
        PageFactory.initElements(driver,this);
    }

    public ProductListingPage navigateToSummerDresses(){
        elementControls.waitTillVisible(summerDressesUnderDresses,60);
        elementControls.click(summerDressesUnderDresses);
        return new ProductListingPage();
    }

    public void verifyHomoLogoIsPresent(){
        Assert.assertTrue(elementControls.isVisible(logo),"Logo is not present");
    }

    public void verifySearchQueryPlaceholder(String attributeValue){
        Assert.assertEquals(elementControls.getAttribute(searchQuery,"Placeholder"),attributeValue);
    }

    public void verifyCartIsEmpty(){
        Assert.assertTrue(elementControls.isVisible(cartIsEmpty),"Cart is not empty");
    }

    public void verifyThePhoneNumber(String phoneNumber){
        Assert.assertEquals(elementControls.getText(this.phoneNumber),phoneNumber);
    }

    public void verifySignInTitle(String title){
        Assert.assertEquals(elementControls.getAttribute(signIn,"title"),title);
    }

    public void verifyTheMenus(List<String> data){

        for(int i=0;i<data.size();i++){
            Assert.assertEquals(elementControls.getText(menus.get(i)),data.get(i));
        }

    }

    public void moveToDresses(){
        elementControls.moveToElement(dresses);
    }

    public void clickOnSummerDressUnderDresses(){
        elementControls.waitTillVisible(summerDressesUnderDresses,60);
        elementControls.click(summerDressesUnderDresses);
    }

    public void verifyLogoutIsPresent(){
        Assert.assertEquals(logoutPresent.size(),0);
    }

    public void verifyLogoutIsDisplayed(){
        Assert.assertEquals(elementControls.isVisible(logout),true);
    }


}
