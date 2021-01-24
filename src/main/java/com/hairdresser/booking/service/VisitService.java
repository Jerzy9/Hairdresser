package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.EmployeeDao;
import com.hairdresser.booking.exception.DayNotFoundException;
import com.hairdresser.booking.exception.VisitNotFoundException;
import com.hairdresser.booking.model.Visit;
import com.hairdresser.booking.model.input.VisitInput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitService {

    @Autowired @Qualifier("MongoDBEmployee")
    private final EmployeeDao employeeDao;

    @PreAuthorize("hasAuthority('visit:add')")
    public Visit insertVisit(String employeeId, VisitInput visitInput) {
        Visit visit = new Visit(UUID.randomUUID().toString(), visitInput.getClient(), visitInput.getHairstyle(), visitInput.getStart(), visitInput.getEnd(), visitInput.getDescription());

        return employeeDao.insertVisit(employeeId, visit).orElseThrow(DayNotFoundException::new);
    }

    @PreAuthorize("hasAuthority('employee:read')")
    public Visit getVisitById(String employeeId, String dayId, String visitId) {
        return employeeDao.getVisitById(employeeId, dayId, visitId).orElseThrow(VisitNotFoundException::new);
    }

    @PreAuthorize("hasAuthority('employee:write')")
    public Visit deleteVisitById(String employeeId, String dayId, String visitId) {
        return employeeDao.deleteVisitById(employeeId, dayId, visitId).orElseThrow(VisitNotFoundException::new);
    }

    @PreAuthorize("hasAuthority('employee:read')")
    public List<Visit> getAllVisits(String employeeId, String dayId) {
        return employeeDao.getAllVisits(employeeId, dayId).orElseThrow(DayNotFoundException::new);
    }
}
