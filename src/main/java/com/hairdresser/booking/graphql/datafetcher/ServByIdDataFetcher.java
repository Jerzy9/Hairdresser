package com.hairdresser.booking.graphql.datafetcher;

import com.hairdresser.booking.model.Serv;
import com.hairdresser.booking.service.ServService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ServByIdDataFetcher implements DataFetcher<Serv> {

    @Autowired
    private ServService servService;

    @Override
    public Serv get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        UUID id = UUID.fromString(dataFetchingEnvironment.getArgument("id"));
        return servService.getServById(id).orElse(
                new Serv(UUID.randomUUID(), "", "",0, 0)
        );
    }
}
