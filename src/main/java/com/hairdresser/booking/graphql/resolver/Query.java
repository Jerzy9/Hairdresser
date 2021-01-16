package com.hairdresser.booking.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hairdresser.booking.model.Number;
import com.hairdresser.booking.model.*;
import com.hairdresser.booking.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RequiredArgsConstructor
//@EnableWebSecurity
public class Query implements GraphQLQueryResolver {

    private final HairstyleService hairstyleService;
    private final EmployeeService employeeService;
    private final CalendarService calendarService;
    private final DayService dayService;
    private final VisitService visitService;

    //Hairstyle
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Hairstyle getHairstyleById(String id) {
        return hairstyleService.getHairstyleById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    public List<Employee> getEmployeesWithThisHairstyle(String hairstyleId) {
        return employeeService.getEmployeesWithThisHairstyle(hairstyleId);
    }

    //Calendar
    public List<Number> getAvailableDatesOfVisit(String employeeId, int time) {
        return calendarService.getAvailableDatesOfVisit(employeeId, time);
    }

    public Calendar basicCalendarConfig30Days(String employeeId) {
        return calendarService.basicCalendarConfig30Days(employeeId);
    }

    //Day
    public Day getDayById(String employeeId, String dayId) {
        return dayService.getDayById(employeeId, dayId);
    }

    //Visit
    public Visit getVisitById(String employeeId, String dayId, String visitId) {
        return visitService.getVisitById(employeeId, dayId, visitId);
    }

    public List<Visit> getAllVisits(String employeeId, String dayId) {
        return visitService.getAllVisits(employeeId, dayId);
    }
}

