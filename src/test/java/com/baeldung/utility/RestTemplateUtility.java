package com.baeldung.utility;

import com.baeldung.SpringDemoApplication;
import com.baeldung.entity.EmployeeEntity;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@CucumberContextConfiguration
//@SpringBootTest(classes = SpringDemoApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class RestTemplateUtility {
    public static ResponseResults latestResponse = null;

    static ResponseEntity<String> responseAsString = null;
    protected static ResponseEntity<EmployeeEntity> responseAsEmpType = null;

    @Autowired
    protected RestTemplate restTemplate;

    public void executeGet(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        HttpEntity entity = new HttpEntity(httpHeaders);

        restTemplate.setErrorHandler(errorHandler);
        responseAsString = restTemplate.exchange(url, HttpMethod.GET, entity,
                String.class);
        latestResponse = restTemplate.execute(url, HttpMethod.GET, requestCallback, response -> {
            if (errorHandler.hadError) {
                return (errorHandler.getResults());
            } else {
                return (new ResponseResults(response));
            }
        });
    }

    protected EmployeeEntity postEmployee(String url, EmployeeEntity payload) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<EmployeeEntity> httpEntity = new HttpEntity(payload, headers);
        responseAsEmpType = restTemplate.postForEntity(url, httpEntity, EmployeeEntity.class);
        return responseAsEmpType.getBody();
    }

    public void executePost(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate
          .execute(url, HttpMethod.POST, requestCallback, response -> {
              if (errorHandler.hadError) {
                  return (errorHandler.getResults());
              } else {
                  return (new ResponseResults(response));
              }
          });
    }

    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResults results = null;
        private Boolean hadError = false;

        private ResponseResults getResults() {
            return results;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            results = new ResponseResults(response);
        }
    }
}