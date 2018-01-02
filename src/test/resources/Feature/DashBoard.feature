Feature: Dashboard verification
  @webAE
Scenario: Dashboard redirection on buy side login

  Given I go to the Login Page of Alpha Exchange
  When I enter Username as "scott+345@alpha-exchange.com"  and Password as "%\;7CX!]" in Login Page
    Then verify user landed on Dashboard Page
  Then verify feed post loaded inside the feed tab
    Then verify feed post loaded inside the Explore tab

  @webAE
  Scenario: saved Search redirection on buy side login

    Given I go to the Login Page of Alpha Exchange
    When I enter Username as "scott+345@alpha-exchange.com"  and Password as "%\;7CX!]" in Login Page
    When I click "Gold Search" from Saved Search list
    Then I should see "gold" in search textbox
    Then I should see "Commodities" checkbox selected under "Asset Classes"
    Then I should direct to the "Research Reports" tab


  @webAE
  Scenario: Verify user is able to comments on feed
    Given I login as buyer side user
   When  I try to comment on first feed post
    And  I should see comment count should increase by one of first feed
    Then I should see same comment shows up when opening research report by clicking on the title


  @webAE
    Scenario: Verify user is able to see filtered Activity log based on start date
      Given I login as buyer side user
      When I navigate to the "Activity Log" of User Profile Dropdown
      When I select "10" days back from date and apply filter
      Then I should see from "10" days back activity logs

  @webAE
  Scenario: Verify user is able to see filtered Activity log based on start date
    Given I login as buyer side user
    When I navigate to the "Personal Briefcase" of User Profile Dropdown

  @webAE
  Scenario: Verify user is able to see filtered Activity log based on start date
    Given I login as buyer side user
    When I navigate to the "Settings" of User Profile Dropdown


  @webAE
  Scenario: Verify user is able to see filtered Activity log based on start date
    Given I login as buyer side user
    When I navigate to the "Following" of User Profile Dropdown