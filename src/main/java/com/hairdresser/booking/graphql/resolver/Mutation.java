package com.hairdresser.booking.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.model.input.CalendarInput;
import com.hairdresser.booking.model.input.EmployeeInput;
import com.hairdresser.booking.model.input.HairstyleInput;
import com.hairdresser.booking.service.CalendarService;
import com.hairdresser.booking.service.EmployeeService;
import com.hairdresser.booking.service.HairstyleService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {

    private final HairstyleService hairstyleService;
    private final EmployeeService employeeService;
    private final CalendarService calendarService;

    //Hairstyle
    public Hairstyle insertHairstyle(HairstyleInput hairstyleInput) {
        return hairstyleService.insertHairstyle(hairstyleInput);
    }

    public Hairstyle deleteHairstyleById(String id) {
        return hairstyleService.deleteHairstyleById(id);
    }

    public Hairstyle editHairstyleById(String id, HairstyleInput hairstyleInput) {
        return hairstyleService.editHairstyleById(id, hairstyleInput);
    }

    //Employee
    public Employee insertEmployee(EmployeeInput employeeInput) {
        return employeeService.insertEmployee(employeeInput);
    }

    public Employee deleteEmployeeById(String id) {
        return employeeService.deleteEmployeeById(id);
    }

    public Employee editEmployeeById(String id, EmployeeInput employeeInput) {
        return employeeService.editEmployeeById(id, employeeInput);
    }

    //Calendar
    public Calendar editCalendarByEmployeeId(String employeeId, CalendarInput calendarInput) {
        return calendarService.editCalendarByEmployeeId(employeeId, calendarInput);
    }
}
