package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserSetup {

    final static Logger logger = Logger.getLogger(BrowserSetup.class);

    /**
     * creates WebDriver by browser name
     * @param browser name
     * @return WebDriver
     */
    public static WebDriver setBrowser(String browser){
        WebDriver driver = null;

        switch(browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver= new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("No Browser found");
                break;

        }

        if(logger.isDebugEnabled()){
            logger.debug("WebDriver returns for : " + browser);
        }
        return driver;
    }
}
