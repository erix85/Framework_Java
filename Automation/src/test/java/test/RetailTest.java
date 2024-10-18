package test;
import Configuration.WebDriverFactory;
import Configuration.WebDriverHelper;
import PageObjects.RetailObject;
import Pages.RetailLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class RetailTest {

    WebDriver driver = null;
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    RetailObject retailObject = new RetailObject();
    RetailLoginPage retailLoginPage = new RetailLoginPage();

    @BeforeTest
    public void setUp(){
        driver =  webDriverFactory.createWebDriver("CHROME_WDM","http://saucedemo.com/");

    }
    @Test
    public void seleniumLoginTest () {
        retailLoginPage.login(driver,"standard_user","secret_sauce");

    }
    @AfterTest
    public void tearDown(){
        driver.quit();

    }
}
