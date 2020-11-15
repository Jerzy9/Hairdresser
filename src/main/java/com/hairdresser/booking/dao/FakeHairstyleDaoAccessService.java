package com.hairdresser.booking.dao;

import com.google.common.collect.Lists;
import com.hairdresser.booking.model.Hairstyle;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fake")
public class FakeHairstyleDaoAccessService implements HairstyleDao {

    private List<Hairstyle> hairstyles = Lists.newArrayList(
            new Hairstyle(UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e"), "first", "first desc", 35, 20),
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
    public Hairstyle insertHairstyle(UUID id, Hairstyle newHairstyle) {
        Hairstyle newHS = new Hairstyle(id, newHairstyle.getName(), newHairstyle.getDescription(), newHairstyle.getTime(), newHairstyle.getPrice());
        hairstyles.add(newHS);
        return newHS;
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
    public Optional<Hairstyle> deleteHairstyleById(UUID id) {
        Optional<Hairstyle> hairstyleToDelete = getHairstyleById(id);
        hairstyleToDelete.ifPresent(hs-> hairstyles.remove(hs));

        return hairstyleToDelete;
    }

    @Override
    //Edit only variables which are not nulls, otherwise do nothing
    public Optional<Hairstyle> editHairstyleById(UUID id, Hairstyle newHairstyle) {
        Optional<Hairstyle> hairstyleToEdit = getHairstyleById(id);
        hairstyleToEdit.ifPresent(hs-> hs = newHairstyle);

        return hairstyleToEdit;
    }
}
