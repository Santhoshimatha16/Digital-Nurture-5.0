package com.library.service;

import com.library.repository.BookRepository;

/**
 * BookService provides business-logic operations on books.
 *
 * Exercise 7 – Constructor + Setter Injection:
 *   - CONSTRUCTOR INJECTION: the bookTitle field is set via the constructor,
 *     configured with <constructor-arg> in applicationContext.xml.
 *   - SETTER INJECTION: the BookRepository dependency is set via the
 *     setBookRepository() method, configured with <property> in applicationContext.xml.
 */
public class BookService {

    // Injected via CONSTRUCTOR injection
    private final String bookTitle;

    // Injected via SETTER injection
    private BookRepository bookRepository;

    // ── Constructor Injection ─────────────────────────────────────────────────
    public BookService(String bookTitle) {
        this.bookTitle = bookTitle;
        System.out.println("[BookService] Constructor called. bookTitle = \"" + bookTitle + "\"");
    }

    // ── Setter Injection ──────────────────────────────────────────────────────
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] BookRepository injected via setter.");
    }

    // ── Business method ───────────────────────────────────────────────────────
    public void showBook() {
        System.out.println("[BookService] Showing book: " + bookTitle);
        bookRepository.displayBook();
    }
}
