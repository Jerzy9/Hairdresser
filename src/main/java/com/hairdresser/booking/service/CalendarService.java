package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.exception.EmployeeNotFoundException;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Number;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarService {

    @Autowired @Qualifier("MongoDBEmployee")
    private final EmployeeDao employeeDao;

    //It clears whole calendar's daysAtWork and builds it from scratch with validated data
    public Calendar editCalendarByEmployeeId(String employeeId, CalendarInput calendarInput) {
        Optional<Employee> employeeToEdit = employeeDao.getEmployeeById(employeeId);
        Optional<Calendar> calendar = employeeToEdit.map(Employee::getCalendar);

        List<Day> newDaysAtWork = new ArrayList<>();

        calendarInput.getDaysAtWork().forEach(day -> {
            String id = UUID.randomUUID().toString();
            int start = day.getStart();
            int end = day.getEnd();

            List<Visit> visits = new ArrayList<>();
            day.getVisits().forEach(visit -> {
                String visitID = UUID.randomUUID().toString();
                String client = visit.getClient();
                String hairstyle = visit.getHairstyle();
                int visitStart = visit.getStart();
                int visitEnd = visit.getEnd();
                String description = visit.getDescription();

                if (client != null && hairstyle != null && visitStart > 0 && visitEnd > 0)
                    visits.add(new Visit(visitID, client, hairstyle, visitStart, visitEnd, description));
            });

            if (start > 0 && end > 0)
                newDaysAtWork.add(new Day(id, start, end, visits));
        });

        calendar.get().setDaysAtWork(newDaysAtWork);
        employeeToEdit.get().setCalendar(calendar.get());
        Optional<Calendar> editedCalendar = Optional.ofNullable(employeeDao.editEmployeeById(employeeToEdit.get()).get().getCalendar());

        return editedCalendar.orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Number> getAvailableDatesOfVisit(String employeeId, int time) {
        Optional<Employee> employee = employeeDao.getEmployeeById(employeeId);
        int breakTime = 15*60;
        List<Number> possibleVisitTimeTables = new ArrayList<>();

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
                            possibleVisitTimeTables.add(new Number(start));
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
                    possibleVisitTimeTables.add(new Number(i));
            }
        }
        return possibleVisitTimeTables;
    }

    public int moveDaysFromDaysAtWorkToHistory(String employeeId) {
        Optional<Employee> employee = employeeDao.getEmployeeById(employeeId);
        int currentTime = (int) Instant.now().getEpochSecond();


        return 1;
    }

    public Calendar basicCalendarConfig30Days(String employeeId) {
        Optional<Employee> employee = employeeDao.getEmployeeById(employeeId);
        Optional<Calendar> calendar = Optional.empty();
        System.out.println(employee);

        if (employee.isPresent()) {
            calendar = Optional.ofNullable(employee.get().getCalendar());

            List<Day> daysAtWork = calendar.get().getDaysAtWork();
            int currentTime = (int) Instant.now().getEpochSecond();
            int hours24 = 24 * 60 * 60;
            int hours8 = 8 * 60 * 60;

            int time8am24_12_2020 = 1608796800;
            int rest = (currentTime - time8am24_12_2020) % (24 * 60 * 60);
            int today8am = currentTime - rest;
            System.out.println("Today's 8am: " + today8am);

            for (int i = daysAtWork.size(); i < 30; i++) {

                int am8 = today8am + (i * hours24);
                Day day = new Day(null, am8, am8 + hours8, new ArrayList<>());
                daysAtWork.add(day);
            }
            calendar.get().setDaysAtWork(daysAtWork);
            employee.get().setCalendar(calendar.get());
            employeeDao.editEmployeeById(employee.get());
            System.out.println("IN");
        }
        return calendar.orElseThrow(EmployeeNotFoundException::new);
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