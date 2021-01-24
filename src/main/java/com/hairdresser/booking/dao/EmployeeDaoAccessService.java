package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository("MongoDBEmployee")
public class EmployeeDaoAccessService implements EmployeeDao {

    @Autowired
    private EmployeeMongoRepository employeeMongoRepository;

    @Override
    public Employee insertEmployee(Employee employee) {
        return employeeMongoRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(String id) {
        return employeeMongoRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeMongoRepository.findAll();
    }

    @Override
    public Optional<Employee> deleteEmployeeById(String id) {
        Optional<Employee> employeeToEdit = getEmployeeById(id);

        if (employeeToEdit.isPresent())
            employeeMongoRepository.deleteById(id);
        return employeeToEdit;
    }

    @Override
    public Optional<Employee> editEmployeeById(Employee employee) {
        Optional<Employee> employeeToEdit = getEmployeeById(employee.getId());

        if (employeeToEdit.isPresent())
            employeeToEdit = Optional.of(employeeMongoRepository.save(employee));
        return employeeToEdit;
    }

    @Override
    public Optional<Day> insertDayAtWork(String employeeId, Day day) {
        Optional<Employee> employee = getEmployeeById(employeeId);
        Optional<Day> newDay = Optional.empty();

        if(employee.isPresent()) {
            employee.get().getCalendar().getDaysAtWork().add(day);
            employeeMongoRepository.save(employee.get());
            newDay = Optional.ofNullable(day);
        }

        return newDay;
    }

    @Override
    public Optional<Day> getDayAtWorkById(String employeeId, String dayId) {
        Optional<Employee> employee = getEmployeeById(employeeId);
        Optional<Day> day = Optional.empty();

        if (employee.isPresent())
            day = employee.get().getCalendar().getDaysAtWork().stream().filter(d -> d.getId().equals(dayId)).findFirst();

        return day;
    }

    @Override
    public Optional<Day> deleteDayById(String employeeId, String dayId) {
        Optional<Employee> employee = getEmployeeById(employeeId);
        Optional<Day> day = getDayAtWorkById(employeeId, dayId);

        if (day.isPresent()) {
            employee.get().getCalendar().getDaysAtWork().remove(day.get());
            employeeMongoRepository.save(employee.get());
        }
        return day;
    }

    @Override
    public Optional<Visit> insertVisit(String employeeId, Visit visit) {
        Optional<Employee> employee = getEmployeeById(employeeId);
        Optional<Day> day = Optional.empty();
        Optional<Visit> newVisit = Optional.empty();

        if (employee.isPresent()) {
             day = employee.get().getCalendar().getDaysAtWork().stream()
                    .filter(d -> d.getStart() <= visit.getStart() && d.getEnd() >= visit.getEnd())
                    .findFirst();

             if (day.isPresent()) {
                 newVisit = Optional.of(visit);
                 day.get().getVisits().add(visit);
                 employeeMongoRepository.save(employee.get());
             }
        }

        return newVisit;
    }

    @Override
    public Optional<Visit> getVisitById(String employeeId, String dayId, String visitId) {
        Optional<Day> day = getDayAtWorkById(employeeId, dayId);
        Optional<Visit> newVisit = Optional.empty();

        if(day.isPresent())
            newVisit = day.get().getVisits().stream().filter(visit -> visit.getId().equals(visitId)).findFirst();

        return newVisit;
    }

    @Override
    public Optional<List<Visit>> getAllVisits(String employeeId, String dayId) {
        Optional<Day> day = getDayAtWorkById(employeeId, dayId);
        Optional<List<Visit>> visits = Optional.empty();

        if (day.isPresent())
            visits = Optional.ofNullable(day.get().getVisits());
        return visits;
    }

    @Override
    public Optional<Visit> deleteVisitById(String employeeId, String dayId, String visitId) {
        Optional<Employee> employee = getEmployeeById(employeeId);
        Optional<Visit> visitToDelete = getVisitById(employeeId, dayId, visitId);

        visitToDelete.flatMap(visit -> employee).ifPresent(value -> value.getCalendar().getDaysAtWork().forEach(d -> {
            if (d.getId().equals(dayId)) {
                d.getVisits().remove(visitToDelete.get());
                employeeMongoRepository.save(employee.get());
            }
        }));
        return visitToDelete;
    }
}
