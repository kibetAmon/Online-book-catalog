package com.amon.book_catalog.entities;

import java.time.LocalDateTime;

public class CollectionBook {

    private Long id;
    private Long collectionId;
    private Long bookId;
    private LocalDateTime addedAt;

    // Default constructor
    public CollectionBook() {
    }

    // All-args constructor
    public CollectionBook(Long id, Long collectionId, Long bookId, LocalDateTime addedAt) {
        this.id = id;
        this.collectionId = collectionId;
        this.bookId = bookId;
        this.addedAt = addedAt;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
