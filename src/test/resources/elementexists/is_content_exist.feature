Feature: Is content exist?

  Scenario: Content exists on a main page
    Given I am on the MainPage
    When I count elements in a content
    Then the result must not be null