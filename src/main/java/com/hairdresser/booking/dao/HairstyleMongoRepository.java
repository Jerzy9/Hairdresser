package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Hairstyle;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface HairstyleMongoRepository extends MongoRepository<Hairstyle, String> {

}
