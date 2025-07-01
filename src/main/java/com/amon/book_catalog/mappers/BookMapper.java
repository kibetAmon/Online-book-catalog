package com.amon.book_catalog.mappers;

// Local libraries
import com.amon.book_catalog.entities.Book;

// Global libraries
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.LocalDateTypeHandler;
import java.util.List;

@Mapper
public interface BookMapper {

    @Select("""
        SELECT id, title, author, isbn, published_date AS publishedDate, cover_image_url AS coverImageUrl,
               created_at AS createdAt
        FROM books WHERE id = #{id}
    """)
    @Results({
            @Result(property = "publishedDate", column = "published_date", typeHandler = LocalDateTypeHandler.class)
    })
    Book findById(Long id);

    @Select("""
        SELECT id, title, author, isbn, published_date AS publishedDate, cover_image_url AS coverImageUrl,
               created_at AS createdAt
        FROM books
    """)
    @Results({
            @Result(property = "publishedDate", column = "published_date", typeHandler = LocalDateTypeHandler.class)
    })
    List<Book> findAll();

    @Insert("""
        INSERT INTO books(title, author, isbn, published_date, cover_image_url)
        VALUES(#{title}, #{author}, #{isbn}, #{publishedDate, typeHandler=org.apache.ibatis.type.LocalDateTypeHandler},
        #{coverImageUrl})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Book book);

    @Update("""
        UPDATE books SET title = #{title}, author = #{author}, isbn = #{isbn},
        published_date = #{publishedDate, typeHandler=org.apache.ibatis.type.LocalDateTypeHandler},
        cover_image_url = #{coverImageUrl} WHERE id = #{id}
    """)
    void update(Book book);

    @Delete("DELETE FROM books WHERE id = #{id}")
    void delete(Long id);

    @Select("""
        SELECT b.id, b.title, b.author, b.isbn, b.published_date AS publishedDate,
               b.cover_image_url AS coverImageUrl, b.created_at AS createdAt
        FROM books b
        JOIN collection_books cb ON b.id = cb.book_id
        WHERE cb.collection_id = #{collectionId}
    """)
    @Results({
            @Result(property = "publishedDate", column = "published_date", typeHandler = LocalDateTypeHandler.class)
    })
    List<Book> findBooksByCollectionId(Long collectionId);
}
