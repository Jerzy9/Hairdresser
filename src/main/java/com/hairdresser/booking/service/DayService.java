package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.dao.HairstyleDao;
import com.hairdresser.booking.exception.DayNotFoundException;
import com.hairdresser.booking.exception.EmployeeNotFoundException;
import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.input.DayInput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DayService {
    @Autowired @Qualifier("MongoDBEmployee")
    private final EmployeeDao employeeDao;

    public Day insertDay(String employeeId, DayInput dayInput) {
        Day day = new Day(UUID.randomUUID().toString(), dayInput.getStart(), dayInput.getEnd(), dayInput.getVisits());

        return employeeDao.insertDayAtWork(employeeId, day).orElseThrow(EmployeeNotFoundException::new);
    }

    public Day getDayById(String employeeId, String dayId) {
        return employeeDao.getDayAtWorkById(employeeId, dayId).orElseThrow(DayNotFoundException::new);
    }

    public Day deleteDayById(String employeeId, String dayId) {
        return employeeDao.deleteDayById(employeeId, dayId).orElseThrow(DayNotFoundException::new);
    }
}
