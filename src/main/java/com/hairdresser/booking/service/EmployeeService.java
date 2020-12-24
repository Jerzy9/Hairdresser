package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.exception.EmployeeNotFoundException;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Day;
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
    @Autowired @Qualifier("fakeEmployee")
    private final EmployeeDao employeeDao;

    public Employee insertEmployee(EmployeeInput employeeInput) {
        // If calendar model will be ready,
        // there is a place for basic calendar config(in separate method)
        // e.g. employee.setCalendar(basicConfig(employee.getCalendar()));

        return employeeDao.insertEmployee(employeeInput);
    }

    public Employee getEmployeeById(UUID id) {
        Optional<Employee> optionalEmployee = employeeDao.getEmployeeById(id);
        return optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee deleteEmployeeById(UUID id) {
        Optional<Employee> optionalEmployee = employeeDao.deleteEmployeeById(id);
        return optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
    }

    //Edit only variables which are't nulls, otherwise do nothing
    public Employee editEmployeeById(UUID id, EmployeeInput employeeInput) {
        //Get employee to edit
        Optional<Employee> employeeToEdit = employeeDao.getEmployeeById(id);

        //Store new variables
        String newName = employeeInput.getName();
        String newDescription = employeeInput.getDescription();
        List<UUID> newHairstyles = employeeInput.getHairstyles();
        Calendar newCalendar = employeeInput.getCalendar();

        //Replace variables given by user
        employeeToEdit.ifPresent(emp -> {
            if(newName != null) emp.setName(newName);
            if(newDescription != null) emp.setDescription(newDescription);
            if(newHairstyles != null) emp.setHairstyles(newHairstyles);
            if (newCalendar != null) emp.setCalendar(newCalendar);
        });

        //Replace old object with the edited one
        Optional<Employee> employeeEdited = employeeDao.editEmployeeById(employeeToEdit.get());

        return employeeEdited.orElseThrow(EmployeeNotFoundException::new);
    }


    public List<Employee> getEmployeesWithThisHairstyle(UUID hairstyleId) {
        // get all employees
        return employeeDao.getAllEmployees().stream().filter(emp -> {

            // return only employees who are able to make given hairstyle
           return emp.getHairstyles().stream().anyMatch(id -> id.equals(hairstyleId));
       }).collect(Collectors.toList());
    }

    public List<Integer> getAvailableDatesOfVisit(UUID employeeId, int time) {
        Optional<Employee> employee = employeeDao.getEmployeeById(employeeId);
        int breakTime = 15*60;
        List<Integer> possibleVisitTimeTables = new ArrayList<>();

        //For every day in work, print possible dates for new visit
        if(employee.isPresent()) {
            for (Day day : employee.get().getCalendar().getWorkDays()) {

                int start = day.getStart();
                int endOfTheDay = day.getEnd();
                int sum = start + time + breakTime;

                //If List<Visit> is empty, just add every possible date
                if (day.getVisits().isEmpty()) {
                    for (int i = start; i < endOfTheDay ; i+=breakTime)
                        possibleVisitTimeTables.add(i);

                } else {
                    int i = 0;
                    while (sum < endOfTheDay) {

                        if (sum < day.getVisits().get(i).getStart()) {
                            possibleVisitTimeTables.add(start);
                            start +=breakTime;
                        } else {
                            i++;
                            start = day.getVisits().get(i).getStart();
                        }
                        sum = start + time + breakTime;
                    }

                }

            }
        }
        return possibleVisitTimeTables;
    }
}
