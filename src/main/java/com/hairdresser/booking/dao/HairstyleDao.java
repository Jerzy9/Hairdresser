package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Hairstyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HairstyleDao {

    Hairstyle insertHairstyle(UUID id, Hairstyle newHairstyle);

    default Hairstyle insertHairstyle(Hairstyle newHairstyle) {
        UUID randomId = UUID.randomUUID();
        return insertHairstyle(randomId, newHairstyle);
    }

    Optional<Hairstyle> getHairstyleById(UUID id);

    List<Hairstyle> getAllHairstyles();

    Optional<Hairstyle> deleteHairstyleById(UUID id);

    Optional<Hairstyle> editHairstyleById(UUID id, Hairstyle newHairstyle);
}
