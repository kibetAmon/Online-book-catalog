package com.amon.book_catalog.service.implementation;

// Local libraries
import com.amon.book_catalog.mappers.CollectionBookMapper;
import com.amon.book_catalog.service.CollectionBookService;

// Global libraries
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionBookServiceImplementation implements CollectionBookService {

    @Autowired
    private CollectionBookMapper collectionBookMapper;

    // Add book to collection
    @Override
    public void addBookToCollection(Long collectionId, Long bookId) {
        collectionBookMapper.addBookToCollection(collectionId, bookId);
    }

    // Remove book from collection
    @Override
    public void removeBookFromCollection(Long collectionId, Long bookId) {
        collectionBookMapper.removeBookFromCollection(collectionId, bookId);
    }
}
