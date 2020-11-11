package com.hairdresser.booking.graphql.datafetcher;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.service.HairstyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {
//    @Autowired        Patryk tego nie ma
    private final HairstyleService hairstyleService;

    public Hairstyle getHairstyleById(UUID id) {
        return hairstyleService.getHairstyleById(id);
    }

    public List<Hairstyle> getAllHairstyles() {
       return hairstyleService.getAllHairstyles();
    }
}
