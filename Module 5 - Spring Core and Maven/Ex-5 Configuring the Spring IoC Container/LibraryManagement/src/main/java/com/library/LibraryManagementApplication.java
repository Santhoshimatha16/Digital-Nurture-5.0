package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class for Exercise 5 – Configuring the Spring IoC Container.
 *
 * Loads the Spring application context from applicationContext.xml,
 * retrieves the BookService bean and calls showBook() to verify that
 * setter injection wired BookRepository correctly.
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("=== Exercise 5: Configuring the Spring IoC Container ===\n");

        // Load the Spring IoC container from the XML configuration file
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n[Main] Spring context loaded successfully.");

        // Retrieve the BookService bean from the container
        BookService bookService = context.getBean("bookService", BookService.class);

        System.out.println("[Main] BookService bean retrieved from context.");
        System.out.println();

        // Invoke business method – proves setter injection worked
        bookService.showBook();

        System.out.println("\n=== Application finished successfully ===");
    }
}
