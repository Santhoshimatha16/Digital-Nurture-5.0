package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringLearnApplication {

    // -----------------------------------------------------------------------
    // Hands-on 3: Logger (SLF4J backed by Logback via Spring Boot)
    // -----------------------------------------------------------------------
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    // -----------------------------------------------------------------------
    // Hands-on 1: main() method - entry point of the Spring Boot application
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        LOGGER.info("START - main()");

        // Boot the application - creates the Spring ApplicationContext,
        // performs component scanning, and starts embedded Tomcat.
        SpringApplication.run(SpringLearnApplication.class, args);

        // Create an instance to call hands-on methods
        SpringLearnApplication app = new SpringLearnApplication();

        // Hands-on 2: Load SimpleDateFormat from XML and parse a date
        app.displayDate();

        // Hands-on 4: Load Country from Spring XML config
        app.displayCountry();

        // Hands-on 5: Singleton vs Prototype scope demonstration
        app.demonstrateScopes();

        // Hands-on 6: Load list of countries
        app.displayCountries();

        LOGGER.info("END - main()");
    }

    // -----------------------------------------------------------------------
    // Hands-on 2: Load SimpleDateFormat bean from date-format.xml
    // -----------------------------------------------------------------------
    public void displayDate() {
        LOGGER.info("START");

        // Create Spring ApplicationContext from XML configuration file
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");

        // Retrieve the SimpleDateFormat bean by id and type
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);

        try {
            // Parse the date string using the format retrieved from Spring context
            Date date = format.parse("31/12/2018");
            LOGGER.debug("Parsed Date : {}", date);
            System.out.println("Parsed Date: " + date);
        } catch (ParseException e) {
            LOGGER.error("Error parsing date: {}", e.getMessage());
        }

        LOGGER.info("END");
    }

    // -----------------------------------------------------------------------
    // Hands-on 4: Load Country bean from country.xml
    // -----------------------------------------------------------------------
    public void displayCountry() {
        LOGGER.info("START");

        // Load Spring context from country.xml (classpath resource)
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        // Retrieve country bean - Spring calls constructor + setters
        Country country = context.getBean("country", Country.class);

        LOGGER.debug("Country : {}", country.toString());

        LOGGER.info("END");
    }

    // -----------------------------------------------------------------------
    // Hands-on 5: Demonstrate Singleton Scope (default) and Prototype Scope
    //
    // To see Singleton behaviour -> no scope attribute in country.xml bean
    // To see Prototype behaviour -> scope="prototype" in country.xml bean
    // -----------------------------------------------------------------------
    public void demonstrateScopes() {
        LOGGER.info("START");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        // First getBean() call
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("First  Country bean  : {}", country);

        // Second getBean() call
        // Singleton -> same instance returned, constructor called ONCE
        // Prototype -> new instance created, constructor called TWICE
        Country anotherCountry = context.getBean("country", Country.class);
        LOGGER.debug("Second Country bean  : {}", anotherCountry);

        if (country == anotherCountry) {
            LOGGER.debug("SINGLETON SCOPE: Both references point to the SAME object.");
        } else {
            LOGGER.debug("PROTOTYPE SCOPE: Both references point to DIFFERENT objects.");
        }

        LOGGER.info("END");
    }

    // -----------------------------------------------------------------------
    // Hands-on 6: Load list of countries from country.xml
    // -----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("START");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        // Retrieve the ArrayList<Country> bean defined in country.xml
        List<Country> countryList = context.getBean("countryList", List.class);

        LOGGER.debug("Number of countries : {}", countryList.size());

        for (Country c : countryList) {
            LOGGER.debug("Country : {}", c);
        }

        LOGGER.info("END");
    }
}
