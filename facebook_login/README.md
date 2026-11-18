# Facebook Login Automation

A Java and Selenium WebDriver project that runs a data-driven browser test against the Facebook login page. Maven manages the project and dependencies, and TestNG supplies the test and its Excel-backed data provider.

> **Use test accounts only.** The test visits the live Facebook website and submits the credentials in the local Excel workbook. Replace its data with an account you are authorized to use. Do not use personal credentials or commit real passwords. Facebook may present CAPTCHA, multi-factor authentication, or other checks that prevent automation.

## Requirements

- Java Development Kit (JDK) 8 or later
- Apache Maven
- Google Chrome installed on the machine

The project compiles with Java 8 source and target compatibility. Selenium 3 uses the ChromeDriver executable at `AutomateFaceBook/src/test/resources/chromedriver.exe`; the driver must be compatible with the installed Chrome version. The code also has Firefox and Internet Explorer options, but those browsers and their corresponding drivers must be installed and configured separately.

## Run the tests

From the `facebook_login` project directory, run the complete Maven test suite:

```shell
mvn test
```

Run only the Facebook login test method from the `facebook_login` directory:

```shell
mvn -pl AutomateFaceBook -Dtest=RegressionTest#faceBookLogin test
```

Alternatively, enter the Maven module directory and run the test directly:

```powershell
cd .\facebook_login\AutomateFaceBook
mvn test "-Dtest=faceBookAutomation.RegressionTest#faceBookLogin"
```

In VS Code:

1. Open [RegressionTest.java](AutomateFaceBook/src/test/java/faceBookAutomation/RegressionTest.java).
2. Click **Run Test** above `faceBookLogin`, or right-click the `RegressionTest` class and select **Run Test**.
3. Keep `browserType=Chrome` in `config.properties` unless Chrome is replaced with another configured browser.

Before running the test, review:

- `AutomateFaceBook/src/test/resources/login_info.xls`
- `AutomateFaceBook/src/test/resources/config.properties`

On Windows, if you are in the parent directory, enter the project directory first:

```powershell
cd .\facebook_login
mvn -pl AutomateFaceBook -Dtest=faceBookAutomation.RegressionTest#faceBookLogin test
```

Maven downloads the dependencies declared in the module POM on the first run. A successful Maven run ends with `BUILD SUCCESS`. The login method uses a TestNG data provider, so it runs once for each data row in the workbook.

## Test data and configuration

- **Login data:** `AutomateFaceBook/src/test/resources/login_info.xls`, worksheet `Sheet1`. The first row is treated as a header; subsequent rows provide the username and password used by the test.
- **Browser and demo setting:** `AutomateFaceBook/src/test/resources/config.properties`. `browserType` defaults to `Chrome`. Supported values in the browser setup are `Chrome`, `Firefox`, and `IE`. `demoMode` controls a log message; it does not change the test flow.
- **Page locators:** `AutomateFaceBook/src/test/resources/objectPropertiesFile/objectMap.properties`. Locator entries map logical names used in the test to Selenium locator types and values.

These paths are relative to the project directory because the test code opens the files by relative path. Review the workbook before running the test and keep credentials out of source control.

## Project layout

```text
facebook_login/
├── pom.xml
├── README.md
└── AutomateFaceBook/
    ├── pom.xml
    └── src/test/
        ├── java/
        │   ├── com/custom/library/       # Selenium helpers, configuration, Excel and reporting utilities
        │   └── faceBookAutomation/       # Facebook page objects and RegressionTest
        └── resources/
            ├── config.properties
            ├── login_info.xls
            ├── log4j.properties
            └── objectPropertiesFile/
                └── objectMap.properties
```

The root POM is a Maven aggregator for the `AutomateFaceBook` module. Test sources are under `src/test/java`; there is no separate application runtime.

## What the test does

`faceBookAutomation.RegressionTest#faceBookLogin` reads the workbook data, opens Facebook in the configured browser, enters each username and password, and clicks the login button. The current flow checks that the initial page title contains “Facebook”; it does not verify that login succeeds.

## Test output

Maven and the test utilities write output under `AutomateFaceBook/target/`, including:

- Surefire test results in `surefire-reports/`
- Log files in `logs/`
- Screenshots in `images/` when a test fails

## Troubleshooting

- **Maven reports “no POM in this directory”:** Change into the directory containing a POM before running Maven:

  ```powershell
  cd C:\Users\nanah\Desktop\Develop\SeleniumJava\facebook_login
  Get-Location
  Test-Path .\pom.xml
  mvn test
  ```

  `Test-Path .\pom.xml` should print `True`. For the module-only command, change to the module directory instead:

  To run only the Facebook test:

  ```powershell
  cd C:\Users\nanah\Desktop\Develop\SeleniumJava\facebook_login\AutomateFaceBook
  mvn test "-Dtest=faceBookAutomation.RegressionTest#faceBookLogin"
  ```

- **Maven says the selected project is not in the reactor:** Run the command from the `facebook_login` directory, where the root `pom.xml` is located.
- **ChromeDriver cannot start or reports a version mismatch:** Update the bundled driver to a version compatible with the installed Chrome, or configure the driver path in the Selenium browser setup.
- **The browser opens but the login flow does not complete:** Check that the workbook contains valid authorized test data and consider whether Facebook is showing CAPTCHA, MFA, or another challenge.
- **Maven cannot download dependencies:** Check network access and Maven repository configuration, then rerun the Maven command.
