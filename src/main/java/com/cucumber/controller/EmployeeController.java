package com.cucumber.controller;

import com.cucumber.entity.EmployeeEntity;
import com.cucumber.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<EmployeeEntity> create(@RequestBody EmployeeEntity employee, UriComponentsBuilder uriComponentsBuilder) {
     EmployeeEntity output =  employeeService.create(employee);
     ResponseEntity<EmployeeEntity> response = null;
     if (output != null){
       response = new ResponseEntity<>(output, HttpStatus.CREATED);
     }
     return response;

//    final URI uri = uriComponentsBuilder.path("/v1/employees/{id}")
//        .build(id);
//
//    return ResponseEntity.created(uri)
//        .build();
  }

}
