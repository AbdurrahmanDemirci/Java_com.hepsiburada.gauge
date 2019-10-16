package com.hepsiburada.test.methods;


import com.hepsiburada.test.helper.ElementHelper;
import com.hepsiburada.test.helper.StoreHelper;
import com.hepsiburada.test.model.ElementInfo;
import com.thoughtworks.gauge.*;
import org.apache.commons.io.IOUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.*;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import com.saha.slnarch.core.element.*;
//import javax.lang.model.element.Element;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.*;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.regex.*;


public class Methods {
    
    @Inject
    Element element;
    public static int DEFAULT_MAX_ITERATION_COUNT = 150;
    public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;
    private static String SAVED_ATTRIBUTE;
    private Logger logger = LoggerFactory.getLogger(getClass());
    WebDriver driver;
    FluentWait<WebDriver> wait;

    public Methods(WebDriver driver) {

        this.driver = driver;
        wait = setFluentWait();
    }

    public void checkElementExistsThenClick(String key) {

        //getElementWithKeyIfExists(key); --> yerine setFluentWait kullanıldı.
        clickElement(key);

    }

    public void waitBySeconds(int seconds) {

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitByMilliSeconds(long milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void clickElement(String key) {
        
        if (!key.equals("")) {
            findElement(key).click();
        }
    }

    public void printPageSource() {

        System.out.println(driver.getPageSource());
    }

    public void getElementWithKeyIfExistsTwo(String key) {

        findElement(key);
    }

    public WebElement getElementWithKeyIfExists(String key) {

        WebElement webElement;

        int loopCount = 0;
        while (loopCount < 5) {
            try {
                webElement = findElementWithKey(key);
                return webElement;
            } catch (WebDriverException e) {
            }
            loopCount++;
            waitByMilliSeconds(2000);
        }
        Assert.fail("Element: '" + key + "' doesn't exist.");
        return null;
    }

    public WebElement findElementWithKey(String key) {

        return findElement(key);
    }

    public void goToUrl(String url) {

        driver.get(url);
    }

    public void checkURLContainsRepeat(String expectedURL) {

        int loopCount = 0;
        String actualURL = "";

        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            actualURL = driver.getCurrentUrl();

            if (actualURL != null && actualURL.contains(expectedURL)) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail(
                "Actual URL doesn't match the expected." + "Expected: " + expectedURL + ", Actual: "
                        + actualURL);
    }

    public void waitElementLoadWithCss(String css) {

        int loopCount = 0;

        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (driver.findElements(By.cssSelector(css)).size() > 0) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element: '" + css + "' doesn't exist.");
    }

    public void waitElementLoadWithXpath(String xpath) {

        int loopCount = 0;

        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (driver.findElements(By.xpath(xpath)).size() > 0) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element: '" + xpath + "' doesn't exist.");
    }

    public void getElementWithKeyIfExistsWithMessage(String key, String message) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);

        int loopCount = 0;

        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (driver.findElements(by).size() > 0) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail(message);
    }

