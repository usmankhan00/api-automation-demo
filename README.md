API Automation (Cucumber - Rest Assured) 
========================================
This is automation suite is designed for api automation using Java with Rest Assured and Cucumber Framework

### Installation (pre-requisites)
    -Java JDK 8 or above (make sure Java class path is set)
    -Maven (make sure .m2 class path is set)
    -Allure command line tools
    -Cucumber for Java plugin in IntelliJ/Eclipse IDE

### Framework set up
Fork / Clone repository from [here](https://github.com/usmankhan00/web-automation-demo) or download zip and set it up in your local workspace.

### Test Execution
Open terminal (MAC OSX) or command prompt / power shell (for windows OS) and navigate to the project directory type mvn clean test command to run features. With this command it will invoke the Chrome browser (First it will download binaries for chrome driver) and will execute the tests.

### Allure Report
You can generate a report using one of the following command in terminal or command prompt.
- allure generate target/allure-results --clean -o allure-report

## Sample Report
![Test Execution Allure Report](https://github.com/usmankhan00/web-automation-demo/blob/main/img.png)