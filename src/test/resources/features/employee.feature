Feature: Create Employee

  @Test4
  Scenario: Save employee details to the database

    Given user wants to create an employee with the following attributes
      | id  | firstName | lastName | dateOfBirth | startDate  | employmentType | email               |
      | 100 | Rachel    | Green    | 1990-01-01  | 2018-01-01 | Permanent      | rachel.green@fs.com |
      | 101 | Michel    | Green    | 1991-01-01  | 2019-01-01 | Permanent      | michel.green@fs.com |

    And with the following phone numbers
      | id  | type   | isdCode | phoneNumber | extension |
      | 102 | Mobile | +1      | 2141112222  |           |
      | 103 | Office | +1      | 8362223000  | 333       |

    When user saves the new employee 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'