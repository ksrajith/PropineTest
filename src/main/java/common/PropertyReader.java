package common;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Read application.properties file to get browser name
 */

public class PropertyReader {

    final static Logger logger = Logger.getLogger(PropertyReader.class);
    InputStream inputStream;
    String globleBrowser="";

    public String getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "application.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            globleBrowser = prop.getProperty("browser");
            if(logger.isDebugEnabled()){
                logger.debug("browser name picked :" + globleBrowser);
            }
        } catch (Exception e) {
            logger.error("application.properties file read error", e);
        } finally {
            inputStream.close();
        }
        if(logger.isDebugEnabled()){
            logger.debug("browser name is : " + globleBrowser+ " returned successfully");
        }
        return globleBrowser;
    }
}
