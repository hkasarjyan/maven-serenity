Feature: Positive and Negetive API tests

  Scenario: Verify results for all supported endpoints
    When user with method "Get" calls product "cola"
    Then response status code should be 200
    Then user verifies the results displayed for "cola"
    When user with method "Get" calls product "apple"
    Then response status code should be 200
    Then user verifies the results displayed for "apple"

  Scenario: Verify post method is not allowed
    When user with method "Post" calls product "cola"
    Then response status code should be 405
    Then response status line should be "HTTP/1.1 405 Method Not Allowed"

  Scenario: Negative cases for method Get
    When user with method "Get" calls product "mango"
    Then response status code should be 404
    Then user verifies requested item
      |boolean_comparison|key_name       |key_value           |
      |false             |requested_item |mango               |
      |false             |message        |Not found           |
      |true              |error          |true                |
      |false             |served_by      |https://waarkoop.com|

  Scenario: Verify all supported endpoints contains 9 keys
    Then user with method "Get" verifies "apple, orange, pasta, cola" products all keys
      |provider|title|url|brand|price|unit|isPromo|promoDetails|image|

  Scenario: Verify all supported endpoints contains right number of providers
    When user with method "Get" calls product "cola"
    Then providers count is 44
    When user with method "Get" calls product "orange"
    Then providers count is 53
    When user with method "Get" calls product "pasta"
    Then providers count is 47
    When user with method "Get" calls product "apple"
    Then providers count is 34