    public void checkElementNotExists(String key) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);

        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (driver.findElements(by).size() == 0) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element '" + key + "' still exist.");
    }

    public void uploadFile(String path, String key) {

        String pathString = System.getProperty("user.dir") + "/";
        pathString = pathString + path;
        findElement(key).sendKeys(pathString);
    }

    public void writeTextToElement(String text, String key) {

        if (!key.equals("")) {
            findElement(key).sendKeys(text);
        }
    }

    public void javascriptClickerWithCss(String css) {

        Assert.assertTrue("Element bulunamadı", isDisplayedBy(By.cssSelector(css)));
        javaScriptClicker(driver, driver.findElement(By.cssSelector(css)));
    }

    public void javascriptClickerWithXpath(String xpath) {

        Assert.assertTrue("Element bulunamadı", isDisplayedBy(By.xpath(xpath)));
        javaScriptClicker(driver, driver.findElement(By.xpath(xpath)));
    }

    public void checkElementAttributeExists(String key, String attribute) {

        WebElement element = findElement(key);
        int loopCount = 0;

        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (element.getAttribute(attribute) != null) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element DOESN't have the attribute: '" + attribute + "'");
    }

    public void checkElementAttributeNotExists(String key, String attribute) {

        WebElement element = findElement(key);
        int loopCount = 0;

        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (element.getAttribute(attribute) == null) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element STILL have the attribute: '" + attribute + "'");
    }

    public void checkElementAttributeEquals(String key, String attribute, String expectedValue) {

        WebElement element = findElement(key);
        String actualValue;

        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            actualValue = element.getAttribute(attribute).trim();
            if (actualValue.equals(expectedValue)) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element's attribute value doesn't match expected value");
    }

    public void checkElementAttributeContains(String key, String attribute, String expectedValue) {

        WebElement element = findElement(key);
        String actualValue;

        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            actualValue = element.getAttribute(attribute).trim();
            if (actualValue.contains(expectedValue)) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element's attribute value doesn't contain expected value");
    }

    public void setElementAttribute(String value, String attributeName, String key){

        String attributeValue = findElement(key).getAttribute(attributeName);
        findElement(key).sendKeys(attributeValue, value);
    }

    public void setElementAttributeWithJs(String value, String attributeName, String key) {

        WebElement webElement = findElement(key);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + value + "')",
                webElement);
    }

    public void clearInputArea(String key) {

        findElement(key).clear();
    }

    public void clearInputAreaWithBackspace(String key) {

        WebElement element = findElement(key);
        element.clear();
        element.sendKeys("a");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.BACK_SPACE).build().perform();
    }

    public void chromeZoomOut(String value) {

        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("document.body.style.zoom = '" + value + "%'");
    }

    public void chromeOpenNewTab() {

        ((JavascriptExecutor) driver).executeScript("window.open()");
    }

    public void chromeFocusTabWithNumber(int number) {

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(number - 1));
    }

    public void chromeFocusLastTab() {

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public void chromeFocusFrameWithNumber(String key) {

        WebElement webElement = findElement(key);
        driver.switchTo().frame(webElement);
    }

    public void saveAttributeValueOfElement(String attribute, String key) {

        SAVED_ATTRIBUTE = findElement(key).getAttribute(attribute);
        System.out.println("Saved attribute value is: " + SAVED_ATTRIBUTE);
    }

    public void writeSavedAttributeToElement(String key) {

        findElement(key).sendKeys(SAVED_ATTRIBUTE);
    }
    
    public void checkElementCount(int expectedAmount, String key) {
        
        int loopCount = 0;

        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (expectedAmount == findElements(key).size()) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail(
                "Expected element count failed. Expected amount:" + expectedAmount + " Actual amount:"
                        + findElements(key).size());
    }

    public void checkElementContainsText(String key, String expectedText) {

        Boolean containsText = findElement(key).getText().contains(expectedText);
        Assert.assertTrue("Expected text is not contained", containsText);
    }

    public void refreshPage() {

        driver.navigate().refresh();
    }

    public void writeRandomValueToElement(String key) {

        findElement(key).sendKeys(randomString(15));
    }

    public void writeRandomValueToElement(String key, String startingText) {

        String randomText = startingText + randomString(15);
        findElement(key).sendKeys(randomText);
    }

    public void UUIDCreate(String email, String key) {

        if (!key.equals("")) {
            email = UUID.randomUUID().toString() + "@gmail.com";
            findElement(key).sendKeys(email);
            System.out.println("Email : " + " " + email);
        }
    }

    public void printElementText(String css) {

        System.out.println(driver.findElement(By.cssSelector(css)).getText());
    }

    public void sendKeysWithFocus(String text, String key) {

        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(key));
        actions.click();
        actions.sendKeys(text);
        actions.build().perform();
    }

    public void clickElementWithFocus(String key) {

        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(key));
        actions.click();
        actions.build().perform();
    }

    public void writeDateWithDaysAdded(int daysToAdd, String key) {

        if (!key.equals("")) {
            findElement(key).sendKeys(getCurrentDateThenAddDays(daysToAdd));
        }
    }

    public void acceptChromeAlertPopup() {

        driver.switchTo().alert().accept();
    }

    public void checkAndClick(String key) {

        By by = getBy(key);
        if (driver.findElements(by).size() != 0) {
            wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        }
    }

    public void sendKeyToElementTAB(String key) {

        findElement(key).sendKeys(Keys.TAB);
    }

    public void sendKeyToElementBACKSPACE(String key) {

        findElement(key).sendKeys(Keys.BACK_SPACE);
    }

    public void sendKeyToElementESCAPE(String key) {

        findElement(key).sendKeys(Keys.ESCAPE);
    }

    public void sendFile(String key, String path) throws Exception {

        String myString = System.getProperty("user.dir") + "/" + path;
        System.out.println(myString);
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        waitBySeconds(2);
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);

        //Fullscreen kontrolü
        java.awt.Dimension dimension2 = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(dimension2.width + " " + dimension2.height);
        org.openqa.selenium.Dimension dimension3 = driver.manage().window().getSize();
        System.out.println(dimension3.width + " " + dimension3.height);

        if (dimension2.width == dimension3.width && dimension2.height == dimension3.height) {
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_META);
        }

        clickElementBy(key);

        java.awt.Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        robot.delay(3000);
        robot.mouseMove(dimension.width / 2, dimension.height / 2);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        waitBySeconds(2);
    }

    public void randomClickInList(String key) {

        Random r = new Random();
        List<WebElement> elementList = findElements(key);
        int i = r.nextInt(elementList.size());
        WebElement element = elementList.get(i);
        if (element.getText() != "Bilet, Tatil & Eğlence") {
            element.click();
            waitBySeconds(5);
            if (driver.findElements(By.cssSelector("span[class='closeBtn']")).size() != 0) {
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class='closeBtn']"))).click();
                waitBySeconds(2);
            } else {
                System.out.println("Element is Absent");
            }
            if (driver.findElements(By.cssSelector(".filterItem.parent > ul > li > a")).size() != 0) {
                List<WebElement> subList = driver.findElements(By.cssSelector(".filterItem.parent > ul > li > a"));
                subList.get(r.nextInt(subList.size())).click();
                waitBySeconds(2);
            }
        }
    }

    public void findElementAndSave(String key, String saveKey) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        StoreHelper.INSTANCE.saveValue(saveKey,
                element.find(ElementHelper.getElementInfoToBy(elementInfo))
                        .getElement(elementInfo.getIndex())
                        .getText());
    }

    public void findElementAndCompare(String key, String saveKey) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        Assert.assertEquals(
                StoreHelper.INSTANCE.getValue(saveKey).replace("\n", "").replace("\r", "")
                        .replaceAll("\\s+", ""),
                element.find(ElementHelper.getElementInfoToBy(elementInfo))
                        .getElement(elementInfo.getIndex())
                        .getText().replace("\n", "").replace("\r", "").replaceAll("\\s+", ""));
    }

    public void findElementAndStartsWith(String key, String saveKey) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        Assert.assertTrue(
                element.find(ElementHelper.getElementInfoToBy(elementInfo))
                        .getElement(elementInfo.getIndex())
                        .getText().contains(StoreHelper.INSTANCE.getValue(saveKey)));
    }

    public void dowlandUrlFile (String url,String filePath) {

        try {
            downloadUsingNIO(url, filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadUsingStream(String urlStr, String file) throws IOException {

        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        int count=0;

        while((count = bis.read(buffer,0,1024)) != -1) {
            fis.write(buffer, 0, count);
        }

        fis.close();
        bis.close();
    }

    private static void downloadUsingNIO(String urlStr, String file) throws IOException {

        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        fos.close();
        rbc.close();
    }

    public void deletefilePath (String deletefilePath) {

        Path fileToDeletePath = Paths.get(deletefilePath);

        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSMSToken(String phoneNo, String phoneKey) {

        String password = null;
        String smsContent = null;
        int size = 6;

        try {
            InputStream in = new URL("http://dev.testinium.com:10256/code?phone=" + phoneNo).openStream();
            try {
                smsContent = IOUtils.toString(in);
                Pattern r = Pattern.compile("([0-9]){" + size + "}");
                Matcher m = r.matcher(smsContent);
                if (m.find()) {
                    password = m.group(0).trim();
                    System.out.println("sms pass : " + password);
                    StoreHelper.INSTANCE.saveValue(phoneKey, password);
                }
            } finally {
                IOUtils.closeQuietly(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public void sendKeysBy(String key, String text) {

        findElement(key).sendKeys(text);
    }

    private void hoverElement(WebElement element) {

        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void hoverElementBy(String key) {

        WebElement webElement = findElement(key);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }

    public void clickElement(WebElement element) {

        element.click();
    }

    public void clickElementBy(String key) {

        findElement(key).click();
    }

    private WebElement findElement(String key) {

        By by = getBy(key);
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        if ((!webElement.equals(""))) {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            WebDriverWait webDriverWait = new WebDriverWait(driver,0);
            webElement = webDriverWait
                    .until(ExpectedConditions.presenceOfElementLocated(infoParam));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    webElement);
        }
        return webElement;
    }

    private List<WebElement> findElements(String key) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    private By getBy(String key) {

        logger.info("Element " + key + " değerinde tutuluyor");
        return ElementHelper.getElementInfoToBy(getElementInfo(key));
    }

    private ElementInfo getElementInfo(String key) {

        return StoreHelper.INSTANCE.findElementInfoByKey(key);
    }

    public boolean isDisplayedBy(By by) {

        return driver.findElement(by).isDisplayed();
    }

    public boolean isDisplayed(WebElement element) {

        return element.isDisplayed();
    }

    public static String getSavedAttribute() {

        return SAVED_ATTRIBUTE;
    }

    public String getElementText(String key) {

        return findElement(key).getText();
    }

    public String getElementAttributeValue(String key, String attribute) {

        return findElement(key).getAttribute(attribute);
    }

    public String getPageSource() {

        return driver.switchTo().alert().getText();
    }

    public void javaScriptClicker(WebDriver driver, WebElement element) {

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);", element);
    }

    public String randomString(int stringLength) {

        Random random = new Random();
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUWVXYZabcdefghijklmnopqrstuwvxyz0123456789".toCharArray();
        String stringRandom = "";
        for (int i = 0; i < stringLength; i++) {

            stringRandom = stringRandom + String.valueOf(chars[random.nextInt(chars.length)]);
        }

        return stringRandom;
    }

    public String getCurrentDateThenAddDays(int daysToAdd) {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date currentDate = new Date();
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DAY_OF_MONTH, daysToAdd);

        return dateFormat.format(c.getTime());
    }

    public FluentWait<WebDriver> setFluentWait() {

        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(3000))
                .ignoring(NoSuchElementException.class);

        return fluentWait;
    }


    public void imageDiff(String imgUrl, String imgPath) {

        {
            try {

                BufferedImage img1 = ImageIO.read(new URL(imgUrl));
                BufferedImage img2 = ImageIO.read(new File(System.getProperty("user.dir") + "/lib/ImageDifference/"+ imgPath ));

                double p = getDifferencePercent(img1, img2);
                logger.info("Diff percent: "  + p );
                System.out.println("__________Diff percent: " + p);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private double getDifferencePercent (BufferedImage img1, BufferedImage img2) {

        int width = img1.getWidth();
        int height = img1.getHeight();
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();

        if (width != width2 || height != height2) {

            throw new IllegalArgumentException(String.format("__________Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
        }

        long diff = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
            }
        }
        long maxDiff = 3L * 255 * width * height;

        return 100.0 * diff / maxDiff;
    }

    private int pixelDiff (int rgb1, int rgb2) {

        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 =  rgb1        & 0xff;
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >>  8) & 0xff;
        int b2 =  rgb2        & 0xff;
        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
    }

    public void findElementSize (String key, int widthW, int hightH ) {

        if (!key.equals("")) {

            int width = findElement(key).getSize().getWidth();
            int hight = findElement(key).getSize().getHeight();

            System.out.println("__________Width :  "+ width + "  "+ "__________Hight :  "+hight);

            Assert.assertEquals(width, widthW);
            Assert.assertEquals(hight, hightH);
        }

    }

    public void searchAndAddToCartTable(Table table) {

        List<String> listColumnNames =  table.getColumnNames();
        List<String> listSearch = table.getColumnValues(listColumnNames.get(0));

        for(int i = 0; i < listSearch.size(); i++){
            clearInputArea("search-textbox_HomePageHeader");
            sendKeysWithFocus(listSearch.get(i),"search-textbox_HomePageHeader");
            clickElement("search-button_HomePageHeader");
            checkURLContainsRepeat("https://www.hepsiburada.com/ara?q=" + listSearch.get(i));
            checkAndClick("searchBookClosePopup_ResultSearchBook");
            findElementSize("elementSizePLP_searchResult",280,414);
            clickElement("searchBookSelectProduct_ResultSearchBook");
            clickElement("addToCart-button_ProductDetailPage");
            waitByMilliSeconds(1500);
        }
    }

}
