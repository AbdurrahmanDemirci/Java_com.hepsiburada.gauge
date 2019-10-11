package com.hepsiburada.test.driver;

import com.relevantcodes.extentreports.*;
import com.thoughtworks.gauge.*;
import org.apache.commons.io.*;
import org.apache.commons.lang3.*;
import org.openqa.selenium.*;
import org.slf4j.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import static com.hepsiburada.test.utils.PropertyUtils.loadProperties;


public class DriverCreater extends VideoRecorder {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private String baseUrl = "https://www.hepsiburada.com";
    private static String browserName = "chrome";
    private static boolean isFullScreen = true;

    protected static WebDriver driver;
    public ExtentTest extentTest;
    public ExtentReports extent;

    @BeforeSuite
    public void beforeSuite(ExecutionContext executionContext) {

        loadProperties();
        logger.info("****************************************************************************************************" + "\r\n");
        logger.info("---------------------------------------------TEST PLAN---------------------------------------------");
    }

    @BeforeSpec
    public void beforeSpec(ExecutionContext executionContext) {

        logger.info("====================================================================================================" + "\r\n");
        logger.info("------------------------------------------------SPEC-----------------------------------------------");
        logger.info("SPEC FILE NAME: " + executionContext.getCurrentSpecification().getFileName());
        logger.info("SPEC NAME: " + executionContext.getCurrentSpecification().getName());
    }

    @BeforeScenario
    public void beforeScenario(ExecutionContext executionContext) throws MalformedURLException, Exception {

        logger.info("____________________________________________________________________________________________________" + "\r\n");
        logger.info("---------------------------------------------SCENARIO---------------------------------------------");
        logger.info("SCENARIO NAME: " + executionContext.getCurrentScenario().getName());
        logger.info("SCENARIO TAG: " + executionContext.getCurrentScenario().getTags().toString());

        String extentReportFile = System.getProperty("user.dir") + "/lib/reports/extentReportFile.html";
        extent = new ExtentReports(extentReportFile, true);
        extentTest = extent.startTest("SCENARIO TAG: " + executionContext.getCurrentScenario().getTags().toString(), baseUrl);
        extentTest.log(LogStatus.INFO,"SPEC FILE NAME: " + executionContext.getCurrentSpecification().getFileName());
        extentTest.log(LogStatus.INFO,"SPEC NAME: " + executionContext.getCurrentSpecification().getName());
        extentTest.log(LogStatus.INFO,"SCENARIO NAME: " + executionContext.getCurrentScenario().getName());
        extentTest.log(LogStatus.INFO,"SCENARIO TAG: " + executionContext.getCurrentScenario().getTags().toString());

        String key = System.getenv("key");

        if(StringUtils.isEmpty(key)) {

            driver = LocalBrowserExec.LocalExec(browserName);
            if(isFullScreen) {

                driver.manage().window().fullscreen();
                this.startRecording();
            }
        }
        driver.get(baseUrl);
    }

    @BeforeStep
    public void beforeStep(ExecutionContext executionContext) {

        //logger.info(executionContext.getCurrentStep().getText());
        logger.info(executionContext.getCurrentStep().getDynamicText());
        extentTest.log(LogStatus.PASS,"Scenario Steps : " + executionContext.getCurrentStep().getDynamicText());
    }

    @AfterStep
    public void afterStep(ExecutionContext executionContext) throws Exception {

        if (executionContext.getCurrentStep().getIsFailing()) {

            logger.error(executionContext.getCurrentSpecification().getFileName());
            logger.error(executionContext.getCurrentScenario().getName());
            logger.error(executionContext.getCurrentStep().getDynamicText());
            logger.error("Message: " + executionContext.getCurrentStep().getErrorMessage() + "\r\n"
                    + executionContext.getCurrentStep().getStackTrace());
        }

        if (executionContext.getCurrentStep().getIsFailing()) {

            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            File fileName = new File(dateFormat.format(date) +".png");

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File(System.getProperty("user.dir") + "/lib/screenshot/ssImage/error-Scenario:"
                    + executionContext.getCurrentScenario().getName().replace(" ", "") + fileName);
            FileUtils.copyFile(srcFile, targetFile);

            extentTest.log(LogStatus.FAIL, "Fail Scenario Steps : " + executionContext.getCurrentStep().getDynamicText());
            extentTest.log(LogStatus.WARNING,"Warning Scenario Spec : " + executionContext.getCurrentScenario().getName());
            extentTest.log(LogStatus.WARNING,"Warning Scenario Message : " + executionContext.getCurrentStep().getErrorMessage());
            extentTest.log(LogStatus.WARNING, "Warning ScreenCapture : " + extentTest.addScreenCapture(String.valueOf(targetFile)));
        }
    }

    @AfterScenario
    public void afterScenario(ExecutionContext executionContext) throws Exception {

        quitDriver();
        if (executionContext.getCurrentScenario().getIsFailing()) {

            logger.info("_____________________________________________TEST FAİL_____________________________________________");
            extent.endTest(extentTest);
            extentTest.log(LogStatus.WARNING, "TEST WARNING Browser closed");
            extent.flush();
        } else {
            logger.info("________________________________________TEST SUCCESSFULL________________________________________");
            extent.endTest(extentTest);
            extentTest.log(LogStatus.PASS, "TEST SUCCESSFULL Browser closed");
            extent.flush();
        }

        logger.info("____________________________________________________________________________________________________" + "\r\n");
    }

    @AfterSpec
    public void afterSpec(ExecutionContext executionContext) {

        logger.info("====================================================================================================" + "\r\n");
    }

    @AfterSuite
    public void afterSuite(ExecutionContext executionContext) {

        logger.info("****************************************************************************************************" + "\r\n");
    }

    private void quitDriver() throws Exception {

        if(driver != null){
            this.stopRecording();
            driver.quit();
            logger.info("________________________________________SCENARIO FINISHED________________________________________");
        }
    }
}
