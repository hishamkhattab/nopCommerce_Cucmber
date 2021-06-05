Feature: Customers

  Background: The setup for each scenario
    Given user launch chrome browser
    When user opens URl: "https://admin-demo.nopcommerce.com/login"
    And user enters email as "admin@yourstore.com" and password as "admin"
    And click on login
    Then user can view dashboard
    When user click on customer menu
    And click on customer menu item


  @Sanity
  Scenario: Add new customer
    And click on add new button
    Then user can view add new customer page
    When user enter customer info
    And click on save button
    Then user can view confirmation message "The new customer has been added successfully"
    And close browser

  @Regression
  Scenario: Search a customer by using Email ID
    And enter the customer Email
    When click on search button
    Then user should find Email in the search table
    And close browser

  @Regression
  Scenario: Search a customer by using name
    And enter the customer first name
    And enter the customer last name
    When click on search button
    Then user should find name in the search table
    And close browser
