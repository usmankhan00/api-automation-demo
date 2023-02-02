Feature: Users

  @Smoke
  Scenario: Create User in pet store
    When I created the user in pet store
    Then I validated the status code should be 200


  @Smoke
  Scenario: Read User in pet store
    When I read the user in pet store
    Then I validated the status code should be 200
    And I validated the values in response body
      | username   | first name | last name | email          | password    | phone        |
      | UsmanRaees | Usman      | Raees     | demo@usman.com | password123 | 923213333666 |

  @Smoke
  Scenario: Update and Read User in pet store
    When I updated the user in pet store
      | username   | first name | last name | email                 | password           | phone        |
      | UsmanRaees | Usman      | Raees     | update_demo@usman.com | update_password123 | 923213333666 |
    Then I validated the status code should be 200
    When I read the user in pet store
    Then I validated the status code should be 200
    And I validated the values in response body
      | username   | first name | last name | email                 | password           | phone        |
      | UsmanRaees | Usman      | Raees     | update_demo@usman.com | update_password123 | 923213333666 |

  @Smoke
  Scenario: Update and Read User in pet store
    When I updated the user in pet store
      | username   | first name | last name | email                 | password           | phone        |
      | UsmanRaees | Usman      | Raees     | update_demo@usman.com | update_password123 | 923213333666 |
    Then I validated the status code should be 200
    When I read the user in pet store
    Then I validated the status code should be 200
    And I validated the values in response body
      | username   | first name | last name | email                 | password           | phone        |
      | UsmanRaees | Usman      | Raees     | update_demo@usman.com | update_password123 | 923213333666 |

    @Smoke
    Scenario: Delete and validate user is deleted
      When I delete the user in pet store
      Then I validated the status code should be 200
      When I read the user in pet store
      Then I validated the status code should be 404


