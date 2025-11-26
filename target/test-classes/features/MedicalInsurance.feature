# src/test/resources/features/MedicalInsurance.feature

Feature: Medical Insurance Options Retrieval

  As a Policybazaar user
  I want to view various medical insurance options
  So that I can choose the best plan for my health needs

  
  Scenario: Verify display of medical insurance options
    Given I navigate to the "View All Products" section
    When I fetch the list of medical insurance options
    Then I should see more than zero medical insurance options displayed
    And I should print all fetched medical insurance options