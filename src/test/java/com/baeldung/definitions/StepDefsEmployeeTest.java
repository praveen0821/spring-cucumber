package com.baeldung.definitions;

import com.baeldung.entity.EmployeeEntity;
import com.baeldung.entity.PhoneEntity;
import com.baeldung.utility.RestTemplateUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StepDefsEmployeeTest extends RestTemplateUtility {

    private List<EmployeeEntity> empList = new ArrayList<>();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Given("^user wants to create an employee with the following attributes$")
    public void the_create_employee(DataTable dataTable) throws IOException {
        List<Map<String, String>> userList = dataTable.asMaps(String.class, String.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for(Map<String, String> e : userList) {
            EmployeeEntity emp = new EmployeeEntity();
            emp.setId(Long.parseLong(e.get("id")));
            emp.setFirstName(e.get("firstName"));
            emp.setLastName(e.get("lastName"));
            emp.setDateOfBirth(LocalDate.parse(e.get("dateOfBirth"), formatter));
            emp.setStartDate(LocalDate.parse(e.get("startDate"), formatter));
            emp.setEmploymentType(e.get("employmentType"));
            emp.setEmail(e.get("email"));
            empList.add(emp);
            System.out.println(empList);
        }
    }


    @And("^with the following phone numbers$")
    public void the_phone_lists(DataTable dataTable) {
        List<PhoneEntity> phoneList = new ArrayList<>();
        List<Map<String, String>> userList = dataTable.asMaps(String.class, String.class);

        for(Map<String, String> e : userList) {
            PhoneEntity phone = new PhoneEntity();
            phone.setId(Long.parseLong(e.get("id")));
            phone.setType(e.get("type"));
            phone.setIsdCode(e.get("isdCode"));
            phone.setPhoneNumber(e.get("phoneNumber"));
            phone.setExtension(e.get("extension"));
            phoneList.add(phone);
            empList.get(0).setPhones(phoneList);
            System.out.println(phoneList);
        }
    }

    @When("user saves the new employee {string}")
    public void saves_the_new_employee(String fields) {
        EmployeeEntity res = postEmployee("http://localhost:8082/employees/create", empList.get(0));
        assertThat(responseAsEmpType.getBody()).isNotNull();
    }

    @Then("the save {string}")
    public void the_save_is_success_or_not(String saveSuccess) {
        String expectedResult = "FAILS";
        if (responseAsEmpType.getBody() != null) {
            assertThat(responseAsEmpType.getStatusCode().value()).isIn(200, 201);
        } else {
            assertThat(responseAsEmpType.getStatusCode().value()).isBetween(400, 504);
        }
    }
}