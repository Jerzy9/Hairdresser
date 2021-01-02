package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Visit;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    //Employee
    Employee insertEmployee(Employee employee);
    Optional<Employee> getEmployeeById(String id);
    List<Employee> getAllEmployees();
    Optional<Employee> deleteEmployeeById(String id);
    Optional<Employee> editEmployeeById(Employee employee);

    //CALENDAR
    //Days
    Optional<Day> insertDayAtWork(String employeeId, Day day);
    Optional<Day> getDayAtWorkById(String employeeId, String dayId);
    Optional<Day> deleteDayById(String employeeId, String dayId);

    //Visits
    Optional<Visit> insertVisit(String employeeId, String dayId, Visit visit);
    Optional<Visit> getVisitById(String employeeId, String dayId, String visitId);
    Optional<List<Visit>> getAllVisits(String employeeId, String dayId);
    Optional<Visit> deleteVisitById(String employeeId, String dayId, String visitId);
}
