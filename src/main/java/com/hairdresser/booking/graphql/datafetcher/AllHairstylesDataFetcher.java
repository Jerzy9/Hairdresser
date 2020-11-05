package com.hairdresser.booking.graphql.datafetcher;

import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.service.HairstyleService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllHairstylesDataFetcher implements DataFetcher<List<Hairstyle>> {

    @Autowired
    private HairstyleService hairstyleService;

    @Override
    public List<Hairstyle> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return hairstyleService.getAllHairstyles();
    }
}
