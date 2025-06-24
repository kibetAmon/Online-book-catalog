package com.amon.book_catalog.mappers;

// Global libraries
import com.amon.book_catalog.entities.Collection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionMapper {

    // Read the collections
    @Select("SELECT * FROM collections WHERE id = #{id}")
    Collection findById(Long id);

    // Read the collections by user
    @Select("SELECT * FROM collections WHERE user_id = #{userId}")
    List<Collection> findByUserId(Long userId);

    // Insert a collection
    @Insert("INSERT INTO collections(user_id, name) VALUES (#{userId}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Collection collection);

    // Update a collection
    @Update("UPDATE collections SET name = #{name} WHERE id = #{id}")
    void update(Collection collection);

    // Delete a collection
    @Delete("DELETE FROM collections WHERE id = #{id}")
    void delete(Long id);

}
