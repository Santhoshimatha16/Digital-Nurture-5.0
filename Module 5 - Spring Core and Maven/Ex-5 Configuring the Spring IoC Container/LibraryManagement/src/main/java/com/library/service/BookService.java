package com.library.service;

import com.library.repository.BookRepository;

/**
 * BookService provides business-logic operations on books.
 *
 * Exercise 5 – Setter Injection:
 *   The Spring IoC container injects a BookRepository instance
 *   through the setBookRepository() setter method.
 */
public class BookService {

    // Dependency injected via setter by the Spring IoC container
    private BookRepository bookRepository;

    // ── Setter Injection ──────────────────────────────────────────────────────
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] BookRepository injected via setter.");
    }

    // ── Business method ───────────────────────────────────────────────────────
    public void showBook() {
        System.out.println("[BookService] showBook() called.");
        bookRepository.displayBook();
    }
}
