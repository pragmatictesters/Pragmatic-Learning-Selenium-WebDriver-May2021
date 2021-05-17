@registration
Feature: User registration

  Background:
    Given I am on the landing page

  @TA-0001
  Scenario Outline: User registration
    When I am click on "Login" link
    And Generate Random String To Email combine To "<email address>"
    And Verify the Page Header "Already registered?"
    And I am click on "Register" Tab
    And I enter new email address and enter "<password>" and enter "<confirm password>"
    And I Agree the conditions and subscribe
    And I click on Register button
    And Verify the Page Header "almost there!"
    And I click on "Go to login" button
    And I get the Activate URL from mailinator and load it
    And Verify the Page Header "You have successfully activated your account"
    And I enter email address and enter "<password>" for new User
    And  I am click on "Login" button
    Then Verify logout link
    And Logout from the Application

    Examples:
      | email address           | password       | confirm password |
      | testauto@hemnetteqa.com | TestAuto@qa123 | TestAuto@qa123   |