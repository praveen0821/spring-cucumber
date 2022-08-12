package com.cucumber.runner;


import com.cucumber.SpringDemoApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class SpringRunnerTest {
    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
        Assertions.assertNotNull("Test run");
    }
}
