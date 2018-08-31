Feature: File IO

  Scenario: Valid output path creates a file
    Given I provide a valid file output path
    When I write some entries to a file
    Then A file is created at the provided path

  Scenario: Invalid output path throws an exception
    Given I provide an invalid file output path
    When I write some entries to a file
    Then An exception is thrown when writing to file
