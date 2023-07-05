Feature: Test Employee Service Rest API’s with rest assured library and cucumber framework

#@RestAPITest
#Scenario Outline: Employee GET API test

#Given the valid endpoint to fetch employees
#When the request is send to server 
#Then validate the response of first employee record having name as Tiger Nixon

#Examples:
    #|page|name |
    #| 2  |michael.lawson@reqres.in|
    #| 1  |george.bluth@reqres.in|

@RestAPITest
Scenario Outline: Employee Service API CRUD operations

Given the valid endpoint with payload to create employee
When the request is send to the server
And the new employee must be created with name as test
And the new employee must be created must be fetched
And the new employee created must be updated
Then the new employee created must be deleted

Examples:
    |empname|
    | john  |
