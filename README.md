# https://github.com/tsheppy/cambiahealth/
[![CircleCI](https://circleci.com/gh/tsheppy/cambiahealth/tree/master.svg?style=svg)](https://circleci.com/gh/tsheppy/cambiahealth/tree/master)
[![Build Status](https://travis-ci.org/tsheppy/cambiahealth.svg?branch=feature%2Ftravis-ci)](https://travis-ci.org/tsheppy/cambiahealth)
[![Coverage Status](https://coveralls.io/repos/github/tsheppy/cambiahealth/badge.svg?branch=master)](https://coveralls.io/github/tsheppy/cambiahealth?branch=master)

This is the Git repo for the [Cambia Health SDET position](https://careers-cambiahealth.icims.com/jobs/24078/lead-test-and-software-engineer-%28sdet%29/job) being applied for by myself [(Tucker Sheppy)](https://www.linkedin.com/in/tucker-sheppy/).  

# Programming

I addressed the programming section of this interview by creating a Docker project using Java and Gradle for builds.  This is the first truly open-source project I have worked on, so it was a great chance to learn what tools are available to the open-source community.

## Building the application

Run `./gradlew` to build and test the application.

## Sort CSV file contents using the Docker container

``docker run --rm -v /my/path/to/csv:/work tsheppy/cambiahealth:1.0`` 
This will start a new container and look for a CSV file in the _/my/path/to/csv/_ directory.  Upon completion, a file named _output.csv_ will be created in the same directory with the contents of _input.csv_ but sorted within each row.

# Gherkin

1. __Write Gherkin tests for program__
    
    Tests use the Cucumber framework with the _.feature_ files written in Gherkin.  Tests are directed at both a unit-test level (sorting.feature) as well as a service-level (dockerExecution.feature).  If this were in production, I'd recommend replacing the sorting.feature with just JUnit tests unless significant interaction is expected from the PO regarding that behavior.  The use of data tables in the tests was done as a learning exercise and would need additional work to scale out and support a larger collection of matching input/output CSV files.  Although step definitions were not required, I enjoyed the challenge.  

2. __Explain in detail why they might be helpful in the future__
    
    Tests such as these can provide the following benefits:

    * Act as a safety net for future refactoring efforts
    * Provide clarity for the expected behavior of the system in a readable manner by less-technical stakeholders
    * Provide metrics to help inform development and release activities

# Tools

1. __What's helpful about version control systems?  What's annoying about them?__

    Version Control Systems are critical whenever there is a need to coordinate work on shared source code.  They (generally) provide a way to track history, provide access to source code, and manage conflicts between developers working on the same code.  Without them, we'd be stuck back in the days of distributing code back and forth via floppy disk or email.  Both methods are extremely fragile.

    As version control systems are just tools, I don't generally find anything annoying about them.  If chosen and used correctly, they can greatly enhance development teams' productivity.        

2. __What are pros and cons of using Docker to develop, test, and deploy Software?__

    Docker is a great way to stabilize the runtime environment of a process.  A simple dockerfile can be very useful in understanding its purpose.  Explicit declaration of exposed ports, volumes, and runtime arguments make it easier to understand the scope of security and functional testing.  Docker is also widely used within the industry to host microservices and there are many products that also assist with their operational management like Kubernetes and Docker Swarm.
    
    Although docker can be extremely useful during development, some challenges remain.  If a standard install of Docker is made on a Linux system and volumes are mapped using a non-sudo account, removal of created files can create additional complexity.  Early support of Docker on Windows was certainly challenging in an LDAP environment.  Test framework support of containers is also not yet fully developed, although there are some solutions in place like [JUnit5-Docker](https://faustxvi.github.io/junit5-docker/).  I am also not currently aware of how to debug a process running within a Docker container should the need arise.
      
3.  __How to choose which language for a task?  Why did I choose the language in this exercise?__

    When choosing a language, I typically look at the following factors (in priority order):
    * What the development team or organization is knowledgeable with
    * If there is a industry adopted language for that type of task
    * Personal preference
    
    I chose to implement the main parsing logic with Java because I was most familiar with developing and testing it using Cucumber.  The logic is simple enough that it could probably have been implemented in a simple scripting language like Python if the developer was familiar with writing and testing it.

    If this were production code, I'd also advocate for using a good CSV parsing library like [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/) for its increased robustness and features.  I did not use it for this example because it was simple enough to do with core Java.
    
    I chose to use Gradle to build the project because I am familiar with it, it provides cross-platform build compatibility, and its ease of implementation and extension.        

# Testing Methodology

1.  __What's the right role for QA in the software development process?__
    
    I believe the right role for QA within a development process is whatever it takes to deliver a product that meets the organization's and customers' expectations.  There is a range of roles and environments for QA, and I believe that there is no single role.  Just a _right_ role for __your__ organization and application.  
    
    For example, if a product were being developed for a highly regulated industry such as health care or spaceflight there may be a need for dedicated QA teams that focus solely on QA and work against a documented set of requirements.  
    
    Within a modern agile software process, the QA role might be embedded with the development team and focused on developing functional and *ility tests at the same time as the developers.  Depending upon organization culture, the role might only focus on building test frameworks, providing training, gather and report metrics, and otherwise enabling core development teams to deliver a quality product.
    
2.  __As a QA person, you have two weeks to prepare before development begins, what do you do?__

    To prepare for a project as it begins, I'd try to accomplish as many of the below objectives as time allows.
    * Understand the program's objective and the value we're trying to deliver to the customer
    * Understand the programming language(s) and general strategy of the development team
    * Identify testing challenges you're like to encounter (a collection of micro-services, hardware dependencies, big data, etc)
    * Identify the appropriate language and testing frameworks
    * Prototype some skeleton tests using the above choices
    * Build up QA product backlog as appropriate  
    * Establish quality metrics and reporting mechanism with all stakeholders if not done already
    
3.  __When is it appropriate to use automated testing?  When manual?__

    In general I prefer to use automated testing whenever possible, but there are situations when manual testing may be more appropriate.  Some of those situations are described below.
    * Exploratory testing
    * The product management team has decided that they don't want to dedicate resources to build automated testing (like on a large legacy product)
    * The time available to test is extremely limited
    * A feature can't easily be tested (the cost outweighs the benefit)
    * Usability and related non-functional tests
    
4. __I'm in charge of integration testing for new features.  Where to focus testing efforts?  How to decide what not to test?__

    As the QA role is largely about understanding the risk of a project, I'd focus first on the riskiest areas as outlined below.
    * Functionality that exercises the integration between multiple components that was not covered by unit tests
    * Most common use cases
    * Code with low code coverage
    * Code with most churn
    * Functionality that has most reported defects
    * Functionality that the development team is less confident about 
    
    I'd place less emphasis on testing:
    * Functionality that has not been refactored
    * Non-integration aspects of code that has high unit-test coverage
    * Functionality that relies upon widely adopted third party libraries
    * Seldom used features
