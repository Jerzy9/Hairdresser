package com.hairdresser.booking.api;

import com.hairdresser.booking.graphql.GraphQLService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HairstyleRestController {

    @Autowired
    private GraphQLService graphQLService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping()
    public String mainPage() {
        return "MainPage - everyone has access";
    }

    @PostMapping(value = "/graphql")
    @ResponseBody
    public ResponseEntity<Object> graphql(@RequestBody Map<String, Object> query) {
        //Parsing from JSON
        ExecutionResult execute = graphQLService.getGraphQL().execute(ExecutionInput.newExecutionInput()
                .query((String) query.get("query"))
                .variables((Map<String, Object>) query.get("variables"))
                .build());

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}