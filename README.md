# https://github.com/tsheppy/cambiahealth/

This is the Git repo for the [Cambia Health SDET position](https://careers-cambiahealth.icims.com/jobs/24078/lead-test-and-software-engineer-%28sdet%29/job) being applied for by myself [(Tucker Sheppy)](https://www.linkedin.com/in/tucker-sheppy/).  

# Programming Portion

I addressed the programming section of this interview by creating a Docker project using Java and Gradle for builds.  This is the first truly open-source project I have worked on, so it was a great chance to learn what tools are available to the open-source community.

## Building the application
Run `./gradlew` to build and test the application.

## Using the docker image
The docker image   

## Why Gradle? 
I chose to use Gradle to build the project for a few reasons.
 * I am familiar with it  :)
 * Cross-platform build compatibility since I have been using Windows at home and work
 * Ease of implementation and extension

## Tell me about the Java implementation
I chose to implement the main parsing logic with Java because I was most familiar with developing and testing it using Cucumber.  The logic is simple enough that it could probably have been implemented in a simple scripting language like Python if the developer was familiar with writing and testing it.

If this were production code, I'd also advocate for using a good CSV parsing library like [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/) for its increased robustness and features.  I did not use it for this example because it was simple enough to do with core Java.    


## And the Gherkin Tests?
Tests use the Cucumber framework with the _.feature_ files written in Gherkin.  Tests are directed at both a unit-test level (sorting.feature) as well as a service-level (dockerExecution.feature).  If this were in production, I'd recommend replacing the sorting.feature with just JUnit tests unless significant interaction is expected from the PO regarding that behavior.  The use of data tables in the tests was done as a learning exercise and would need additional work to scale out and support a larger collection of matching input/output csv files.  Although step definitions were not required, I enjoyed the challenge.  

Tests such as these can provide the following benefits:

* Act as a safety net for future refactoring efforts
* Provide clarity for the expected behavior of the system in a readable manner by less-technical stakeholders
* Provide metrics to help inform development and release activities

# Question / Answers

## Tools






