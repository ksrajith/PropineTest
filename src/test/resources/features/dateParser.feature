Feature: Test date parser

  # Pass valid parameters here
  Scenario Outline: user selects valid dates
    Given Validate is form available to fill
    When Add "<inputDateValue>" date to text field
    And Click submit button
    Then results should be populated as "<resultValue>"
    Examples:
      |inputDateValue|resultValue|
      |5-31-2020     |Sun May 31 2020 00:00:00 GMT+0000|
      |22/Oct/19 05:30 |Tue Oct 22 2019 05:30:00 GMT+0000|
      |5-31-2020 05:10:12 |Sun May 31 2020 05:10:12 GMT+0000|
      |1 1 12 |Sun Jan 01 2012 00:00:00 GMT+0000|
      |15/Oct/19 2100 |Tue Oct 15 2019 00:00:00 GMT+0000|

 # Pass invalid parameters here
  Scenario Outline: user selects invalid dates
    Given Validate is form available to fill
    When Add "<inputDateValue>" date to text field
    And Click submit button
    Then results should be populated as "<resultValue>"
    Examples:
      |inputDateValue|resultValue|
      |testDate |Invalid date|
      |Sun Feb 03 4565 00:00:00 IST+0000 |Invalid date|
      |ABC Feb 03 4565 00:00:00 IST+0000 |Invalid date|
      |Sun Ind 03 4565 00:00:00 IST+0000 |Invalid date|
