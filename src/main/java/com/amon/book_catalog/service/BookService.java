package com.amon.book_catalog.service;

// Local libraries
import com.amon.book_catalog.entities.Book;

// Global libraries
import java.util.List;

public interface BookService {
    List<Book> findAllBooks();
    Book findBookById(Long id);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(Long id);
    List<Book> findBooksByCollectionId(Long collectionId);

    // ✅ New: Find books not in a given collection
    List<Book> findBooksNotInCollection(Long collectionId);
}
