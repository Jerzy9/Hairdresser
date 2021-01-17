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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    @Scheduled(cron = "0 0 6 * * ?") @PostConstruct
    public void updateDaysAtWorkForEveryEmployee() {
        employeeDao.getAllEmployees().forEach(emp -> {
            moveDaysFromDaysAtWorkToHistory(emp.getId());
        });
    }

    //It clears whole calendar's daysAtWork and builds it from scratch with validated data
    @PreAuthorize("hasAuthority('employee:write')")
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
        int currentTime = (int) Instant.now().getEpochSecond();

        //For every day in work, print possible dates for new visit
        if(employee.isPresent()) {
            for (int i = 0; i < 7; i++) {
                Day day = employee.get().getCalendar().getDaysAtWork().get(i);
                int start = day.getStart();
                int endOfTheDay = day.getEnd();
                int sum = start + time + breakTime;

                //First check, if there any are already booked visits for that day,
                //If there are, check each break between these visits and try to fit there a new visit
                //When the break is too short, go to the next one
                if(!day.getVisits().isEmpty()) {
                    int j = 0;
                    int length = day.getVisits().size();
                    while (j < length && sum < endOfTheDay) {

                        if (sum < day.getVisits().get(j).getStart() && sum > currentTime) {
                            possibleVisitTimeTables.add(new Number(start));
                            start +=breakTime;
                        } else {
                            start = day.getVisits().get(j).getEnd() + breakTime;
                            j++;
                        }
                        sum = start + time + breakTime;
                    }
                }
                //Add available dates, if there was no visit at all on that day or
                //when there was a time between last visit and end of the work
                for (int k = start; k < endOfTheDay - time; k+=breakTime)
                    if(k > currentTime)
                        possibleVisitTimeTables.add(new Number(k));
            }
        }
        return possibleVisitTimeTables;
    }

    //This methods checks if there are any days in daysAtWork list, which are out of date
    public void moveDaysFromDaysAtWorkToHistory(String employeeId) {
        Optional<Employee> employee = employeeDao.getEmployeeById(employeeId);

        //Calculate today's morning
        int currentTime = (int) Instant.now().getEpochSecond();
        int time5am02_01_2021 = 1609563600;
        int rest = (currentTime - time5am02_01_2021) % (24 * 60 * 60);
        int today5am = currentTime - rest;

        //Remove days witch are older than today's morning and add the to history
        //DaysAtWork List is always sorted by start time, so break loop if it will start of work day will be higher than today's morning
        if (employee.isPresent()) {

            //Copy of DaysAtWork List
            List<Day> daysAtWork = new ArrayList<>(employee.get().getCalendar().getDaysAtWork());
            int size = daysAtWork.size();

            for (int i = 0; i < size; i ++) {
                Day currentDay = employee.get().getCalendar().getDaysAtWork().get(i);
                if (currentDay.getStart() > today5am)
                   break;

                //Add day to history and remove from DaysAtWork
                employee.get().getCalendar().getHistoryOfWork().add(currentDay);
                daysAtWork.remove(currentDay);
            }
            //Update Days at work
            employee.get().getCalendar().setDaysAtWork(daysAtWork);
            employeeDao.editEmployeeById(employee.get());
        }
    }

    //It configures employee's calendar with basic 30 days
    //It won't overwrite days witch are already on the list
    @PreAuthorize("hasAuthority('employee:write')")
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

            for (int i = daysAtWork.size(); i < 30; i++) {

                int am8 = today8am + (i * hours24);
                Day day = new Day(UUID.randomUUID().toString(), am8, am8 + hours8, new ArrayList<>());
                daysAtWork.add(day);
            }
            calendar.get().setDaysAtWork(daysAtWork);
            employee.get().setCalendar(calendar.get());
            employeeDao.editEmployeeById(employee.get());
        }
        return calendar.orElseThrow(EmployeeNotFoundException::new);
    }
}