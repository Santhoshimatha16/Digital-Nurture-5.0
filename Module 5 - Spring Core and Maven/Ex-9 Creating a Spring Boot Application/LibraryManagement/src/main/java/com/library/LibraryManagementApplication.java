package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for Exercise 9 – Creating a Spring Boot Application.
 *
 * @SpringBootApplication combines:
 *   @Configuration        – marks this as a configuration class
 *   @EnableAutoConfiguration – enables Spring Boot's auto-config
 *   @ComponentScan        – scans com.library and sub-packages for beans
 */
@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }
}
