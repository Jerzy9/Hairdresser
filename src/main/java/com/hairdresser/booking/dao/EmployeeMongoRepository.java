package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeMongoRepository extends MongoRepository<Employee, String> {
}
