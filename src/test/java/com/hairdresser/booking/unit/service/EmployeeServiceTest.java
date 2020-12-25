package com.hairdresser.booking.unit.service;

import com.google.common.collect.Lists;
import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.model.*;
import com.hairdresser.booking.service.EmployeeService;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDao employeeDao;

    @Test
    public void getEmployeesWithThisHairstyle_inputHairstyleID_returnListOfEmployees() {
        UUID hairstyleId = UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e");

        Mockito.when(employeeDao.getAllEmployees()).thenReturn(Lists.newArrayList(
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
        ));

        List<Employee> list = employeeService.getEmployeesWithThisHairstyle(hairstyleId);
        System.out.println(list);
        assertNotNull(list);
        assertEquals(hairstyleId, list.get(0).getHairstyles().get(0));
    }

    @Test
    public void getAvailableDatesOfVisits_inputEmptyVisits_returnListOfEveryDate() {
        UUID hairstyleId = UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e");
        UUID employeeId = UUID.fromString("3b7b4052-2603-4043-8f82-33a05b76f61d");
        int timeOfHairstyle = 35*60;

        Employee employee = new Employee(UUID.fromString("3b7b4052-2603-4043-8f82-33a05b76f61d"),
                "Adam",
                "Hairdresser senior",
                Lists.newArrayList(UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e"), UUID.fromString("a1fd9c09-c064-4c26-9d18-6151a369eeec")),
                new Calendar());

        //Empty List visits
        Day newDay = new Day(UUID.randomUUID(), 1608796800 ,1608832800, new ArrayList<>());
        employee.getCalendar().getWorkDays().add(newDay);

        Mockito.when(employeeDao.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        List<Integer> returns = employeeService.getAvailableDatesOfVisit(employeeId, timeOfHairstyle);
        System.out.println(returns);
        assertFalse(returns.isEmpty());
    }

    @Test
    public void getAvailableDatesOfVisits_inputFewVisits_returnListOfAvailableDate() {
        UUID hairstyleId = UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e");
        UUID employeeId = UUID.fromString("3b7b4052-2603-4043-8f82-33a05b76f61d");
        int timeOfHairstyle = 35*60;

        Employee employee = new Employee(UUID.fromString("3b7b4052-2603-4043-8f82-33a05b76f61d"),
                "Adam",
                "Hairdresser senior",
                Lists.newArrayList(UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e"), UUID.fromString("a1fd9c09-c064-4c26-9d18-6151a369eeec")),
                new Calendar());

        //Empty List visits
        Day newDay = new Day(UUID.randomUUID(), 1608796800 ,1608832800, Lists.newArrayList(
                new Visit(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 1608801300, 1608805800,""),
                new Visit(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 1608809400, 1608811200,""),
                new Visit(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 1608815700, 1608825600,"")
        ));
        employee.getCalendar().getWorkDays().add(newDay);

        List<Integer> output = Lists.newArrayList(1608796800, 1608797700, 1608812100, 1608826500, 1608827400, 1608828300, 1608829200, 1608830100);

        Mockito.when(employeeDao.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        List<Integer> returns = employeeService.getAvailableDatesOfVisit(employeeId, timeOfHairstyle);
        System.out.println(returns);
        assertFalse(returns.isEmpty());

        assertEquals(output, returns);
    }

    @Test
    public void getAvailableDatesOfVisits_inputFullVisits_returnEmptyList() {
        UUID hairstyleId = UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e");
        UUID employeeId = UUID.fromString("3b7b4052-2603-4043-8f82-33a05b76f61d");
        int timeOfHairstyle = 70*60;

        Employee employee = new Employee(UUID.fromString("3b7b4052-2603-4043-8f82-33a05b76f61d"),
                "Adam",
                "Hairdresser senior",
                Lists.newArrayList(UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e"), UUID.fromString("a1fd9c09-c064-4c26-9d18-6151a369eeec")),
                new Calendar());

        //Empty List visits
        Day newDay = new Day(UUID.randomUUID(), 1608796800 ,1608825600, Lists.newArrayList(
                new Visit(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 1608801300, 1608805800,""),
                new Visit(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 1608809400, 1608811200,""),
                new Visit(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 1608815700, 1608825600,"")
        ));
        employee.getCalendar().getWorkDays().add(newDay);

        Mockito.when(employeeDao.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        List<Integer> returns = employeeService.getAvailableDatesOfVisit(employeeId, timeOfHairstyle);
        System.out.println(returns);
        assertTrue(returns.isEmpty());
    }

}
