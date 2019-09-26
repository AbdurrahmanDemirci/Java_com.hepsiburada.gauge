Getting Started with Gauge
==========================

This is an executable specification file. This file follows markdown syntax. Every heading in this file denotes a scenario. Every bulleted point denotes a step.
To execute this specification, use `mvn test`

Hepsiburada New Create Account and Shopping
----------------------
Tags: Hepsiburada New Create Account and Shopping

* Close At First Drop 'cookiePopup' | Home Page
* Check If Image And Element Size | Home Page
* HomePage And Next Register Form Firstname:"firstnameDir" Lastname:"lastnameDir" Email:"" Password:"passwordDir1" Create Account | SignUp Page
* Search "kitap" And Add To Cart | My Cart
* Check 'sepetim' And Complete Shopping | My Cart
* Delivery Information Form | Delivery Page
* Shopping Payment Information Credit Card cartNumber:"5890040000000016" cartName:"cartName" cartCVC:"061" | Payment Information
* Confirm Order | Payment Information
* "3" saniye bekle



Hepsiburada Login, Add Basket , Logout
----------------------
Tags: Hepsiburada Login, Add Basket , Logout

* Close At First Drop 'cookiePopup' | Home Page
* Check If Image And Element Size | Home Page
* HomePage And Next Login Email:"adiTestSoyadiTest@gmail.com" Password:"A*A1234560" | Login Page
* Search "kitap" And Add To Cart | My Cart
* My Cart And Delete Product | My Cart
* Account Log Out | HomePage
* "3" saniye bekle