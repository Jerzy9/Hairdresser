package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.model.input.HairstyleInput;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HairstyleDao {

    Hairstyle insertHairstyle(HairstyleInput hairstyleInput);

    Optional<Hairstyle> getHairstyleById(UUID id);

    List<Hairstyle> getAllHairstyles();

    Optional<Hairstyle> deleteHairstyleById(UUID id);

    Optional<Hairstyle> editHairstyleById(Hairstyle hairstyle);
}
