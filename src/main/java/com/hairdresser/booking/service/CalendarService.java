package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.exception.EmployeeNotFoundException;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Visit;
import com.hairdresser.booking.model.input.CalendarInput;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {

    @Autowired @Qualifier("fakeEmployee")
    private final EmployeeDao employeeDao;

    //It clears whole calendar's daysAtWork and builds it from scratch with validated data
    public Calendar editCalendarByEmployeeId(String employeeId, CalendarInput calendarInput) {
        Optional<Employee> employeeToEdit = employeeDao.getEmployeeById(employeeId);
        Optional<Calendar> calendar = employeeToEdit.map(Employee::getCalendar);

        List<Day> newDaysAtWork = new ArrayList<>();

        calendarInput.getDaysAtWork().forEach(day -> {
            int start = day.getStart();
            int end = day.getEnd();

            List<Visit> visits = new ArrayList<>();
            day.getVisits().forEach(visit -> {
                String client = visit.getClient();
                String hairstyle = visit.getHairstyle();
                int visitStart = visit.getStart();
                int visitEnd = visit.getEnd();
                String description = visit.getDescription();

                if (client != null && hairstyle != null && visitStart > 0 && visitEnd > 0)
                    visits.add(new Visit(null, client, hairstyle, visitStart, visitEnd, description));
            });

            if (start > 0 && end > 0)
                newDaysAtWork.add(new Day(null, start, end, visits));
        });

        calendar.get().setDaysAtWork(newDaysAtWork);
        employeeToEdit.get().setCalendar(calendar.get());
        Optional<Calendar> editedCalendar = Optional.ofNullable(employeeDao.editEmployeeById(employeeToEdit.get()).get().getCalendar());

        return editedCalendar.orElseThrow(EmployeeNotFoundException::new);
    }

    public int moveDaysFromDaysAtWorkToHistory(String employeeId) {
        Optional<Employee> employee = employeeDao.getEmployeeById(employeeId);
        int currentTime = (int) Instant.now().getEpochSecond();


        return 1;
    }

    public Calendar basicCalendarConfig30Days(Calendar calendar) {
        List<Day> daysAtWork = new ArrayList<>();
        int currentTime = (int) Instant.now().getEpochSecond();
        int hours24 = 24*60*60;
        int hours8 = 8*60*60;

        int time8am24_12_2020 = 1608796800;
        int rest = (currentTime-time8am24_12_2020) % (24*60*60);
        int today8am = currentTime-rest;
        System.out.println("Today's 8am: " + today8am);

        for (int i = 0; i < 30; i++) {
            int am8 = today8am + (i*hours24);
            Day day = new Day(null, am8, am8+hours8, new ArrayList<>());
            daysAtWork.add(day);
        }
        calendar.setDaysAtWork(daysAtWork);
        return calendar;
    }
}


//    int start = day.getStart();
//    int end = day.getEnd();
//    List<Visit> visits = day.getVisits();
//
//                if (start > 0) day.setStart(start);
//                        if (end > 0)  {
//                        System.out.println("TRUE");
//                        day.setEnd(end);
//                        }
//                        System.out.println(end);
//
//                        //Visits
//                        if (visits!= null) {
//                        visits.forEach(visit -> {
//                        UUID client = visit.getClient();
//                        UUID hairstyle = visit.getHairstyle();
//                        int visitStart = visit.getStart();
//                        int visitEnd = visit.getEnd();
//                        String description = visit.getDescription();
//
//
//                        if (client != null) visit.setClient(client);
//                        if (hairstyle != null) visit.setHairstyle(hairstyle);
//                        if (visitStart > 0) visit.setStart(visitStart);
//                        if (visitEnd > 0) visit.setEnd(visitEnd);
//                        if (description != null) visit.setDescription(description);
//
//                        });
//                        day.setVisits(visits);
//                        }