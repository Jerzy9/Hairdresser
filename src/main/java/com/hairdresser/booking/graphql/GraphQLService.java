package com.hairdresser.booking.graphql;

import com.coxautodev.graphql.tools.SchemaParserBuilder;
import com.hairdresser.booking.graphql.datafetcher.AllHairstylesDataFetcher;
import com.hairdresser.booking.graphql.datafetcher.HairstyleByIdDataFetcher;
import com.hairdresser.booking.graphql.datafetcher.Query;
import com.hairdresser.booking.service.HairstyleService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GraphQLService {
    @Autowired
    private final AllHairstylesDataFetcher allHairstylesDataFetcher;
    @Autowired
    private final HairstyleByIdDataFetcher hairstyleByIdDataFetcher;

    //move value loading to a separate configuration class if this keeps growing
    @Value("classpath:main.graphqls")
    private Resource resource;

    private GraphQL graphQL;
    @Autowired
    private HairstyleService hairstyleService;

    @PostConstruct
    private void loadSchema() throws IOException {
        //get the schema
//        File schemaFile = resource.getFile();

        //parse schema
//        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
//        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema schema = buildSchema();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private GraphQLSchema buildSchema() {
        return new SchemaParserBuilder()
                .file("graphql/main.graphqls")
                .resolvers(
                        new Query(hairstyleService)
                )
                .build()
                .makeExecutableSchema();
    }

//    private RuntimeWiring buildRuntimeWiring() {
//        return RuntimeWiring.newRuntimeWiring()
//                .type("Query", typeWiring -> typeWiring
//                         .dataFetcher("getAllHairstyles", allHairstylesDataFetcher)
//                         .dataFetcher("getHairstyleById", hairstyleByIdDataFetcher))
//                .build();
//    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
