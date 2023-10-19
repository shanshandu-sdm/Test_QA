Feature: Negative functionality on login page of Application
  In order to ensure Login Functionality works,
  I want to run the cucumber test to verify it is working

  Scenario Outline: Verification of error messages with numbers of credential by table
    Given Open the Chrome and launch the SFBU application
    When Open Application Portal
    And Enter the Username as <username> and Password <password>
    Then Login should be failed <message>
    And Logout
    Examples:
      | username | password    | message                            |
      | "User1"  | "Password1" | "The Account User1 does not exist" |
      | "User2"  | "Password2" | "The Account User2 does not exist" |
      | "user3"  | "Password3" | "The Account User3 does not exist" |
