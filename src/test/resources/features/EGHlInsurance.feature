# src/test/resources/features/TravelInsurance.feature

Feature: EGH Insurance Quote Flow

  As a Policybazaar user
  I want to get a travel insurance quote
  So that I can secure my trip

  Scenario: Successfully navigate through the EGH insurance quote process
    Given I am on the Policybazaar website
    When the user clicks on the Employee Group Health Insurance link
    And the user enters mobile number "9876543210" and number of employees "25"
    And the user clicks on View Plan Instantly
    And the dialog appears and the user selects "Yes"
    And the user enters company name "CTS"
    And the user clicks on Continue button
    And the user selects city "Bengaluru"
    And the user clicks on View Plans button
    Then the insurer names should be printed
      