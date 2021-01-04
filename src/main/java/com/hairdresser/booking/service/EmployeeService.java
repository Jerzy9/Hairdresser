package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.exception.EmployeeNotFoundException;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.input.CalendarInput;
import com.hairdresser.booking.model.input.EmployeeInput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Autowired @Qualifier("MongoDBEmployee")
    private final EmployeeDao employeeDao;

    public Employee insertEmployee(EmployeeInput employeeInput) {
        Employee newEmp = new Employee(null, employeeInput.getName(), employeeInput.getDescription(), employeeInput.getHairstyles(), new Calendar());

        return employeeDao.insertEmployee(newEmp);
    }

    public Employee getEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeDao.getEmployeeById(id);
        return optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee deleteEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeDao.deleteEmployeeById(id);
        return optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
    }

    //Edit only variables which are't nulls, otherwise do nothing
    public Employee editEmployeeById(String id, EmployeeInput employeeInput) {
        //Get employee to edit
        Optional<Employee> employeeToEdit = employeeDao.getEmployeeById(id);

        //Store new variables
        String newName = employeeInput.getName();
        String newDescription = employeeInput.getDescription();
        List<String> newHairstyles = employeeInput.getHairstyles();

        //Replace variables given by user
        employeeToEdit.ifPresent(emp -> {
            if(newName != null) emp.setName(newName);
            if(newDescription != null) emp.setDescription(newDescription);
            if(newHairstyles != null) emp.setHairstyles(newHairstyles);
        });

        //Replace old object with the edited one
        Optional<Employee> employeeEdited = employeeDao.editEmployeeById(employeeToEdit.get());

        return employeeEdited.orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getEmployeesWithThisHairstyle(String hairstyleId) {
        // get all employees
        Optional<List<Employee>> employees =  Optional.of(employeeDao.getAllEmployees().stream().filter(emp -> {

            // return only employees who are able to make given hairstyle
           return emp.getHairstyles().stream().anyMatch(id -> id.equals(hairstyleId));
       }).collect(Collectors.toList()));

        return employees.orElseThrow(EmployeeNotFoundException::new);
    }
}
