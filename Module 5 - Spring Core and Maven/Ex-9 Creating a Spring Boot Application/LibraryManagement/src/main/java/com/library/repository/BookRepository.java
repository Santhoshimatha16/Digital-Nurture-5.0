package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookRepository extends JpaRepository so Spring Data JPA automatically
 * provides implementations for all standard CRUD operations at runtime.
 *
 * Extra derived query:
 *   findByAuthor – generated from the method name by Spring Data.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Returns all books written by the given author.
     * Spring Data JPA generates the SQL from the method name automatically.
     */
    List<Book> findByAuthor(String author);
}
