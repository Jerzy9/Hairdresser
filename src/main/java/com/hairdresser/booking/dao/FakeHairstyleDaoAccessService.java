package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Hairstyle;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fake")
public class FakeHairstyleDaoAccessService implements HairstyleDao {

    private List<Hairstyle> hairstyles = Arrays.asList(
                new Hairstyle(UUID.randomUUID(), "first", "first desc", 35, 20),
                new Hairstyle(UUID.randomUUID(), "second", "second desc", 55, 60),
                new Hairstyle(UUID.randomUUID(), "third", "third desc", 150, 45),
                new Hairstyle(UUID.randomUUID(), "forth", "forth desc", 180, 20),
                new Hairstyle(UUID.randomUUID(), "fifth", "fifth desc", 35, 60),
                new Hairstyle(UUID.randomUUID(), "sixth", "sixth desc", 70, 45),
                new Hairstyle(UUID.randomUUID(), "seventh", "seventh desc", 20, 20),
                new Hairstyle(UUID.randomUUID(), "eighth", "eighth desc", 50, 60),
                new Hairstyle(UUID.randomUUID(), "ninth", "ninth desc", 15, 45),
                new Hairstyle(UUID.randomUUID(), "tenth", "tenth desc", 40, 45)
            );

    @Override
    public int insertHairstyle(Hairstyle newHairstyle) {
        hairstyles.add(newHairstyle);
        return 1;
    }

    @Override
    public Optional<Hairstyle> getHairstyleById(UUID id) {
        return hairstyles.stream().filter(hairstyle -> hairstyle.getId().equals(id)).findFirst();
    }

    @Override
    public List<Hairstyle> getAllHairstyles() {
        return hairstyles;
    }

    @Override
    public int deleteHairstyleById(UUID id) {
        Optional<Hairstyle> hairstyleToDelete = getHairstyleById(id);

        if(hairstyleToDelete.isEmpty())
            return 0;

        hairstyles.remove(hairstyleToDelete.get());
        return 1;
    }
}
