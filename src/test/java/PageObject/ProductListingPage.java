package PageObject;

import Utils.ElementControls;
import Utils.PageControls;
import Utils.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductListingPage {
    WebDriver driver;

    @FindBy(id = "selectProductSort")
    public WebElement sortBy;

    @FindBy(className = "icon-th-list")
    public WebElement listView;

    @FindBy(xpath = "(//button[contains(@class,'compare')])[1]")
    public WebElement compareButton;

    @FindBy(xpath = "(//button[contains(@class,'compare')])[1]//strong")
    public WebElement noOfCompareItems;

    @FindBy(xpath = "//div[contains(@class,'right')]//span[@itemprop='price']")
    List<WebElement> allTheItemsPrice;

    @FindBy(xpath = "//span[contains(@class,'ajax_cart_quantity ')]")
    public WebElement cartQuantity;

    @FindBy(id = "button_order_cart")
    public WebElement orderCart;

    private ElementControls elementControls;
    private PageControls pageControls;

    public ProductListingPage(){
        elementControls=new ElementControls();
        pageControls=new PageControls();
        driver= WebDriverManager.getDriver();
        PageFactory.initElements(driver,this);
    }

    public void sortProductBy(String sortByCondition){

        elementControls.selectDropDownByVisibleText(sortBy,sortByCondition);
    }

    public void selectListView(){
        elementControls.click(listView);
        try {
            Thread.sleep(4000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static double highestPrice;
    public void openProductWithHighestPrice(){
        List<Double> prices=new ArrayList<>();
        for(WebElement element:allTheItemsPrice)
            prices.add(Double.parseDouble(elementControls.getText(element).trim().replaceAll("\\$","")));
        Collections.sort(prices);
        Collections.reverse(prices);
        System.out.println(prices);
        System.out.println("//div[div[contains(@class,'right')]//span[contains(text(),'"+prices.get(0)+"')]]/div[contains(@class,'center-block')]//a[@class='product-name']");
        WebElement element=elementControls.find(By.xpath("//div[div[contains(@class,'right')]//span[contains(text(),'"+prices.get(0)+"')][@itemprop='price']]/div[contains(@class,'center-block')]//a[@class='product-name']"));
        elementControls.click(element);
        highestPrice=prices.get(0);

    }

    public void verifyCartHasNumberOfItems(String noOfItems){
        Assert.assertEquals(elementControls.getText(noOfCompareItems),noOfItems);
    }

    public void moveToCartQuantity(){
        elementControls.moveToElement(cartQuantity);
        elementControls.waitTillVisible(orderCart,60);
    }

    public void clickOnOrderCart(){
        elementControls.click(orderCart);
    }

}
