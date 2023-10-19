Feature: Negative functionality on login page of Application
  In order to ensure Login Functionality works,
  I want to run the cucumber test to verify it is working

  Scenario: Verification of error messages with numbers of credential by parameter
    Given Open the Chrome and launch the SFBU application
    When Open Application Portal
    And User logs in using Username as "User1" and Password "Password1"
    Then Return Login failed message "The Account User1 does not exist"
    And Logout