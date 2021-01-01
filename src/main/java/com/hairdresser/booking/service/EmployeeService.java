package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.exception.EmployeeNotFoundException;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.input.CalendarInput;
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
    @Autowired @Qualifier("MongoDBEmployee")
    private final EmployeeDao employeeDao;

    public Employee insertEmployee(EmployeeInput employeeInput) {
        Employee newEmp = new Employee(null, employeeInput.getName(), employeeInput.getDescription(), employeeInput.getHairstyles(), new Calendar());

        return employeeDao.insertEmployee(newEmp);
    }

    public Employee getEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeDao.getEmployeeById(id);
        return optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee deleteEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeDao.deleteEmployeeById(id);
        return optionalEmployee.orElseThrow(EmployeeNotFoundException::new);
    }

    //Edit only variables which are't nulls, otherwise do nothing
    public Employee editEmployeeById(String id, EmployeeInput employeeInput) {
        //Get employee to edit
        Optional<Employee> employeeToEdit = employeeDao.getEmployeeById(id);

        //Store new variables
        String newName = employeeInput.getName();
        String newDescription = employeeInput.getDescription();
        List<String> newHairstyles = employeeInput.getHairstyles();

        //Replace variables given by user
        employeeToEdit.ifPresent(emp -> {
            if(newName != null) emp.setName(newName);
            if(newDescription != null) emp.setDescription(newDescription);
            if(newHairstyles != null) emp.setHairstyles(newHairstyles);
        });

        //Replace old object with the edited one
        Optional<Employee> employeeEdited = employeeDao.editEmployeeById(employeeToEdit.get());

        return employeeEdited.orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getEmployeesWithThisHairstyle(String hairstyleId) {
        // get all employees
        Optional<List<Employee>> employees =  Optional.of(employeeDao.getAllEmployees().stream().filter(emp -> {

            // return only employees who are able to make given hairstyle
           return emp.getHairstyles().stream().anyMatch(id -> id.equals(hairstyleId));
       }).collect(Collectors.toList()));

        return employees.orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Integer> getAvailableDatesOfVisit(String employeeId, int time) {
        Optional<Employee> employee = employeeDao.getEmployeeById(employeeId);
        int breakTime = 15*60;
        List<Integer> possibleVisitTimeTables = new ArrayList<>();

        //For every day in work, print possible dates for new visit
        if(employee.isPresent()) {
            for (Day day : employee.get().getCalendar().getDaysAtWork()) {
                int start = day.getStart();
                int endOfTheDay = day.getEnd();
                int sum = start + time + breakTime;

                //First check, if there any are already booked visits for that day,
                //If there are, check each break between these visits and try to fit there a new visit
                //When the break is too short, go to the next one
                if(!day.getVisits().isEmpty()) {
                    int i = 0;
                    int length = day.getVisits().size();
                    while (i < length && sum < endOfTheDay) {

                        if (sum < day.getVisits().get(i).getStart()) {
                            possibleVisitTimeTables.add(start);
                            start +=breakTime;
                        } else {
                            start = day.getVisits().get(i).getEnd() + breakTime;
                            i++;
                        }
                        sum = start + time + breakTime;
                    }
                }
                //Add available dates, if there was no visit at all on that day or
                //when there was a time between last visit and end of the work
                for (int i = start; i < endOfTheDay - time; i+=breakTime)
                    possibleVisitTimeTables.add(i);
            }
        }
        return possibleVisitTimeTables;
    }
}
