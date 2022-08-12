package com.baeldung.definitions;

import com.baeldung.SpringDemoApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberGlueTest {

}