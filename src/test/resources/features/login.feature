Feature: Login

  @Sanity
  Scenario: Successful login with valid credentials
    Given user launch chrome browser
    When user opens URl: "https://admin-demo.nopcommerce.com/login"
    And user enters email as "admin@yourstore.com" and password as "admin"
    And click on login
    Then page title should be "Dashboard / nopCommerce administration"
    When user click on logout link
    Then page title should be "Your store. Login"
    And close browser


  @Regression
  Scenario Outline: Login Data Driven
    Given user launch chrome browser
    When user opens URl: "https://admin-demo.nopcommerce.com/login"
    And user enters email as <email> and password as <password>
    And click on login
    Then page title should be "Dashboard / nopCommerce administration"
    When user click on logout link
    Then page title should be "Your store. Login"
    And close browser
    Examples:
      | email                 | password |
      | "admin@yourstore.com" | "admin"  |
   #| "admin@yourstore.com"  | "admin12" |
