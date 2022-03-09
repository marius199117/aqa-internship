# AQA Internship

## Required Tools

-	Java JDK/JRE

-	IntelliJ Idea / Any Java IDE

-	Git

-	Selenium Standalone Server

-	Selenium Webdriver

-	BDD Framework - Cucumber

-	Junit / TestNG

-	Maven

## Test Execution on Local Machine

Prerequisits:
1.	Ensure that you have set your local environment (environment variables, maven dependencies, SDK configuration etc.)
2.	Ensure that you have no errors on your classes (if there is an error, the test will not start, and an error message will be displayed)

To run a single class with test scenarios please follow the following steps:

1. Right click on a classname.feature class and select Run Feature File Name
2. The test will start running
3. When the test scenarios has finished running, a report can be seen on the command line (Pass or Failed)

To run all the scenarios (all the classes) please follow the following steps:

1. Right click on CucumberTest class
2. Select Run ‘CucumberTest’ and the test will start running
3. When all the test scenarios has finished running, a report can be seen on the command line (Pass or Failed)