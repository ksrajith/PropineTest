package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;



public class BasePage {

    final static Logger logger = Logger.getLogger(BasePage.class);
    public static WebDriver driver;
    public static final int TIME_OUT = 4;
    public static final int SLEEP_TIME_MILL=4000;

    public static void browser()  {
        try{
            PropertyReader pr = new PropertyReader();
            driver = BrowserSetup.setBrowser(pr.getPropValues());
        }catch (Exception ex){
            logger.error("Browser not functioning :", ex);
        }

    }

    public enum BY_TYPE {
        BY_XPATH, BY_LINKTEXT, BY_ID, BY_CLASSNAME, BY_NAME, BY_CSSSELECTOR, BY_PARTIALLINKTEXT, BY_TAGNAME
    };

    public static By getLocator(String locator, BY_TYPE type) {
        switch (type) {
            case BY_XPATH:
                return By.xpath(locator);
            case BY_LINKTEXT:
                return By.linkText(locator);
            case BY_ID:
                return By.id(locator);
            case BY_CSSSELECTOR:
                return By.cssSelector(locator);
            case BY_CLASSNAME:
                return By.className(locator);
            case BY_NAME:
                return By.name(locator);
            case BY_PARTIALLINKTEXT:
                return By.partialLinkText(locator);
            case BY_TAGNAME:
                return By.tagName(locator);

        }
        throw new IllegalArgumentException(
                "Invalid By Type, Please provide correct locator type");

    }

    public static void click(By locator, int ...seconds) throws Exception{
        if(logger.isDebugEnabled()){
            logger.debug("Trying to clicks on : " + locator);
        }
        int time = (seconds.length==0) ? TIME_OUT : seconds[0];
        WebDriverWait wait = new WebDriverWait(driver, time);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public static void type(By locator, String value){

        try{
            if(logger.isDebugEnabled()){
                logger.debug("Trying to type "+value+" on field: " + locator);
            }

            WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            setTimeOut(TIME_OUT);
            element.click();
            element.sendKeys(value);
        }catch(NoSuchElementException ex){
            logger.error("type text NoSuchElementException field", ex);
        } catch (Exception e){
            logger.error("type text Exception field", e);
        }

    }

    public static String getText(By locator){
        try {
            if(logger.isDebugEnabled()){
                logger.debug("Trying to get text " + locator);
            }

            WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
            wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(locator),"value"));
            setTimeOut(TIME_OUT);
            return driver.findElement(locator).getAttribute("value");
        }catch (Exception ex){
            logger.error("GetText failed ", ex);
            return null;
        }
    }

    public static void setTimeOut(long timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(timeoutInSeconds, TimeUnit.SECONDS);
    }

}
