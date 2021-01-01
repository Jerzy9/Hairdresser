package com.hairdresser.booking.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hairdresser.booking.model.*;
import com.hairdresser.booking.model.input.*;
import com.hairdresser.booking.service.*;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {

    private final HairstyleService hairstyleService;
    private final EmployeeService employeeService;
    private final CalendarService calendarService;
    private final DayService dayService;
    private final VisitService visitService;

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

    //Day
    public Day insertDay(String employeeId, DayInput dayInput) {
        return dayService.insertDay(employeeId, dayInput);
    }

    public Day deleteDayById(String employeeId, String dayId) {
        return dayService.deleteDayById(employeeId, dayId);
    }

    //Visit
    public Visit insertVisit(String employeeId, String dayId, VisitInput visitInput) {
        return visitService.insertVisit(employeeId, dayId, visitInput);
    }

    public Visit deleteVisitById(String employeeId, String dayId, String visitId) {
        return visitService.deleteVisitById(employeeId, dayId, visitId);
    }

}
