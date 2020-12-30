package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Hairstyle;

import java.util.List;
import java.util.Optional;

public interface HairstyleDao {

    Hairstyle insertHairstyle(Hairstyle hairstyle);

    Optional<Hairstyle> getHairstyleById(String id);

    List<Hairstyle> getAllHairstyles();

    Optional<Hairstyle> deleteHairstyleById(String id);

    Optional<Hairstyle> editHairstyleById(Hairstyle hairstyle);
}
