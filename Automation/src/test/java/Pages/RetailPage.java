package Pages;
import PageObjects.RetailObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RetailPage {
RetailObject retailPageObject = new RetailObject();

    public void login(WebDriver driver, String user, String password) {

        WebElement  userNameElm = driver.findElement(retailPageObject.txtUsrNm);
        WebElement  passElm = driver.findElement(retailPageObject.txtPass);
        WebElement  btnLoginElm = driver.findElement(retailPageObject.btnLogin);

        userNameElm.sendKeys(user);
        passElm.sendKeys(password);
        btnLoginElm.click();
    }









}
