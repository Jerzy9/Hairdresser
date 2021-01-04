package com.hairdresser.booking.graphql;

import com.coxautodev.graphql.tools.SchemaParserBuilder;
import com.hairdresser.booking.graphql.resolver.Mutation;
import com.hairdresser.booking.graphql.resolver.Query;
import com.hairdresser.booking.service.*;
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
    private final HairstyleService hairstyleService;
    @Autowired
    private final EmployeeService employeeService;
    @Autowired
    private final CalendarService calendarService;
    @Autowired
    private final DayService dayService;
    @Autowired
    private final VisitService visitService;

    @PostConstruct
    private void loadSchema() {
        GraphQLSchema schema = buildSchema();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private GraphQLSchema buildSchema() {
        return new SchemaParserBuilder()
                .file("graphql/main.graphqls")
                .resolvers(
                        new Query(hairstyleService, employeeService, calendarService, dayService, visitService),
                        new Mutation(hairstyleService, employeeService, calendarService, dayService, visitService)
                )
                .build()
                .makeExecutableSchema();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
