package com.hepsiburada.test;

import com.hepsiburada.test.driver.DriverCreater;
import com.hepsiburada.test.methods.Methods;
import com.thoughtworks.gauge.*;
import org.apache.log4j.PropertyConfigurator;

public class StepImplementation extends DriverCreater {

    Methods methods;


    public StepImplementation() {

        PropertyConfigurator.configure(StepImplementation.class.getClassLoader().getResource("log4j.properties"));
        methods = new Methods(driver);

    }

    @Step({"Wait <value> seconds",
            "<int> saniye bekle"})
    public void waitBySeconds(int seconds) {

        methods.waitBySeconds(seconds);
    }

    @Step({"<long> wait milliseconds",
            "<long> milisaniye bekle"})
    public void waitByMilliSeconds(long milliseconds) {

        methods.waitByMilliSeconds(milliseconds);
    }

    @Step({"Click to element <key>",
            "Elementine tıkla <key>"})
    public void clickElement(String key) {

        methods.clickElement(key);
    }

    @Step("print page source")
    public void printPageSource() {

        methods.printPageSource();
    }

    @Step({"Wait for element then click <key>",
            "Elementi bekle ve sonra tıkla <key>"})
    public void checkElementExistsThenClick(String key) {

        methods.checkElementExistsThenClick(key);
    }

    @Step({"Check if element <key> exists",
            "Wait for element to load with key <key>",
            "Element var mı kontrol et <key>",
            "Elementinin yüklenmesini bekle <key>"})
    public void getElementWithKeyIfExists(String key) {

        methods.getElementWithKeyIfExists(key);
    }

    @Step({"Go to <url> address",
            "<url> adresine git"})
    public void goToUrl(String url) {

        methods.goToUrl(url);
    }

    @Step({"Check if current URL contains the value <expectedURL>",
            "Şuanki URL <url> değerini içeriyor mu kontrol et"})
    public void checkURLContainsRepeat(String expectedURL) {

        methods.checkURLContainsRepeat(expectedURL);
    }

    @Step({"Wait for element to load with css <css>",
            "Elementinin yüklenmesini bekle css <css>"})
    public void waitElementLoadWithCss(String css) {

        methods.waitElementLoadWithCss(css);
    }

    @Step({"Wait for element to load with xpath <xpath>",
            "Elementinin yüklenmesini bekle xpath <xpath>"})
    public void waitElementLoadWithXpath(String xpath) {

        methods.waitElementLoadWithXpath(xpath);
    }

    @Step({"Check if element <key> exists else print message <message>",
            "Element <key> var mı kontrol et yoksa hata mesajı ver <message>"})
    public void getElementWithKeyIfExistsWithMessage(String key, String message) {

        methods.getElementWithKeyIfExistsWithMessage(key, message);
    }

    @Step({"Check if element <key> not exists",
            "Element yok mu kontrol et <key>"})
    public void checkElementNotExists(String key) {

        methods.checkElementNotExists(key);
    }

    @Step({"Upload file in project <path> to element <key>",
            "Proje içindeki <path> dosyayı elemente upload et <key>"})
    public void uploadFile(String path, String key) {

        methods.uploadFile(path, key);
    }

    @Step({"Write value <text> to element <key>",
            "<text> textini elemente yaz <key>"})
    public void writeTextiniToElement(String text, String key) {

        methods.writeTextToElement(text, key);
    }

    @Step({"Click with javascript to css <css>",
            "Javascript ile css tıkla <css>"})
    public void javascriptClickerWithCss(String css) {

        methods.javascriptClickerWithCss(css);
    }

    @Step({"Click with javascript to xpath <xpath>",
            "Javascript ile xpath tıkla <xpath>"})
    public void javascriptClickerWithXpath(String xpath) {

        methods.javascriptClickerWithXpath(xpath);
    }

    @Step({"Check if element <key> has attribute <attribute>",
            "<key> elementi <attribute> niteliğine sahip mi"})
    public void checkElementAttributeExists(String key, String attribute) {

        methods.checkElementAttributeExists(key, attribute);
    }

    @Step({"Check if element <key> not have attribute <attribute>",
            "<key> elementi <attribute> niteliğine sahip değil mi"})
    public void checkElementAttributeNotExists(String key, String attribute) {

        methods.checkElementAttributeNotExists(key, attribute);
    }

    @Step({"Check if <key> element's attribute <attribute> equals to the value <expectedValue>",
            "<key> elementinin <attribute> niteliği <value> değerine sahip mi"})
    public void checkElementAttributeEquals(String key, String attribute, String expectedValue) {

        methods.checkElementAttributeEquals(key, attribute, expectedValue);
    }

