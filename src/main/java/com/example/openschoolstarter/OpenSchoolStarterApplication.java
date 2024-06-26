package com.example.openschoolstarter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class OpenSchoolStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenSchoolStarterApplication.class, args);
    }

}
