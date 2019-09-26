package com.hepsiburada.test.driver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import java.util.*;


public class LocalBrowserExec {

    private static WebDriver driver;
    public static WebDriver LocalExec(String browser) throws Exception {

        SetBrowserForOS.setDriverPath(browser);

        switch (browser.toLowerCase(Locale.ENGLISH)) {

            case "chrome" :
                driver = getChromeDriver();
                break;
            case "firefox" :
                driver = getFirefoxDriver();
                break;
            default:
                throw new Exception ("Error");
        }

        return driver;
    }

    private static ChromeDriver getChromeDriver() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");

        ChromeOptions options = new ChromeOptions();
        // options
        return new ChromeDriver(chromeOptions);
    }

    private static FirefoxDriver getFirefoxDriver() {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        //firefoxOptions.setLogLevel(FirefoxDriverLogLevel.INFO);
        // options
        return new FirefoxDriver(firefoxOptions);
    }
}
