package com.hairdresser.booking.unit.service;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.service.CalendarService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import static org.junit.Assert.*;

@SpringBootTest
public class CalendarServiceTest {

    @Autowired
    private CalendarService calendarService;

    @MockBean
    private EmployeeDao employeeDao;

    @Test
    public void basicConfig30Days_inputEmptyCalendar_returnCalendarWith30Days() {
        Calendar calendar = new Calendar();

        Calendar calendarAfterConfig = calendarService.basicCalendarConfig30Days(calendar);
        assertEquals(30, calendarAfterConfig.getDaysAtWork().size());
        assertEquals(24*60*60, calendarAfterConfig.getDaysAtWork().get(1).getStart()-calendarAfterConfig.getDaysAtWork().get(0).getStart());
    }

}
