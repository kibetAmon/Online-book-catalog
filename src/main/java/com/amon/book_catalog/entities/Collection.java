package com.amon.book_catalog.entities;

// Global libraries
import java.sql.Timestamp;

public class Collection {
    private Long id;
    private Long userId;
    private String name;
    private Timestamp createdAt;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId() {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
