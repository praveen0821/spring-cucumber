package com.cucumber.controller;

import com.cucumber.entity.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaeldungController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/baeldung")
    public Address sayHelloPost() {
        Address address = new Address();
        address.setDoorNo("169");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setPincode(560077L);
        return address;
    }
}
