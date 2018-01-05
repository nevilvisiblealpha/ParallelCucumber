Feature: Dashboard verification

  @webAE
  Scenario: Feed and Explore tab redirection

    Given I go to the Login Page of Alpha Exchange
    When I enter Username as "scott+345@alpha-exchange.com"  and Password as "%\;7CX!]" in Login Page
    Then verify user landed on Dashboard Page
    Then verify feed post loaded inside the feed tab
    Then verify feed post loaded inside the Explore tab

  @webAE
  Scenario: search functionality verification

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
    When I navigate to the first feed
    Then I should see same comment shows up when opening research report

  @webAE
  Scenario:  Verify share report Functionality
    Given I login as buyer side user
    When I navigate to the first feed
    And I share report with company

  @webAE
  Scenario:  Verify save report Functionality
    Given I login as buyer side user
    When I navigate to the first feed
    And I save the report in personal brief case
    And I navigate to the "Personal Briefcase" of User Profile Dropdown
    Then I should see same report listed in My personal brief case

  @webAE
  Scenario:  Verify save report Functionality
    Given I login as buyer side user
    When I navigate to the first feed
    And I save the report to company RMS
    And I navigate to the "Personal Briefcase" of User Profile Dropdown
    Then I should see same report listed in Company Research


  @webAE
  Scenario: Verify user is able to see filtered Activity log based on start date
    Given I login as buyer side user
    When I navigate to the "Activity Log" of User Profile Dropdown
    When I apply following filter
      | fromDate | toDate | team | analyst | reasearchProvider | author | activityType | starRating |
      | 10       |        |      |         |                   |        |              |            |
    Then I should see from "10" days back activity logs

  @webAE
  Scenario: Verify redirection of Personal Briefcase
    Given I login as buyer side user
    When I navigate to the "Personal Briefcase" of User Profile Dropdown
    Then I should direct to the "Personal Briefcase" tab

  @webAE
  Scenario: Verify redirection of setting page
    Given I login as buyer side user
    When I navigate to the "Settings" of User Profile Dropdown
    Then I should direct to the "Settings" page

  @webAE
  Scenario: Verify redirection of Following page
    Given I login as buyer side user
    When I navigate to the "Following" of User Profile Dropdown
    Then I should direct to the "Following" page

  @webAE
  Scenario: Verify user is able to create event
    Given I login as buyer side user
    When I click on create event
    Then I should see create event form

  @webAE
  Scenario: Verify user is able upload research
    Given I login as buyer side user
    When I click on upload research
    Then I should see uploaded research form

  @webAE
  Scenario: Verify user is able to see filtered Activity log based on start date
    Given I login as buyer side user
    When I navigate to the "Activity Log" of User Profile Dropdown
    When I apply following filter
      | fromDate | toDate | team | analyst     | reasearchProvider | author | activityType | starRating |
      | 20       |        |      | James Grant |                   |        |              |            |
    Then I should see Activity log with Internal Attendess as "James Grant"

  @test
  Scenario: Verify user is able to see filtered Activity log based on start date
    Given I login as buyer side user
    When I navigate to the "Activity Log" of User Profile Dropdown
    When I apply following filter
      | fromDate | toDate | team | analyst     | reasearchProvider | author | activityType | starRating |
      |          |        |      | James Grant |                   |        |              | 4         |
    Then I should see Activity log with "4" star rating only
