package com.amon.book_catalog.service.implementation;

// Local libraries
import com.amon.book_catalog.entities.Collection;
import com.amon.book_catalog.mappers.CollectionMapper;
import com.amon.book_catalog.service.CollectionService;

// Global libraries
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImplementation implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    // Find all collections
    @Override
    public Collection findById(Long id){
        return collectionMapper.findById(id);
    }

    // Find collections by User Id
    @Override
    public List<Collection> findByUserId(Long userId) {
        return collectionMapper.findByUserId(userId);
    }

    // Add a collection
    @Override
    public void addCollection(Collection collection) {
        collectionMapper.insert(collection);
    }

    // Update a collection
    @Override
    public void updateCollection(Collection collection) {
        collectionMapper.update(collection);
    }

    @Override
    public void deleteCollection(Long id) {
        collectionMapper.delete(id);
    }
}
