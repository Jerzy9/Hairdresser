package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Visit;
import org.checkerframework.checker.nullness.Opt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeDao {

    //Employee
    Employee insertEmployee(Employee employee);
    Optional<Employee> getEmployeeById(UUID id);
    List<Employee> getAllEmployees();
    Optional<Employee> deleteEmployeeById(UUID id);
    Optional<Employee> editEmployeeById(Employee employee);

    //CALENDAR
    //Days
    Optional<Day> insertDayAtWork(UUID employeeId, Day day);
    Optional<Day> getDayAtWorkById(UUID employeeId, UUID dayId);
    Optional<List<Day>> getAllDaysAtWork(UUID employeeId);
    Optional<Day> deleteDayById(UUID employeeId, UUID dayId);
    Optional<Day> editDayById(UUID employeeId, Day day);

    //Visits
    Optional<Visit> insertVisit(UUID employeeId, UUID dayId, Visit visit);
    Optional<Visit> getVisitById(UUID employeeId, UUID dayId, UUID visitId);
    Optional<List<Visit>> getAllVisits(UUID employeeId, UUID dayId);
    Optional<Visit> deleteVisitById(UUID employeeId, UUID dayId, UUID visitId);
    Optional<Visit> editVisitById(UUID employeeId, UUID dayId, Visit visit);
}