    @Step({"Check if <key> element's attribute <attribute> contains the value <expectedValue>",
            "<key> elementinin <attribute> niteliği <value> değerini içeriyor mu"})
    public void checkElementAttributeContains(String key, String attribute, String expectedValue) {

        methods.checkElementAttributeContains(key, attribute, expectedValue);
    }

    @Step({"Write <value> to <attributeName> of element <key>",
            "<value> değerini <attribute> niteliğine <key> elementi için yaz"})
    public void setElementAttribute(String value, String attributeName, String key) {

        methods.setElementAttribute(value, attributeName, key);
    }

    @Step({"Write <value> to <attributeName> of element <key> with Js",
            "<value> değerini <attribute> niteliğine <key> elementi için JS ile yaz"})
    public void setElementAttributeWithJs(String value, String attributeName, String key) {

        methods.setElementAttributeWithJs(value, attributeName, key);
    }

    @Step({"Clear text of element <key>",
            "<key> elementinin text alanını temizle"})
    public void clearInputArea(String key) {

        methods.clearInputArea(key);
    }

    @Step({"Clear text of element <key> with BACKSPACE",
            "<key> elementinin text alanını BACKSPACE ile temizle"})
    public void clearInputAreaWithBackspace(String key) {

        methods.clearInputAreaWithBackspace(key);
    }

    @Step({"Change page zoom to <value>%",
            "Sayfanın zoom değerini değiştir <value>%"})
    public void chromeZoomOut(String value) {

        methods.chromeZoomOut(value);
    }

    @Step({"Click to <key> element then load document from path <path>",
            "<key> öğesini tıklatarak belgeyi <path> yolundan yükleyin"})
    public void sendFile(String key, String path) throws Exception {

        methods.sendFile(key, path);
    }

    @Step({"Open new tab",
            "Yeni sekme aç"})
    public void chromeOpenNewTab() {

        methods.chromeOpenNewTab();
    }

    @Step({"Focus on tab number <number>",
            "<number> numaralı sekmeye odaklan"})//Starting from 1
    public void chromeFocusTabWithNumber(int number) {

        methods.chromeFocusTabWithNumber(number);
    }

    @Step({"Focus on last tab",
            "Son sekmeye odaklan"})
    public void chromeFocusLastTab() {

        methods.chromeFocusLastTab();
    }

    @Step({"Focus on frame with <key>",
            "Frame'e odaklan <key>"})
    public void chromeFocusFrameWithNumber(String key) {

        methods.chromeFocusFrameWithNumber(key);
    }

    @Step({"Save attribute <attribute> value of element <key>",
            "<attribute> niteliğini sakla <key> elementi için"})
    public void saveAttributeValueOfElement(String attribute, String key) {

        methods.saveAttributeValueOfElement(attribute, key);
    }

    @Step({"Write saved attribute value to element <key>",
            "Kaydedilmiş niteliği <key> elementine yaz"})
    public void writeSavedAttributeToElement(String key) {

        methods.writeSavedAttributeToElement(key);
    }

    @Step({"Check if <int> amount element exists with same key <key>",
            "<int> miktar elementi <key> ile aynı anahtarda olup olmadığını kontrol et"})
    public void checkElementCount(int expectedAmount, String key) {

        methods.checkElementCount(expectedAmount,key);
    }

    @Step({"Check if element <key> contains text <expectedText>",
            "<key> elementi <text> değerini içeriyor mu kontrol et"})
    public void checkElementContainsText(String key, String expectedText) {

        methods.checkElementContainsText(key, expectedText);
    }

    @Step({"Refresh page",
            "Sayfayı yenile"})
    public void refreshPage() {

        methods.refreshPage();
    }

    @Step({"Write random value to element <key>",
            "<key> elementine random değer yaz"})
    public void writeRandomValueToElement(String key) {

        methods.writeRandomValueToElement(key);
    }

    @Step({"Write random value to element <key> starting with <text>",
            "<key> elementine <text> değeri ile başlayan random değer yaz"})
    public void writeRandomValueToElement(String key, String startingText) {

        methods.writeRandomValueToElement(key, startingText);
    }

    @Step({"Print element text by css <css>",
            "Elementin text değerini yazdır css <css>"})
    public void printElementText(String css) {

        methods.printElementText(css);
    }

