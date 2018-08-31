Feature: CSV Sorting

  Scenario: Sorting an out-of-order single CSV line
    Given I have an entry of "d,c,b,a"
    When I sort the entry
    Then I have a result of "a,b,c,d"

  Scenario: Sorting an in-order single CSV line
    Given I have an entry of "a,b,c,d"
    When I sort the entry
    Then I have a result of "a,b,c,d"

  Scenario: Sorting a CSV line with double entries
    Given I have an entry of "e,a,a,g"
    When I sort the entry
    Then I have a result of "a,a,e,g"

  Scenario: Sorting a CSV line with mixed case entries
    Given I have an entry of "D,C,b,a"
    When I sort the entry
    Then I have a result of "C,D,a,b"

  Scenario: Sorting a CSV line with numbers
    Given I have an entry of "D,1,2,a"
    When I sort the entry
    Then I have a result of "1,2,D,a"

  Scenario: Sorting an empty list
    Given I have an entry of ""
    When I sort the entry
    Then I have a result of ""

  Scenario: Empty entries are sorted in front
    Given I have an entry of "d,c,,a"
    When I sort the entry
    Then I have a result of ",a,c,d"