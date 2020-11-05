package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Hairstyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HairstyleDao {

    int insertHairstyle(Hairstyle newHairstyle);

    Optional<Hairstyle> getHairstyleById(UUID id);

    List<Hairstyle> getAllHairstyles();

    int deleteHairstyleById(UUID id);
}
