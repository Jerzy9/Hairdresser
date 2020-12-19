package com.hairdresser.booking.dao;

import com.google.common.collect.Lists;
import com.hairdresser.booking.model.Calendar;
import com.hairdresser.booking.model.Employee;
import com.hairdresser.booking.model.input.EmployeeInput;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeEmployee")
public class FakeEmployeeDaoAccessService implements EmployeeDao {

    private List<Employee> employees = Lists.newArrayList(
            new Employee(UUID.fromString("3b7b4052-2603-4043-8f82-33a05b76f61d"),
                    "Adam",
                    "Hairdresser senior",
                    Lists.newArrayList(UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e"), UUID.fromString("a1fd9c09-c064-4c26-9d18-6151a369eeec")),
                    new Calendar(UUID.randomUUID())),
            new Employee(UUID.fromString("5c0d8d00-57ca-4968-8c4a-30a5028a8f9b"),
                    "Monika",
                    "Hairdresser junior",
                    Lists.newArrayList(UUID.fromString("a1fd9c09-c064-4c26-9d18-6151a369eeec")),
                    new Calendar(UUID.randomUUID()))
            );

    @Override
    public Employee insertEmployee(EmployeeInput employeeInput) {
        UUID id = UUID.randomUUID();
        Employee newEmp = new Employee(id, employeeInput.getName(), employeeInput.getDescription(), employeeInput.getHairstyles(), employeeInput.getCalendar());
        employees.add(newEmp);
        return newEmp;
    }

    @Override
    public Optional<Employee> getEmployeeById(UUID id) {
        return employees.stream().filter(employee -> employee.getId().equals(id)).findFirst();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Optional<Employee> deleteEmployeeById(UUID id) {
        Optional<Employee> employeeToDelete = getEmployeeById(id);
        employeeToDelete.ifPresent(emp-> employees.remove(emp));

        return employeeToDelete;
    }

    @Override
    //Edit only variables which are not nulls, otherwise do nothing
    public Optional<Employee> editEmployeeById(Employee employee) {
        Optional<Employee> hairstyleToEdit = getEmployeeById(employee.getId());
        hairstyleToEdit.ifPresent(emp-> emp = employee);

        return hairstyleToEdit;
    }
}
