package com.hairdresser.booking.graphql;

import com.hairdresser.booking.graphql.datafetcher.AllServsDataFetcher;
import com.hairdresser.booking.graphql.datafetcher.ServByIdDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:main.graphqls")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllServsDataFetcher allServsDataFetcher;
    @Autowired
    private ServByIdDataFetcher servByIdDataFetcher;

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
                         .dataFetcher("getAllServs", allServsDataFetcher)
                         .dataFetcher("getServById", servByIdDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
