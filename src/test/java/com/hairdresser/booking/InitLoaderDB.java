package com.hairdresser.booking;


import com.google.common.collect.Lists;
import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.dao.HairstyleDao;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.model.Visit;
import com.hairdresser.booking.model.input.HairstyleInput;
import com.hairdresser.booking.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class InitLoaderDB {

    @Autowired
    private HairstyleDao hairstyleDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private CalendarService calendarService;

//    @Test
    public  void create20Hairstyles() {
        List<HairstyleInput> names = Lists.newArrayList(
                new HairstyleInput("Farbowanie", "Farbowanie włosów o dowolnej długości na dowolny kolor", 180*60, 220),
                new HairstyleInput("Strzyżenie męskie", "Klasyczne", 25*60, 40),
                new HairstyleInput("Fryzura weselna", "Fryzura dla panny młodej", 160*60, 190),
                new HairstyleInput("Strzyżenie", "Farbowanie włosów o dowolnej długości na dowolny kolor", 120*60, 170),
                new HairstyleInput("Podcięcie końcówek", "Szybkie podcięcie końcówek", 20*60, 40),
                new HairstyleInput("Koloryzacja", "Farbowanie+Pielęgnacja", 180*60, 350),
                new HairstyleInput("Odmładzanie", "Kuracja odmładzająca 40+", 220*60, 450),
                new HairstyleInput("Masaż skóry głowy", "Masaż z olejkami", 45*60, 60),
                new HairstyleInput("Strzyżenie damskie wykonane maszynką", "Strzyżenie damskie wykonane tylko maszynką", 30*60, 50),
                new HairstyleInput("Strzyżenie grzywki", "Szybkie podcięcie grzywki", 15*60, 15),
                new HairstyleInput("Strzyżenie maszynką męskie", "Standardowe podcięcie", 30*60, 35),
                new HairstyleInput("Strzyżenie dzieci do 7 lat", "Delikatne podcięcie dziecięcych włosów", 25*60, 30),
                new HairstyleInput("Modelowanie", "Pełen serwis", 300*60, 660),
                new HairstyleInput("Rozjaśnianie", "Rozjaśnianie pod koloryzjace", 90*60, 120),
                new HairstyleInput("Color Balancing", "Regulara koloru, Ombre, Przejścia", 170*60, 320),
                new HairstyleInput("Dekoloryzacja", "Powrót do naturalnego koloru", 150*60, 310),
                new HairstyleInput("Tonowanie męskie", "Narost brody", 45*60, 50)
        );

        for (HairstyleInput hairstyle:names) {
            hairstyleDao.insertHairstyle(new Hairstyle(null, hairstyle.getName(), hairstyle.getDescription(), hairstyle.getTime(), hairstyle.getPrice()));
        }
    }

//    @Test
    public void fillEmployeesAvailableHairstyles() {
        List<Employee> employees = employeeDao.getAllEmployees();
        List<Hairstyle> hairstyles = hairstyleDao.getAllHairstyles();
        int step = 1;

        //First employee has all hairstyle, every next employee has half hairstyles of the previous one
        for (Employee employee: employees) {
            List<String> employeesHairstyles = new ArrayList<>();
            for (int i = 0; i < hairstyles.size() ; i += step) {
                employeesHairstyles.add(hairstyles.get(i).getId());
            }

            step++;
            employee.setHairstyles(employeesHairstyles);
            employeeDao.editEmployeeById(employee);
        }
    }

//    @Test
    public void insertVisits() {
        Visit visit;
        List<Hairstyle> hairstyles = hairstyleDao.getAllHairstyles();
        Random r = new Random();
        int currentTime = (int) Instant.now().getEpochSecond();
        int startVisit;

        for (Employee employee: employeeDao.getAllEmployees()) {
            startVisit = currentTime;
            for (int i = 0; i < 8; i++) {
                String id = UUID.randomUUID().toString();
                String idClient = UUID.randomUUID().toString();
                int randomInt = r.nextInt(hairstyles.size());

                String idHairstyles = hairstyles.get(randomInt).getId();
                int hairstyleTime = hairstyles.get(randomInt).getTime();
                 startVisit += (i * 3*60*60 );

                visit = new Visit(id, idClient, idHairstyles, startVisit, startVisit + hairstyleTime, "-");
                employeeDao.insertVisit(employee.getId(), visit);
            }
        }
    }
}
