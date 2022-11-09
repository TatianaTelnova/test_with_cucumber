Feature: Is block filter faq?

  Scenario: A block item No.1 filters faq
    Given I am on the FaqPage
    When I click on the first block item and count faq
    Then faq must be less than 100