package com.amon.book_catalog.service.implementation;

// Local libraries
import com.amon.book_catalog.entities.Book;
import com.amon.book_catalog.mappers.BookMapper;
import com.amon.book_catalog.service.BookService;

// Global libraries
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImplementation implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAllBooks() {
        return bookMapper.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return bookMapper.findById(id);
    }

    @Override
    public void addBook(Book book) {
        bookMapper.insert(book);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.update(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookMapper.delete(id);
    }

    @Override
    public List<Book> findBooksByCollectionId(Long collectionId) {
        return bookMapper.findBooksByCollectionId(collectionId);
    }

    // ✅ Implementation for finding books NOT in a specific collection
    @Override
    public List<Book> findBooksNotInCollection(Long collectionId) {
        return bookMapper.findBooksNotInCollection(collectionId);
    }
}
