package com.cucumber.definitions;

import com.cucumber.utility.RestTemplateUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepDefsHelloTest extends RestTemplateUtility {

    @When("^the client calls /baeldung$")
    public void the_client_issues_POST_hello() throws Throwable {
        executePost("http://localhost:8082/baeldung");
    }

    @Given("^the client calls /hello$")
    public void the_client_issues_GET_hello() throws Throwable {
        executeGet("http://localhost:8082/hello");
    }

    @Then("^the baeldung client receives status code of (\\d+)$")
    public void the_baeldung_client_receives_status_code_of(int statusCode, String docString) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
        assertThat(docString, is("new lines added"));
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

}