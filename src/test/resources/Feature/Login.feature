

Feature: Login feature For Alpha Exchange

  @webAE
  Scenario Outline:  Verify login feature with valid data

    Given I go to the Login Page of Alpha Exchange
    When I enter Username as "<Username>"  and Password as "<Password>" in Login Page
    Then I should be able to see logged in user as "<LoggedinUserName>"


    Examples:
  | Username   | Password  |LoggedinUserName|
  |scott+345@alpha-exchange.com| %\;7CX!]|James rant|


  @webAE
  Scenario Outline:  Verify login feature invalid data set

    Given I go to the Login Page of Alpha Exchange
    When I enter Username as "<Username>"  and Password as "<Password>" in Login Page
    Then I should be able to see ErrorMessage as "<ErrorMessage>"

    Examples:
      | Username   | Password  |ErrorMessage|
      |scott+345@alpha-exchange.com |12334 |Invalid Email or password.|
      | scot@aphhange.com |%\;7CX!] |Invalid Email or password.|

