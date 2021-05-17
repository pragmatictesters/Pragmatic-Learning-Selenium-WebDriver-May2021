@productSearch
Feature: Search an item

  @product_search
  @smoke
  @TC_0003
  Scenario: Product search with logged in user
    Given I am log in to the application with "jhone@aldi.com"  "123456"
    When I am click on "Special Buys ™" link
    And I am click on "Shop All" link
    Then Verify the Products are available

  @product_search
  @smoke
  @TC_0004
  Scenario: Product search without login
    Given I am on the landing page
    When I am click on "Special Buys ™" link
    And I am click on "Shop All" link
    Then Verify the Products are available

  @product_search
  @smoke
  @TC_0005
  Scenario: Specific Product search without login
    Given I am on the landing page
    When I search for "TV" link
    Then Verify the Products are available

  @product_search
  @smoke
  @TC_0006
  Scenario: Specific Product search with logged in user
    Given I am log in to the application with "jhone@aldi.com"  "123456"
    When I search for "TV" link
    Then Verify the Products are available