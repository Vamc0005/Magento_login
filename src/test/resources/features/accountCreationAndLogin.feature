Feature: Account Creation and Login

  Scenario: Create an account and log in successfully
    Given I am on the Magento homepage
    When I create a new account
    And I should be able to log out
    Then I should be able to log in with the new account
    
