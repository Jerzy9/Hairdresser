package com.hairdresser.booking.unit.service;

import com.google.common.collect.Lists;
import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Number;
import com.hairdresser.booking.model.Visit;
import com.hairdresser.booking.service.CalendarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
public class CalendarServiceTest {

    @Autowired
    private CalendarService calendarService;

    @MockBean
    private EmployeeDao employeeDao;

    @Test
    public void basicConfig30Days_inputEmptyCalendar_returnCalendarWith30Days() {
        String employeeId = "5ff05c6a97d1a457d9f8dadd";
        Employee employee = new Employee("3b7b4052-2603-4043-8f82-33a05b76f61d",
                "Adam",
                "Hairdresser senior",
                Lists.newArrayList("2b01e86f-f5ce-4415-9c9e-40340e201b9e", "a1fd9c09-c064-4c26-9d18-6151a369eeec"),
                new Calendar(new ArrayList<>(), new ArrayList<>()));

        Mockito.when(employeeDao.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        Calendar calendarAfterConfig = calendarService.basicCalendarConfig30Days(employeeId);
        assertEquals(30, calendarAfterConfig.getDaysAtWork().size());
        assertEquals(24*60*60, calendarAfterConfig.getDaysAtWork().get(1).getStart()-calendarAfterConfig.getDaysAtWork().get(0).getStart());
    }

    @Test
    public void getAvailableDatesOfVisits_inputEmptyVisits_returnListOfEveryDate() {
        String employeeId = "3b7b4052-2603-4043-8f82-33a05b76f61d";
        int timeOfHairstyle = 30*60;
        int startOfWork = (int) Instant.now().getEpochSecond();
        int endOfWork = startOfWork + (8*60*60);

        Employee employee = new Employee("3b7b4052-2603-4043-8f82-33a05b76f61d",
                "Adam",
                "Hairdresser senior",
                Lists.newArrayList("2b01e86f-f5ce-4415-9c9e-40340e201b9e", "a1fd9c09-c064-4c26-9d18-6151a369eeec"),
                new Calendar());

        //Empty List visits
        Day newDay = new Day(UUID.randomUUID().toString(), startOfWork ,endOfWork, new ArrayList<>());
        employee.getCalendar().getDaysAtWork().add(newDay);

        Mockito.when(employeeDao.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        List<Number> result = calendarService.getAvailableDatesOfVisit(employeeId, timeOfHairstyle);
        int lastVisit = endOfWork - ((30+15)*60);

        assertFalse(result.isEmpty());
        assertEquals(startOfWork, result.get(0).getNum());
        assertEquals(lastVisit, (result.get(result.size()-1)).getNum());
    }

    @Test
    public void getAvailableDatesOfVisits_inputFewVisits_returnListOfAvailableDate() {
        String employeeId = "3b7b4052-2603-4043-8f82-33a05b76f61d";
        int timeOfHairstyle = 35*60;

        Employee employee = new Employee("3b7b4052-2603-4043-8f82-33a05b76f61d",
                "Adam",
                "Hairdresser senior",
                Lists.newArrayList("2b01e86f-f5ce-4415-9c9e-40340e201b9e", "a1fd9c09-c064-4c26-9d18-6151a369eeec"),
                new Calendar());

        //Empty List visits
        Day newDay = new Day(UUID.randomUUID().toString(), 1608796800 ,1608832800, Lists.newArrayList(
                new Visit(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1608801300, 1608805800,""),
                new Visit(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1608809400, 1608811200,""),
                new Visit(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1608815700, 1608825600,"")
        ));
        employee.getCalendar().getDaysAtWork().add(newDay);

        List<Number> output = Lists.newArrayList(new Number(1608796800),
                new Number(1608797700), new Number(1608812100), new Number(1608826500), new Number(1608827400),
                new Number(1608828300), new Number(1608829200), new Number(1608830100));

        Mockito.when(employeeDao.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        List<Number> returns = calendarService.getAvailableDatesOfVisit(employeeId, timeOfHairstyle);
        assertFalse(returns.isEmpty());

        assertEquals(output, returns);
    }

    @Test
    public void getAvailableDatesOfVisits_inputFullVisits_returnEmptyList() {
        String employeeId = "3b7b4052-2603-4043-8f82-33a05b76f61d";
        int timeOfHairstyle = 70*60;

        Employee employee = new Employee("3b7b4052-2603-4043-8f82-33a05b76f61d",
                "Adam",
                "Hairdresser senior",
                Lists.newArrayList("2b01e86f-f5ce-4415-9c9e-40340e201b9e", "a1fd9c09-c064-4c26-9d18-6151a369eeec"),
                new Calendar());

        //Empty List visits
        Day newDay = new Day(UUID.randomUUID().toString(), 1608796800 ,1608825600, Lists.newArrayList(
                new Visit(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1608801300, 1608805800,""),
                new Visit(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1608809400, 1608811200,""),
                new Visit(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1608815700, 1608825600,"")
        ));
        employee.getCalendar().getDaysAtWork().add(newDay);

        Mockito.when(employeeDao.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        List<Number> returns = calendarService.getAvailableDatesOfVisit(employeeId, timeOfHairstyle);
        assertTrue(returns.isEmpty());
    }
}
