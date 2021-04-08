package Tests;

import PageObject.AuthenticationPage;
import PageObject.HomePage;
import PageObject.ProductDetailsPage;
import PageObject.ProductListingPage;
import Utils.PropertiesFileReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Listeners(Utils.Listeners.class)
public class Scenario extends BaseTest {


    ProductListingPage productListingPage;


    HomePage homePage;


    ProductDetailsPage productDetailsPage;

    AuthenticationPage authenticationPage;

   @Test
   public void TC001(){
       homePage=new HomePage();
       homePage.verifyHomoLogoIsPresent();
       homePage.verifySearchQueryPlaceholder("Search");
       homePage.verifyCartIsEmpty();
       homePage.verifyThePhoneNumber("0123-456-789");
       homePage.verifySignInTitle("Log in to your customer account");
       List<String> menus=new ArrayList<>();
       menus.add("WOMEN");
       menus.add("DRESSES");
       menus.add("T-SHIRTS");
       homePage.verifyTheMenus(menus);
   }


   @Test
   public void TC002(){
//       Actions actions=new Actions(driver);
//       actions.moveToElement(homePage.dresses).build().perform();
//       WebDriverWait wait=new WebDriverWait(driver,60);
//       wait.until(ExpectedConditions.visibilityOf(homePage.summerDressesUnderDresses));
       homePage.moveToDresses();
       productListingPage=homePage.navigateToSummerDresses();

       //homePage.clickOnSummerDressUnderDresses();
       List<String> menus=new ArrayList<>();
       menus.add("WOMEN");
       menus.add("DRESSES");
       menus.add("T-SHIRTS");
       homePage.verifyTheMenus(menus);
       homePage.verifyLogoutIsPresent();
       productListingPage.verifyCartHasNumberOfItems("0");
       productListingPage.selectListView();
       productListingPage.openProductWithHighestPrice();
   }

   @Test
   public void TC003(){
       productDetailsPage=new ProductDetailsPage();
       productDetailsPage.selectSizeAs("M");
       productDetailsPage.addToCart();
       productDetailsPage.verifyTotalProductPrice(ProductListingPage.highestPrice);
       productDetailsPage.verifyProductPrice(ProductListingPage.highestPrice);

   }

   @Test
   public void TC004(){
       productDetailsPage.clickOnContinueShopping();
       productListingPage.verifyCartHasNumberOfItems("1");
   }

   @Test
   public void TC005(){
       productListingPage.moveToCartQuantity();
       productListingPage.clickOnOrderCart();
       productDetailsPage.clickOnProceedToCheckout();
       authenticationPage=new AuthenticationPage();
       authenticationPage.login(PropertiesFileReader.properties.getProperty("Username"),PropertiesFileReader.properties.getProperty("Password"));
       homePage.verifyLogoutIsDisplayed();
   }

   @AfterClass
    public void tearUp(){
       driver.quit();
   }

}
