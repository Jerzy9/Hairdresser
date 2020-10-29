package com.hairdresser.booking.api;


import com.hairdresser.booking.graphql.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HairServRestController {

    @Autowired
    private GraphQLService graphQLService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    @PostMapping("/graphql")
    public ResponseEntity<Object> getAllServs(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<Object>(execute, HttpStatus.OK);
    }
}