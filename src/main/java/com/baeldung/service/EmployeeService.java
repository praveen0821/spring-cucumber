package com.baeldung.service;

import com.baeldung.entity.EmployeeEntity;
import com.baeldung.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepo;

  @Transactional
  @Validated
  public EmployeeEntity create(EmployeeEntity employee) {
    return employeeRepo.save(employee);
  }

}
