# Maven_serenity automation framework for API testing
This project is a test task. <br />
Libs used: Cucumber, Serenity, Restassured <br />
Runner: Maven<br />
Reporting: Surefire <br />

## Test scenarios
Endpoint: GET https://waarkoop-server.herokuapp.com/api/v1/search/demo/{product} for getting the products.
Available products: "orange", "apple", "pasta", "cola"
Positive and negative scenarios are written

### The project directory structure
The project has build scripts for Maven, and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main
  + test
    + java                        Test runners and supporting code
    + resources
      + features                  Feature files
     + search                  Feature file subdirectories 
```

## Setup Instructions
Install JAVA <br />
Install Maven <br />
Project URL: https://gitlab.com/hovhanneskasarjyan/test_task_mave_serenity.git

## Executing the tests
Run a single test class.
`mvn -Dtest=TestApp1 test`<br />
Run a single test method from a test class.
`mvn -Dtest=TestApp1#methodname test`<br />
Run all tests
`mvn test`<br />

The test results will be recorded in the `target/site/serenity` directory.

## Generating the reports
To Generate the reports use command
`mvn surefire-report:report-only`
XML report is located in `target\surefire-reports\*.xml`
HTML report is located in `target\site\surefire-report.html`

## Gitlab CI/CD
Gitlab CICD is setup to run testcases. <br />
Pipeline to run tests:  https://gitlab.com/hovhanneskasarjyan/test_task_mave_serenity/-/pipelines
Job archives the html and xml report artifacts

## Briefly write in Readme what was refactored and why
1) Removed gradle code
2) Implemented Cucumber format
3) Implemented data table format
4) Removed unnecessary and unused code
5) Created positive and negative cases