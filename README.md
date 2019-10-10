# Java_com.hepsiburada.gauge


## GAUGE WEB AUTOMATİON TEST


### com.hepsiburada.testspec


Within the project  hepsiburada WebDriver Test (https://www.hepsiburada.com) automation realized.


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
   * -Ses (optionally) 
   * -Format: .AVI/.MOV
* Screenshot 
   * -Format: .png
* Extentreports (GUI Reports)
   * -Format: .html
* xpath, css, id 
   * -Format: .json key/value/type



## Scenario

    |           email            |  password    |
    |adiSoyadi@gmail.com         |  AA123456    |
    |adiTestSoyadiTest@gmail.com |  A*A1234560  |
    
    * Close At First Drop 'cookiePopup' | Home Page
    * Check If Image And Element Size | Home Page

###   * 1- [Hepsiburada New Create Account and Shopping](https://github.com/AbdurrahmanDemirci/Java_com.hepsiburada.gauge/blob/master/specs/example.spec)

      * HomePage And Next Register Form Firstname:"firstnameDir" Lastname:"lastnameDir" Email:"" Password:"passwordDir1" Create Account | SignUp Page
      * Search And Add To Cart | My Cart
          | searchProduct |
          |   defter      |
          |   kitap       |
      * Check 'sepetim' And Complete Shopping | My Cart
      * Delivery Information Form | Delivery Page
      * Shopping Payment Information Credit Card cartNumber:"5890040000000016" cartName:"cartName" cartCVC:"061" | Payment
      * Confirm Order | Payment Information
###   * 2- [Hepsiburada Login, Add Basket , Logout](https://github.com/AbdurrahmanDemirci/Java_com.hepsiburada.gauge/blob/master/specs/example.spec)

      * HomePage And Next Login Email:"adiTestSoyadiTest@gmail.com" Password:"A*A1234560" | Login Page
      * Search "kitap" And Add To Cart | My Cart
      * My Cart And Delete Product | My Cart
      * Account Log Out | HomePage
      
###   * 3- [example.cpt   File](https://github.com/AbdurrahmanDemirci/Java_com.hepsiburada.gauge/blob/master/specs/concepts/example.cpt)
