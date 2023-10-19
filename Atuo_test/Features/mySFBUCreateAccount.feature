Feature: Create a SFBU New Account
  As a new user to SFBU,
  In order to ensure create a new account functionality works,
  I want to run the cucumber test to verify it is working

  Scenario: Verification of creating a new account

    Given Open the Chrome and launch the SFBU application

    When Open Application Portal
    And Create Account
    And Select New Applicant
    And Enter Names
    And Select Gender
    And Enter Email
    And Enter Passwords
    And Select Survey
    Then Error Messages are popup
    And Close and exit