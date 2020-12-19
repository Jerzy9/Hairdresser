package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.input.EmployeeInput;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeDao {

    Employee insertEmployee(EmployeeInput employeeInput);

    Optional<Employee> getEmployeeById(UUID id);

    List<Employee> getAllEmployees();

    Optional<Employee> deleteEmployeeById(UUID id);

    Optional<Employee> editEmployeeById(Employee employee);
}
