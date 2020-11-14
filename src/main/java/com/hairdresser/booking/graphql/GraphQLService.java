package com.hairdresser.booking.graphql;

import com.coxautodev.graphql.tools.SchemaParserBuilder;
import com.hairdresser.booking.graphql.resolver.Mutation;
import com.hairdresser.booking.graphql.resolver.Query;
import com.hairdresser.booking.service.HairstyleService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class GraphQLService {

    private GraphQL graphQL;
    @Autowired
    private HairstyleService hairstyleService;

    @PostConstruct
    private void loadSchema(){
        GraphQLSchema schema = buildSchema();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private GraphQLSchema buildSchema() {
        return new SchemaParserBuilder()
                .file("graphql/main.graphqls")
                .resolvers(
                        new Query(hairstyleService),
                        new Mutation(hairstyleService)
                )
                .build()
                .makeExecutableSchema();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}
