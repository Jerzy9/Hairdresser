package com.hairdresser.booking.graphql.datafetcher;

import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.service.HairstyleService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HairstyleByIdDataFetcher implements DataFetcher<Hairstyle> {
    @Autowired
    private final HairstyleService hairstyleService;

    @Override
    public Hairstyle get(DataFetchingEnvironment dataFetchingEnvironment) {
        UUID id = UUID.fromString(dataFetchingEnvironment.getArgument("id"));
        return hairstyleService.getHairstyleById(id);
    }
}
