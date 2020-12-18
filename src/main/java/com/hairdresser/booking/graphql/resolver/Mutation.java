package com.hairdresser.booking.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.model.input.HairstyleInput;
import com.hairdresser.booking.service.HairstyleService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {
    private final HairstyleService hairstyleService;

    public Hairstyle insertHairstyle(HairstyleInput newHairstyleInput) {

        return hairstyleService.insertHairstyle(new HairstyleInput());
    }

    public Hairstyle deleteHairstyleById(UUID id) {
        return hairstyleService.deleteHairstyleById(id);
    }

    public Hairstyle editHairstyleById(UUID id, HairstyleInput newHairstyleInput) {
        return hairstyleService.editHairstyleById(id, newHairstyleInput);
    }
}
