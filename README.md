# Java_com.hepsiburada.gauge


## GAUGE WEB OTOMASYON TEST


### com.hepsiburada.testspec


Proje içersinde hepsiburada WebDriver Testi (https://www.hepsiburada.com) otomasyon gerçekleştirilmiş.


* Web Driver Testi
* @Before, @After,   
* @Step
   * -check if image size/px, element size, check if element, save key, click, sendKey, clear text area, page zoom %, elementWait, if else, ......
* SetBrowserForOS
* FindOS
* setCapability 
   * -peter.sh
* log4j 
   * -Versiyon: 1.7.26
* Driver 
   * -Chorme 
   * -Versiyon: 77.0.38
* Dependencies
   * -mavenrepository
* VideoRecording 
   * -Ses (opsiyonel) 
   * -Format: .AVI/.MOV
* Screenshot 
   * -Format: .png
* xpath, css, id 
   * -Format: .json key/value/type



* Senaryo
   * 1- Hepsiburada New Create Account and Shopping
      * Close At First Drop 'cookiePopup' | Home Page
      * Check If Image And Element Size | Home Page
      * HomePage And Next Register Form Firstname:"firstnameDir" Lastname:"lastnameDir" Email:"" Password:"passwordDir1" Create Account | SignUp Page
      * Search "kitap" And Add To Cart | My Cart
      * Check 'sepetim' And Complete Shopping | My Cart
      * Delivery Information Form | Delivery Page
      * Shopping Payment Information Credit Card cartNumber:"5890040000000016" cartName:"cartName" cartCVC:"061" | Payment    Information
      * Confirm Order | Payment Information
   * 2- Hepsiburada Login, Add Basket , Logout
      * Close At First Drop 'cookiePopup' | Home Page
      * Check If Image And Element Size | Home Page
      * HomePage And Next Login Email:"adiTestSoyadiTest@gmail.com" Password:"A*A1234560" | Login Page
      * Search "kitap" And Add To Cart | My Cart
      * My Cart And Delete Product | My Cart
      * Account Log Out | HomePage
