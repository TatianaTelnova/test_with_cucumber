Feature: Is ATM quantity equals to the number?

  Scenario: ATM quantity equals to 4
    Given I am on the AtmPage
    When I click on the ATM list button
    Then ATM must be equals 4