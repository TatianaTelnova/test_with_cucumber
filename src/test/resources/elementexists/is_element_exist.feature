Feature: Is element exist?

  Scenario: Element exists on a page
    Given I am on the MainPage
    When I search for login button
    Then the result must be true