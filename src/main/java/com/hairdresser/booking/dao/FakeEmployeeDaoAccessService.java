package com.hairdresser.booking.dao;

import com.google.common.collect.Lists;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Visit;
import com.hairdresser.booking.model.input.EmployeeInput;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeEmployee")
public class FakeEmployeeDaoAccessService implements EmployeeDao {

    private List<Employee> employees = Lists.newArrayList(
            new Employee(UUID.fromString("3b7b4052-2603-4043-8f82-33a05b76f61d"),
                    "Adam",
                    "Hairdresser senior",
                    Lists.newArrayList(UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e"), UUID.fromString("a1fd9c09-c064-4c26-9d18-6151a369eeec")),
                    new Calendar()),
            new Employee(UUID.fromString("5c0d8d00-57ca-4968-8c4a-30a5028a8f9b"),
                    "Monika",
                    "Hairdresser junior",
                    Lists.newArrayList(UUID.fromString("a1fd9c09-c064-4c26-9d18-6151a369eeec")),
                    new Calendar())
            );

    ////**Employees**////
    @Override
    public Employee insertEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeById(UUID id) {
        return employees.stream().filter(employee -> employee.getId().equals(id)).findFirst();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Optional<Employee> deleteEmployeeById(UUID id) {
        Optional<Employee> employeeToDelete = getEmployeeById(id);
        employeeToDelete.ifPresent(emp-> employees.remove(emp));

        return employeeToDelete;
    }

    @Override
    //Edit only variables which are not nulls, otherwise do nothing
    public Optional<Employee> editEmployeeById(Employee employee) {
        Optional<Employee> employeeToEdit = getEmployeeById(employee.getId());
        employeeToEdit.ifPresent(emp-> emp = employee);

        return employeeToEdit;
    }


    ////**Days**////
    @Override
    public Optional<Day> insertDayAtWork(UUID employeeId, Day day) {
        Optional<Employee> employee = getEmployeeById(employeeId);

        employee.ifPresent(emp -> {
            emp.getCalendar().getDaysAtWork().add(day);
        });
        return getDayAtWorkById(employeeId, day.getId());
    }

    @Override
    public Optional<Day> getDayAtWorkById(UUID employeeId, UUID dayId) {
        Optional<Employee> employee = getEmployeeById(employeeId);
        Optional<Day> day = Optional.empty();

        if (employee.isPresent())
            day = employee.get().getCalendar().getDaysAtWork().stream().filter(d -> d.getId().equals(dayId)).findFirst();

        return day;
    }

    @Override
    public Optional<List<Day>> getAllDaysAtWork(UUID employeeId) {
        Optional<Employee> employee = getEmployeeById(employeeId);
        Optional<List<Day>> daysAtWork = Optional.empty();

        if(employee.isPresent())
            daysAtWork = Optional.ofNullable(employee.get().getCalendar().getDaysAtWork());

        return daysAtWork;
    }

    @Override
    public Optional<Day> deleteDayById(UUID employeeId, UUID dayId) {
        return Optional.empty();
    }

    @Override
    public Optional<Day> editDayById(UUID employeeId, Day day) {
        return Optional.empty();
    }


    ////**Visits**////
    @Override
    public Optional<Visit> insertVisit(UUID employeeId, UUID dayId, Visit visit) {
        return null;
    }

    @Override
    public Optional<Visit> getVisitById(UUID employeeId, UUID dayId, UUID visitId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Visit>> getAllVisits(UUID employeeId, UUID dayId) {
        return null;
    }

    @Override
    public Optional<Visit> deleteVisitById(UUID employeeId, UUID dayId, UUID visitId) {
        return Optional.empty();
    }

    @Override
    public Optional<Visit> editVisitById(UUID employeeId, UUID dayId, Visit visit) {
        return Optional.empty();
    }
}
