package Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class
WebDriverFactory {
    WebDriver driver = null;
    public WebDriverFactory(){

    }

    public WebDriver createWebDriver(String browser, String url){

        try {
            if(StringUtils.equalsIgnoreCase(browser, "CHROME")){
                System.out.println("Creating chrome session...");
                //System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", "./target/test-classes/bin/chromedriver.exe");
                driver = new ChromeDriver();
            }else if(StringUtils.equalsIgnoreCase(browser, "CHROME_LOCAL")){
                System.out.println("Creating chrome local session...");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("./target/test-classes/bin/chrome-win64");
                System.setProperty("webdriver.chrome.driver", "./target/test-classes/bin/chromedriver.exe");
                driver = new ChromeDriver();
            }else if(StringUtils.equalsIgnoreCase(browser, "CHROME_Capabilities")){
                 /*
                    options.addArguments('--disable-gpu')
                    options.addArguments('--disable-infobars')
                    options.addArguments('--disable-translate')
                    options.addArguments('--dns-prefetch-disable')
                    options.addArguments('--headless')
                    options.addArguments('--ignore-certificate-errors')
                    options.addArguments("--disable-notifications");
                    options.setExperimentalOption("prefs",
                        Collections.singletonMap("unexpectedAlertBehaviour", "accept"));
                */
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");

                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
                prefs.put("download.prompt_for_download", false);
                options.setExperimentalOption("prefs", prefs);
                System.out.println("Creating chrome local session with capabilities...");
                System.setProperty("webdriver.chrome.driver", getCurrentPath() + "\\src\\test\\resources\\bin\\chromedriver.exe");
                driver = new ChromeDriver(options);

            }else if(StringUtils.equalsIgnoreCase(browser, "FIREFOX")){
                System.out.println("Creating firefox session...");
                //System.setProperty("webdriver.firefox.driver", "./geckodriver.exe");
                System.setProperty("webdriver.firefox.driver", "./target/test-classes/bin/geckodriver.exe");
                driver = new FirefoxDriver();
            }else if(StringUtils.equalsIgnoreCase(browser, "CHROME_WDM")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }else if(StringUtils.equalsIgnoreCase(browser, "FIREFOX_WDM")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }else{
                System.out.println(String.format("No drivers configured for this param %s", browser));
            }
        }catch (Exception e){
            throw new SkipException("Skipping the test case, driver is broken...");
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }


    public static String getCurrentPath() {
        return Paths.get("").toAbsolutePath().toString();
    }




}
