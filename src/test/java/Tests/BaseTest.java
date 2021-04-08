package Tests;

import Utils.PropertiesFileReader;
import Utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        PropertiesFileReader propertiesFileReader=new PropertiesFileReader("./ApplicationSettings.properties");
        propertiesFileReader.readFile();
        System.setProperty("webdriver.chrome.driver",PropertiesFileReader.properties.getProperty("Driver.Chrome"));
        WebDriverManager.setDriver(PropertiesFileReader.properties.getProperty("Browser"));
        driver=WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(PropertiesFileReader.properties.getProperty("Application.URL"));
    }
}
