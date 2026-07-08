package com.library.model;

import jakarta.persistence.*;

/**
 * Book entity mapped to the BOOK table in the H2 database.
 *
 * Fields:
 *   id     – auto-generated primary key
 *   title  – book title (cannot be null)
 *   author – book author (cannot be null)
 *   isbn   – International Standard Book Number (unique)
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(unique = true)
    private String isbn;

    // ── Constructors ──────────────────────────────────────────────────────────

    public Book() {}

    public Book(String title, String author, String isbn) {
        this.title  = title;
        this.author = author;
        this.isbn   = isbn;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Long getId()                { return id; }
    public void setId(Long id)         { this.id = id; }

    public String getTitle()           { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor()               { return author; }
    public void setAuthor(String author)    { this.author = author; }

    public String getIsbn()            { return isbn; }
    public void setIsbn(String isbn)   { this.isbn = isbn; }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author
                + "', isbn='" + isbn + "'}";
    }
}
