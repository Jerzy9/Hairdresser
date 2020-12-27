package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.HairstyleDao;
import com.hairdresser.booking.model.Day;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DayService {
    @Autowired @Qualifier("fakeHairstyle")
    private final HairstyleDao hairstyleDao;

//    public Day insertDay() {
//
//    }
}
