Feature: Purchase an Item

  Background:
    Given I am on the landing page

  @purchaseItem
  Scenario Outline: Purchase item as Login user existing address
    And I am log in to the application with "jhone@aldi.com"  "123456"
    When I navigate to the product List
    And I am click on "<product>" product
    And I click on add to basket button in product page
    And click on plus button '2' times
    And Cart price increased by product price
    And product is there in the cart
    And I navigate to order checkout
    And Verify the Delivery fee and Total fee
    And I am click on "Submit order" button
    And I am select the "<payment option>" and "<card name>" and "<card number>" and "<expiration month>" and "<expiration year>" and "<code>"
    And I am click on "Continue" button
    Then Verify the "Thank you, your order has been confirmed." Page Header
    And I am navigating to "order-history"
    And I click on "My Orders" Box
    And Verify the order details "<product>" and "<Quantity>" and Cost

    Examples:
      | product                     | payment option | card name | card number      | expiration month | expiration year | code | Quantity |
      | FERREX 1100W Turbine Blower | VISA           | ANB       | 4111111111111111 | 01               | 24              | 784  | 3        |

  @purchaseItem
  Scenario Outline: Purchase item as Login user with new delivery details
    And I am log in to the application with "jhone@aldi.com"  "123456"
    When I navigate to the product List
    And I am click on "<product>" product
    And I click on add to basket button in product page
    And click on plus button '2' times
    And Cart price increased by product price
    And product is there in the cart
    And I navigate to order checkout
    And I am click on "change delivery details"
    And  I am click on "Add new address" button
    And I enter "<First name>" and "<Surname>" and "<Mobile number>" and "<Address>"
    And I am click on "Use this delivery information" button
    And Verify the Delivery fee and Total fee
    And I am click on "Submit order" button
    And I am select the "<payment option>" and "<card name>" and "<card number>" and "<expiration month>" and "<expiration year>" and "<code>"
    And I am click on "Continue" button
    Then Verify the "Thank you, your order has been confirmed." Page Header
    And I am navigating to "My Orders"
    And I click on "My Orders" Box
    And Verify the order details "<product>" and "<Quantity>" and Cost

    Examples:
      | product                     | First name | Surname | Mobile number | Address                                 | payment option | card name | card number      | expiration month | expiration year | code | Quantity |
      | FERREX 1100W Turbine Blower | Jhone      | Aj      | 0401011101    | 222B Princes Highway, SYLVANIA NSW 2224 | Visa           | ANB       | 4111111111111111 | 02               | 24              | 784  | 3        |