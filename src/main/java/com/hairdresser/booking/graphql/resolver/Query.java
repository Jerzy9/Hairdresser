package com.hairdresser.booking.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.service.EmployeeService;
import com.hairdresser.booking.service.HairstyleService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

    private final HairstyleService hairstyleService;
    private final EmployeeService employeeService;

    //Hairstyle
    public Hairstyle getHairstyleById(String id) {
        return hairstyleService.getHairstyleById(id);
    }

    public List<Hairstyle> getAllHairstyles() {
       return hairstyleService.getAllHairstyles();
    }

    //Employee
    public Employee getEmployeeById(String id) {
        return  employeeService.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
