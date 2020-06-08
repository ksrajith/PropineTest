package pageobjects;

import common.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Date parser related business logic
 */
public class DateParser extends BasePage {

    final static Logger logger = Logger.getLogger(DateParser.class);

    // define globally for future reference
    private String form = ".form-group";
    private String dateField= "input.form-control";
    private String submitBtn= "input.btn.btn-default";

    /**
     *
     * @return form available status
     */
    public boolean isFormAvailable(){
        By formVal= getLocator(form, BY_TYPE.BY_CSSSELECTOR);
        try {
            // Sleep few seconds for iteration results display
            Thread.sleep(SLEEP_TIME_MILL);
        }catch (Exception ex){
                logger.error("Error on form check "+ex);
        }
        if(logger.isDebugEnabled()){
            logger.debug("isFormAvailable " + formVal);
        }
        return formVal != null;
    }

    /**
     *
     * @param date
     * @return date field filled verification
     */
    public String userInsertDateField(String date){
        if(logger.isDebugEnabled()){
            logger.debug("userInsertDateField " + date);
        }
        By dateFldVal  =getLocator(dateField, BY_TYPE.BY_CSSSELECTOR);
        type(dateFldVal, date);
        return getText(dateFldVal);
    }

    /**
     *
     * @return button clicked status
     */
    public boolean isUserClickSubmitBtn(){
        By subBtnVal  =getLocator(submitBtn, BY_TYPE.BY_CSSSELECTOR);
        try {
            click(subBtnVal);
            return true;
        }catch (Exception ex){
            logger.error("Exception on isUserClickSubmitBtn " + ex);
            return false;
        }

    }

    /**
     *
     * @return results of the date submission
     */
    public  String userValidateResultsAs()  {
        String resultsFld = ".col-md-6>div";
        By resultsVal  =getLocator(resultsFld, BY_TYPE.BY_CSSSELECTOR);
        return driver.findElement(resultsVal).getText();
    }
}
