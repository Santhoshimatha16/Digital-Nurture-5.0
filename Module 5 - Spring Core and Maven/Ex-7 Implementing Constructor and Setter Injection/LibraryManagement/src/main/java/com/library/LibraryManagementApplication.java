package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class for Exercise 7 – Implementing Constructor and Setter Injection.
 *
 * Loads the Spring application context from applicationContext.xml,
 * retrieves the BookService bean which is configured with:
 *   - Constructor injection  (bookTitle)
 *   - Setter injection       (bookRepository)
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("=== Exercise 7: Constructor and Setter Injection ===\n");

        // Load the Spring IoC container from the XML configuration file
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n[Main] Spring context loaded successfully.");

        // Retrieve the BookService bean (constructor + setter injection already done)
        BookService bookService = context.getBean("bookService", BookService.class);

        System.out.println("[Main] BookService bean retrieved from context.");
        System.out.println();

        // Invoke business method – proves both injections worked
        bookService.showBook();

        System.out.println("\n=== Application finished successfully ===");
    }
}
