package com.amon.book_catalog.mappers;

// Local libraries
import com.amon.book_catalog.entities.Book;

// Global libraries
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface BookMapper {

    // Read book by id
    @Select("SELECT * FROM books WHERE id = #{id}")
    Book findById(Long id);

    @Select("SELECT * FROM books")
    List<Book> findAll();

    // Insert a book
    @Insert("INSERT INTO books(title, author, isbn, published_date, cover_image_url)" +
    "VALUES(#{title}, #{author}, #{isbn}, #{published_Date}, #{coverImageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert (Book book);

    // Update a book
    @Update("UPDATE books SET title = #{title}, author = #{author}, isbn = #{isbn}, " +
            "published_date = #{publishedDate}, cover_image_url = #{coverImageUrl} WHERE id = #{id}")
    void update(Book book);

    // Delete a book
    @Delete("DELETE FROM books WHERE id = #{id}")
    void delete(Long id);

    // Find books by their collection
    @Select("""
        SELECT b.* FROM books b
        JOIN collection_books cb ON b.id = cb.book_id
        WHERE cb.collection_id = #{collectionId}
    """)
    List<Book> findBooksByCollectionId(Long collectionId);

}
