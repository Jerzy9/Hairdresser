package com.hairdresser.booking.unit.dao;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.model.Day;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void insertDayAtWork_inputDayWithExistingEmployee_returnDay() {
        Day day = new Day(null, 10, 20, new ArrayList<>());
        String employeeId = "5fed0acfb722653adfedb2f3";

//        int sizeBefore = employeeDao.getEmployeeById(employeeId).get().getCalendar().getDaysAtWork().size();
//        Optional<Day> addedDay = employeeDao.insertDayAtWork(employeeId, day);


//        assertEquals(sizeBefore+1, employeeDao.getEmployeeById(employeeId).get().getCalendar().getDaysAtWork().size());
    }

    @Test
    public void getDayAtWorkById_inputIds_returnDay() {
        String employeeId = "5fed0acfb722653adfedb2f3";
        String dayId = "5fed0acfb722673adfedb2e9";

//        Day day = employeeDao.getDayAtWorkById(employeeId, dayId).get();
//        assertEquals(dayId, day.getId());
    }
}
