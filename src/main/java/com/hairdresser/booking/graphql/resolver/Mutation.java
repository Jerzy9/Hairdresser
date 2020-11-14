package com.hairdresser.booking.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.service.HairstyleService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {
    private final HairstyleService hairstyleService;

    public int insertHairstyle(Hairstyle newHairstyle) {
        return hairstyleService.insertHairstyle(newHairstyle);
    }

    public int deleteHairstyleById(UUID id) {
        return hairstyleService.deleteHairstyleById(id);
    }
}
