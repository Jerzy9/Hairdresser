package com.hairdresser.booking.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.service.HairstyleService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {
    private final HairstyleService hairstyleService;

    public Hairstyle insertHairstyle(Hairstyle newHairstyle) {
        return hairstyleService.insertHairstyle(newHairstyle);
    }

    public Hairstyle deleteHairstyleById(UUID id) {
        return hairstyleService.deleteHairstyleById(id);
    }

    public Hairstyle editHairstyleById(UUID id, Hairstyle newHairstyle) {
        return hairstyleService.editHairstyleById(id, newHairstyle);
    }
}
