package Pages;
import PageObjects.RetailObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RetailLoginPage {
RetailObject retailObject = new RetailObject();
    public void login(WebDriver driver, String user, String password) {

        WebElement  userNameElm = driver.findElement(retailObject.txtUsrNm);
        WebElement  passElm = driver.findElement(retailObject.txtPass);
        WebElement  btnLoginElm = driver.findElement(retailObject.btnLogin);

        userNameElm.sendKeys(user);
        passElm.sendKeys(password);
        btnLoginElm.click();
    }









}
