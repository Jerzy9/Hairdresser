package com.hairdresser.booking.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.model.input.EmployeeInput;
import com.hairdresser.booking.model.input.HairstyleInput;
import com.hairdresser.booking.service.EmployeeService;
import com.hairdresser.booking.service.HairstyleService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {

    private final HairstyleService hairstyleService;
    private final EmployeeService employeeService;

    //Hairstyle
    public Hairstyle insertHairstyle(HairstyleInput newHairstyleInput) {
        return hairstyleService.insertHairstyle(newHairstyleInput);
    }

    public Hairstyle deleteHairstyleById(UUID id) {
        return hairstyleService.deleteHairstyleById(id);
    }

    public Hairstyle editHairstyleById(UUID id, HairstyleInput newHairstyleInput) {
        return hairstyleService.editHairstyleById(id, newHairstyleInput);
    }

    //Employee
    public Employee insertEmployee(EmployeeInput employeeInput) {
        return employeeService.insertEmployee(employeeInput);
    }

    public Employee deleteEmployeeById(UUID id) {
        return employeeService.deleteEmployeeById(id);
    }

    public Employee editEmployeeById(UUID id, EmployeeInput employeeInput) {
        return employeeService.editEmployeeById(id, employeeInput);
    }
}
