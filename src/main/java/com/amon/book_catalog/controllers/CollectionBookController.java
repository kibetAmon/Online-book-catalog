package com.amon.book_catalog.controllers;

// Local libraries
import com.amon.book_catalog.service.CollectionBookService;

// Global libraries
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/collection-books")
public class CollectionBookController {

    @Autowired
    private CollectionBookService collectionBookService;

    // Add book to a collection
    @PostMapping("/add")
    public String addBookToCollection(@RequestParam Long collectionId, @RequestParam Long bookId) {
        collectionBookService.addBookToCollection(collectionId, bookId);
        return "redirect:/books/collection/" + collectionId;
    }

    // Remove book from a collection
    @PostMapping("/remove")
    public String removeBookFromCollection(@RequestParam Long collectionId, @RequestParam Long bookId) {
        collectionBookService.removeBookFromCollection(collectionId, bookId);
        return "redirect:/books/collection/" + collectionId;
    }
}
