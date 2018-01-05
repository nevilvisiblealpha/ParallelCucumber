

Feature: Login feature For Alpha Exchange

  @webAE
  Scenario Outline:  Verify login feature with valid data

    Given I go to the Login Page of Alpha Exchange
    #2.1.1
    When I enter Username as "<Username>"  and Password as "<Password>" in Login Page
    Then I should be able to see logged in user as "<LoggedinUserName>"


    Examples:
  | Username   | Password  |LoggedinUserName|
  |scott+345@alpha-exchange.com| %\;7CX!]|James Grant|


  @webAE
  Scenario Outline:  Verify login feature invalid data set

    Given I go to the Login Page of Alpha Exchange
    When I enter Username as "<Username>"  and Password as "<Password>" in Login Page
    Then I should be able to see Message as "<ErrorMessage>"

    Examples:
      | Username                    | Password  |ErrorMessage               |
      |scott+345@alpha-exchange.com |1233       |Invalid Email or password. |
      | scot@aphhange.com           |%\;7CX!]   |Invalid Email or password. |

  @webAE
    Scenario: verify new user is Able to sign up
      Given I go to the Login Page of Alpha Exchange
      When I try to signup with "Automation" as firstname and "User" as lastname
      Then I should be able to see Message as "Thank you for signing up! We'll be verifying your account shortly."



