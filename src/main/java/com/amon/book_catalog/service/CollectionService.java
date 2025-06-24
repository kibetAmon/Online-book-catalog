package com.amon.book_catalog.service;

// Local libraries
import com.amon.book_catalog.entities.Collection;

// Global libraries
import java.util.List;

public interface CollectionService {
    Collection findById(Long id);
    List<Collection> findByUserId(Long userId);
    void addCollection(Collection collection);
    void updateCollection(Collection collection);
    void deleteCollection(Long id);
}
