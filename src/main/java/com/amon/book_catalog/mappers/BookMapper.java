package com.amon.book_catalog.mappers;

import com.amon.book_catalog.entities.Book;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * MyBatis mapper for Book entity.
 */
@Mapper
public interface BookMapper {

    // Get all books
    @Select("""
        SELECT id, title, author, isbn, published_date, cover_image_url, created_at
        FROM books
    """)
    @Results({
            @Result(property = "publishedDate", column = "published_date",
                    typeHandler = org.apache.ibatis.type.LocalDateTypeHandler.class),
            @Result(property = "createdAt", column = "created_at",
                    typeHandler = org.apache.ibatis.type.LocalDateTimeTypeHandler.class)
    })
    List<Book> findAll();

    // Get a book by ID
    @Select("""
        SELECT id, title, author, isbn, published_date, cover_image_url, created_at
        FROM books
        WHERE id = #{id}
    """)
    @Results({
            @Result(property = "publishedDate", column = "published_date",
                    typeHandler = org.apache.ibatis.type.LocalDateTypeHandler.class),
            @Result(property = "createdAt", column = "created_at",
                    typeHandler = org.apache.ibatis.type.LocalDateTimeTypeHandler.class)
    })
    Book findById(Long id);

    // Insert a new book
    @Insert("""
        INSERT INTO books(title, author, isbn, published_date, cover_image_url)
        VALUES(#{title}, #{author}, #{isbn},
               #{publishedDate, typeHandler=org.apache.ibatis.type.LocalDateTypeHandler},
               #{coverImageUrl})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Book book);

    // Update an existing book
    @Update("""
        UPDATE books SET title = #{title}, author = #{author}, isbn = #{isbn},
                         published_date = #{publishedDate, typeHandler=org.apache.ibatis.type.LocalDateTypeHandler},
                         cover_image_url = #{coverImageUrl}
        WHERE id = #{id}
    """)
    void update(Book book);

    // Delete a book by ID
    @Delete("DELETE FROM books WHERE id = #{id}")
    void delete(Long id);

    // Get books by collection ID
    @Select("""
        SELECT b.id, b.title, b.author, b.isbn, b.published_date, b.cover_image_url, b.created_at
        FROM books b
        JOIN collection_books cb ON b.id = cb.book_id
        WHERE cb.collection_id = #{collectionId}
    """)
    @Results({
            @Result(property = "publishedDate", column = "published_date",
                    typeHandler = org.apache.ibatis.type.LocalDateTypeHandler.class),
            @Result(property = "createdAt", column = "created_at",
                    typeHandler = org.apache.ibatis.type.LocalDateTimeTypeHandler.class)
    })
    List<Book> findBooksByCollectionId(Long collectionId);
}