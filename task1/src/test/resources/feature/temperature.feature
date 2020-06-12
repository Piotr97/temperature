Feature: temperature webservice application that allows post and get localization temperature.

  Scenario Outline: Temperature is added depending of the database elements
    Given I have temperature <localization>, <value>
    When I send request to post temperature
    Then I got <status> status
    And I <posted> posted temperature to database

    Examples:
      | localization | value | status   | posted |
      | 1            | 5     | OK       | TRUE   |
      | 2            | 5     | OK       | TRUE   |
      | 1            | 4     | CONFLICT | FALSE  |
      | 1            | 5     | CONFLICT | TRUE   |
      | 1            | 6     | OK       | TRUE   |

