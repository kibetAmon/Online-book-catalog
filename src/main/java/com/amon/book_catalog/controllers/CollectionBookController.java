package com.amon.book_catalog.controllers;

import com.amon.book_catalog.entities.Book;
import com.amon.book_catalog.service.BookService;
import com.amon.book_catalog.service.CollectionBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/collection-books")
public class CollectionBookController {

    @Autowired
    private CollectionBookService collectionBookService;

    @Autowired
    private BookService bookService;

    // ✅ GET view of books in a specific collection
    @GetMapping("/{collectionId}/books")
    public String viewBooksInCollection(@PathVariable Long collectionId, Model model) {
        List<Book> booksInCollection = bookService.findBooksByCollectionId(collectionId);
        List<Book> availableBooks = bookService.findBooksNotInCollection(collectionId);

        model.addAttribute("books", booksInCollection);
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("collectionId", collectionId);

        return "collections/collectionbooks"; // ✅ Thymeleaf template to render
    }

    // ✅ POST: Add book to collection
    @PostMapping("/add")
    public String addBookToCollection(@RequestParam Long collectionId, @RequestParam Long bookId) {
        collectionBookService.addBookToCollection(collectionId, bookId);
        return "redirect:/collection-books/" + collectionId + "/books";
    }

    // ✅ POST: Remove book from collection
    @PostMapping("/remove")
    public String removeBookFromCollection(@RequestParam Long collectionId, @RequestParam Long bookId) {
        collectionBookService.removeBookFromCollection(collectionId, bookId);
        return "redirect:/collection-books/" + collectionId + "/books";
    }
}
