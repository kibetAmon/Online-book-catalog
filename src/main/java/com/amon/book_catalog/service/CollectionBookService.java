package com.amon.book_catalog.service;

public interface CollectionBookService {
    void addBookToCollection(Long collectionId, Long bookId);
    void removeBookFromCollection(Long collectionId, Long bookId);
}
