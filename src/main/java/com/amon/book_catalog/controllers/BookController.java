package com.amon.book_catalog.controllers;

import com.amon.book_catalog.entities.Book;
import com.amon.book_catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // List all books
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books/list";
    }

    // View a single book
    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "books/detail";
    }

    // Form to add a new book
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add";
    }

    // Handle POST to add a new book
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    // Form to edit an existing book
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    // Handle POST to update a book
    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }

    // Delete a book
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    // List books belonging to a specific collection
    @GetMapping("/collection/{collectionId}")
    public String booksByCollection(@PathVariable Long collectionId, Model model) {
        List<Book> books = bookService.findBooksByCollectionId(collectionId);
        model.addAttribute("books", books);
        return "books/collection_books";
    }
}
