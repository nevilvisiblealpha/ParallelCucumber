


  Feature: Login feature

     @web
     Scenario: Successful login
        Given I go to the Login Page
         When I enter "nevil.panchal" email address
         Then verify page is loaded for me

    @web
    Scenario:  other login
       Given  I go to the Login Page
       When I enter "nevil.panchal2" email address
       Then verify page is loaded for me
    @web
    Scenario: morekaka
        Given  I go to the Login Page
        When I enter "morekaka.more" email address


