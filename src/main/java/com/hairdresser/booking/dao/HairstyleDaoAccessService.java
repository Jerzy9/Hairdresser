package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Hairstyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository("MongoDBHairstyle")
public class HairstyleDaoAccessService implements HairstyleDao {

    @Autowired
    private HairstyleMongoRepository hairstyleMongoRepository;

    @Override
    public Hairstyle insertHairstyle(Hairstyle hairstyle) {
        return hairstyleMongoRepository.save(hairstyle);
    }

    @Override
    public Optional<Hairstyle> getHairstyleById(String id) {
            return hairstyleMongoRepository.findById(id);
    }

    @Override
    public List<Hairstyle> getAllHairstyles() {
        return hairstyleMongoRepository.findAll();
    }

    @Override
    public Optional<Hairstyle> deleteHairstyleById(String id) {
        Optional<Hairstyle> hairstyleToEdit = getHairstyleById(id);

        if (hairstyleToEdit.isPresent())
            hairstyleMongoRepository.deleteById(id);
        return hairstyleToEdit;
    }

    @Override
    public Optional<Hairstyle> editHairstyleById(Hairstyle hairstyle) {
        Optional<Hairstyle> hairstyleToEdit = getHairstyleById(hairstyle.getId());

        if (hairstyleToEdit.isPresent())
            hairstyleToEdit = Optional.of(hairstyleMongoRepository.save(hairstyle));
        return hairstyleToEdit;
    }
}
