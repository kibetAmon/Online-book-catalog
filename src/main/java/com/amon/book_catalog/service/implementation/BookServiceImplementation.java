// A java class that implements Book Service

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

    // Fetch all the books
    @Override
    public List<Book> findAllBooks(){
        return bookMapper.findAll();
    }

    // Fetch books by their id
    @Override
    public Book findBookById(Long id){
        return bookMapper.findById(id);
    }

    // Add a book
    @Override
    public void addBook(Book book){
        bookMapper.insert(book);
    }

    // Update a book
    @Override
    public void updateBook(Book book){
        bookMapper.update(book);
    }

    // Delete a book
    @Override
    public void deleteBook(Long id) {
        bookMapper.delete(id);
    }

    // Find books by collection id
    @Override
    public List<Book> findBooksByCollectionId(Long collectionId) {
        return bookMapper.findBooksByCollectionId(collectionId);
    }
}
