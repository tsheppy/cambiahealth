Feature: CSV parser runs as docker container

  Scenario: Parsing given input files writes expected output files
    Given The following input files and matching expected output files
      | sampleInput.csv | sampleOutput.csv |
    When I invoke the CSV Parser container
    Then The expected output files match the actual output files

  Scenario: Not providing a valid input file to parse throws an exception
    Given The following input files and matching expected output files
      | doesNotExist.csv | sampleOutput.csv |
    When I invoke the CSV Parser container
    Then The error code returned is 1