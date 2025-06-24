package com.amon.book_catalog.mappers;

// Global libraries
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;

@Mapper
public interface CollectionBookMapper {

    // Insert a collection book
    @Insert("INSERT INTO collection_books(collection_id, book_id) VALUES(#{collectionId}, #{bookId})")
    void addBookToCollection(@Param("collectionId") Long collectionId, @Param("bookId") Long bookId);

    // Delete a collection book
    @Delete("DELETE FROM collection_books WHERE collection_id = #{collectionId} AND book_id = #{bookId}")
    void removeBookFromCollection(@Param("collectionId") Long collectionId, @Param("bookId") Long bookId);
}
