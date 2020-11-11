package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Hairstyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HairstyleDao {

    int insertHairstyle(UUID id, Hairstyle newHairstyle);

    default int insertHairstyle(Hairstyle newHairstyle) {
        UUID randomId = UUID.randomUUID();
        return insertHairstyle(randomId, newHairstyle);
    }

    Optional<Hairstyle> getHairstyleById(UUID id);

    List<Hairstyle> getAllHairstyles();

    int deleteHairstyleById(UUID id);
}
