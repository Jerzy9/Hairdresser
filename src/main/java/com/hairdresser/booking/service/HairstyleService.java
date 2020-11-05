package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.HairstyleDao;
import com.hairdresser.booking.exception.HairstyleNotFoundException;
import com.hairdresser.booking.model.Hairstyle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HairstyleService {
    @Autowired @Qualifier("fake")
    private final HairstyleDao hairstyleDao;

    public int insertHairstyle(Hairstyle newHairstyle) {
        return hairstyleDao.insertHairstyle(newHairstyle);
    }

    public Hairstyle getHairstyleById(UUID id) {
        Optional<Hairstyle> optionalHairstyle = hairstyleDao.getHairstyleById(id);
        return optionalHairstyle.orElseThrow(HairstyleNotFoundException::new);
    }

    public List<Hairstyle> getAllHairstyles() {
        return hairstyleDao.getAllHairstyles();
    }

    public int deleteHairstyleById(UUID id) {
        return hairstyleDao.deleteHairstyleById(id);
    }
}
