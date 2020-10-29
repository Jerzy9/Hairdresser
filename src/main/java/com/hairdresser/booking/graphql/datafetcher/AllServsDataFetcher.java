package com.hairdresser.booking.graphql.datafetcher;

import com.hairdresser.booking.model.Serv;
import com.hairdresser.booking.service.ServService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AllServsDataFetcher implements DataFetcher<List<Serv>> {

    @Autowired
    private ServService servService;

    @Override
    public List<Serv> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return servService.getAllServs();
    }
}
