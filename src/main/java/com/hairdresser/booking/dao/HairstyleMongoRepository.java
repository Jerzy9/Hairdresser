package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Hairstyle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HairstyleMongoRepository extends MongoRepository<Hairstyle, String> {
    Optional<Hairstyle> deleteHairstyleById(String id);
}
