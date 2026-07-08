package com.library.controller;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookController exposes RESTful endpoints for CRUD operations on Book.
 *
 * Endpoints:
 *   GET    /api/books          – retrieve all books
 *   GET    /api/books/{id}     – retrieve a book by id
 *   POST   /api/books          – create a new book
 *   PUT    /api/books/{id}     – update an existing book
 *   DELETE /api/books/{id}     – delete a book
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    // Injected by Spring Boot automatically (constructor injection)
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ── GET /api/books ─────────────────────────────────────────────────────────
    /**
     * Returns a list of all books stored in the database.
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ── GET /api/books/{id} ────────────────────────────────────────────────────
    /**
     * Returns a single book by its primary-key id.
     * Returns 404 if no book with that id exists.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── POST /api/books ────────────────────────────────────────────────────────
    /**
     * Creates a new book from the request body JSON.
     * Returns HTTP 201 Created with the saved book.
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // ── PUT /api/books/{id} ────────────────────────────────────────────────────
    /**
     * Updates an existing book identified by {id}.
     * Returns the updated book, or 404 if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @RequestBody Book bookDetails) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookDetails.getTitle());
                    existingBook.setAuthor(bookDetails.getAuthor());
                    existingBook.setIsbn(bookDetails.getIsbn());
                    Book updated = bookRepository.save(existingBook);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ── DELETE /api/books/{id} ─────────────────────────────────────────────────
    /**
     * Deletes the book with the given id.
     * Returns 204 No Content on success, or 404 if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
