package com.hairdresser.booking.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.model.Visit;
import com.hairdresser.booking.service.DayService;
import com.hairdresser.booking.service.EmployeeService;
import com.hairdresser.booking.service.HairstyleService;
import com.hairdresser.booking.service.VisitService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

    private final HairstyleService hairstyleService;
    private final EmployeeService employeeService;
    private final DayService dayService;
    private final VisitService visitService;

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

    public List<Employee> getEmployeesWithThisHairstyle(String hairstyleId) {
        return employeeService.getEmployeesWithThisHairstyle(hairstyleId);
    }

    //Day
    public Day getDayById(String employeeId, String dayId) {
        return dayService.getDayById(employeeId, dayId);
    }

    //Visit
    public Visit getVisitById(String employeeId, String dayId, String visitId) {
        return visitService.getVisitById(employeeId, dayId, visitId);
    }
}

