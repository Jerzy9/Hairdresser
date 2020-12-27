package com.hairdresser.booking.unit.dao;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.model.Day;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void insertDayAtWork_inputDayWithExistingEmployee_returnDay() {
        Day day = new Day(UUID.randomUUID(), 10, 20, new ArrayList<>());
        UUID employeeId = UUID.fromString("5c0d8d00-57ca-4968-8c4a-30a5028a8f9b");

        employeeDao.insertDayAtWork(employeeId, day);
        Optional<Day> dayFromDB = employeeDao.getDayAtWorkById(employeeId, day.getId());

        assertFalse(dayFromDB.isEmpty());
    }
}
