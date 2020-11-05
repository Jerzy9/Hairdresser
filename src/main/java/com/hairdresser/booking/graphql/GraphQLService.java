package com.hairdresser.booking.graphql;

import com.hairdresser.booking.graphql.datafetcher.AllHairstylesDataFetcher;
import com.hairdresser.booking.graphql.datafetcher.HairstyleByIdDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
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

    @PostConstruct
    private void loadSchema() throws IOException {
        //get the schema
        File schemaFile = resource.getFile();

        //parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                         .dataFetcher("getAllHairstyles", allHairstylesDataFetcher)
                         .dataFetcher("getHairstyleById", hairstyleByIdDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
