package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.HairstyleDao;
import com.hairdresser.booking.exception.HairstyleNotFoundException;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.model.input.HairstyleInput;
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
    @Autowired @Qualifier("MongoDBHairstyle")
    private final HairstyleDao hairstyleDao;

    public Hairstyle insertHairstyle(HairstyleInput hairstyleInput) {
        return hairstyleDao.insertHairstyle(new Hairstyle(null, hairstyleInput.getName(), hairstyleInput.getDescription(), hairstyleInput.getTime(), hairstyleInput.getPrice()));
    }

    public Hairstyle getHairstyleById(String id) {
        Optional<Hairstyle> optionalHairstyle = hairstyleDao.getHairstyleById(id);
        return optionalHairstyle.orElseThrow(HairstyleNotFoundException::new);
    }

    public List<Hairstyle> getAllHairstyles() {
        return hairstyleDao.getAllHairstyles();
    }

    public Hairstyle deleteHairstyleById(String id) {
        Optional<Hairstyle> optionalHairstyle = hairstyleDao.deleteHairstyleById(id);
        return optionalHairstyle.orElseThrow(HairstyleNotFoundException::new);
    }

    //Edit only variables which are't nulls, otherwise do nothing
    public Hairstyle editHairstyleById(String id, HairstyleInput hairstyleInput) {
        //Get hairstyle which will be edited
        Optional<Hairstyle> hairstyleToEdit = hairstyleDao.getHairstyleById(id);

        //Store new variables
        String newName = hairstyleInput.getName();
        String newDescription = hairstyleInput.getDescription();
        float newPrice = hairstyleInput.getPrice();
        int newTime = hairstyleInput.getTime();

        //Replace variables given by user
        hairstyleToEdit.ifPresent(hs-> {
            if(newName != null) hs.setName(newName);
            if(newDescription != null) hs.setDescription(newDescription);
            if(newPrice > 0) hs.setPrice(newPrice);
            if(newTime > 0) hs.setTime(newTime);
        });

        //Replace old object with the edited one in database
        Optional<Hairstyle> hairstyleEdited = hairstyleDao.editHairstyleById(hairstyleToEdit.get());
        return hairstyleEdited.orElseThrow(HairstyleNotFoundException::new);
    }
}