    @Step({"Write value <string> to element <key> with focus",
            "<string> değerini <key> elementine focus ile yaz"})
    public void sendKeysWithFocus(String text, String key) {

        methods.sendKeysWithFocus(text, key);
    }

    @Step({"Click to element <key> with focus",
            "<key> elementine focus ile tıkla"})
    public void clickElementWithFocus(String key) {

        methods.clickElementWithFocus(key);
    }

    @Step({"Write date with <int> days added to current date to element <key>",
            "Bugünkü tarihe <int> gün ekle ve <key> elementine yaz"})
    public void writeDateWithDaysAdded(int daysToAdd, String key) {

        methods.writeDateWithDaysAdded(daysToAdd, key);
    }

    @Step({"Accept Chrome alert popup",
            "Chrome uyarı popup'ını kabul et"})
    public void acceptChromeAlertPopup() {

        methods.acceptChromeAlertPopup();
    }

    @Step({"<email> create text write to element <key>",
            "<email> texti oluştur elemente yaz <key>"})
    public void UUIDCreate(String email, String key) {

        methods.UUIDCreate(email, key);
    }

    @Step({"Click <key> element if exists",
            "varsa <key> elemente tıkla"})
    public void checkAndClick(String key) throws InterruptedException {

        methods.checkAndClick(key);
    }

    @Step({"Send TAB key to element <key>",
            "Elemente TAB keyi yolla <key>"})
    public void sendKeyToElementTAB(String key) {

        methods.sendKeyToElementTAB(key);
    }

    @Step({"Send BACKSPACE key to element <key>",
            "Elemente BACKSPACE keyi yolla <key>"})
    public void sendKeyToElementBACKSPACE(String key) {

        methods.sendKeyToElementBACKSPACE(key);
    }

    @Step({"Send ESCAPE key to element <key>",
            "Elemente ESCAPE keyi yolla <key>"})
    public void sendKeyToElementESCAPE(String key) {

        methods.sendKeyToElementESCAPE(key);
    }

    @Step("<key> listesinden rastgele birine tıkla")
    public void randomClickInList(String key) {

        //random list and n11 homepage -> and categories page
        methods.randomClickInList(key);
    }

    @Step({"Find element with key <key> and save text to <saveKey>",
            "<key> elementi bul ve değerini <saveKey> olarak kaydet"})
    public void findElementAndSave(String key, String saveKey) {

        methods.findElementAndSave(key, saveKey);
    }

    @Step({"Find element with key <key> and compare text to <saveKey>",
            "<key> elementi bul ve değerini <saveKey> ile karşılaştır"})
    public void findElementAndCompare(String key, String saveKey) {

        methods.findElementAndCompare(key, saveKey);
    }

    @Step({"Find element with key <key> and compare text to <saveKey>",
            "<key> elementi bul ve değerinin <saveKey> içerdiğini kontrol et"})
    public void findElementAndStartsWith(String key, String saveKey) {

        methods.findElementAndStartsWith(key, saveKey);
    }

    @Step("<phoneNo> ya gelen smsi al ve <phoneKey> olarak kaydet")
    public void getSMSToken(String phoneNo, String phoneKey) {

        methods.getSMSToken(phoneNo, phoneKey);
    }

    @Step({"Check if two pictures are different <img-Url> and <img-Path>",
            "İki resmin farklı olup olmadığını kontrol et <img-Url> ve <img-Path> "})
    public void imageDiff(String imgUrl, String imgPath) {

        methods.imageDiff(imgUrl, imgPath);
    }

    @Step({"Check if element size are different <key> width : <widthW> hight : <hightH>",
            "Eleman boyutunun farklı olup olmadığını kontrol et <key> width : <widthW> hight : <hightH>"})
    public void findElementSize(String key, int widthW, int hightH) {

        methods.findElementSize(key, widthW, hightH);
    }

    @Step({"Search And Add To Cart | My Cart <table>",
            "Ara Ve Sepete Ekle | Sepetim <table>"})
    public void searchAndAddToCartTable(Table table) {
        methods.searchAndAddToCartTable(table);
    }

    @Step({"Dowland <url> File <filePath>",
            "<url> Adresindeki Dosyayı indir  <filePath>"})
    public void dowlandUrlFile(String url, String filePath) {
        methods.dowlandUrlFile(url, filePath);
    }

    @Step({"Delete File Path <deletefilePath>",
            "Dosya yolundaki dosyayı sil <deletefilePath>"})
    public void deletefilePath(String deletefilePath) {
        methods.deletefilePath(deletefilePath);
    }

}
