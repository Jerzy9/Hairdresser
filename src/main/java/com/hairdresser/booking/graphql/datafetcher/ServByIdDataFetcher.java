package com.hairdresser.booking.graphql.datafetcher;

import com.hairdresser.booking.model.Serv;
import com.hairdresser.booking.service.ServService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ServByIdDataFetcher implements DataFetcher<Serv> {
    @Autowired
    private final ServService servService;

    @Override
    public Serv get(DataFetchingEnvironment dataFetchingEnvironment) {
        UUID id = UUID.fromString(dataFetchingEnvironment.getArgument("id"));
        return servService.getServById(id);
    }
}
