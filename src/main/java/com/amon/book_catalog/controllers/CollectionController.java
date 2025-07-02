package com.amon.book_catalog.controllers;

import com.amon.book_catalog.entities.Collection;
import com.amon.book_catalog.service.BookService;
import com.amon.book_catalog.service.CollectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private BookService bookService; // ✅ Needed to show books in a collection

    // ✅ 1. List all collections for a specific user
    @GetMapping("/user/{userId}")
    public String listCollectionsByUser(@PathVariable Long userId, Model model) {
        List<Collection> collections = collectionService.findByUserId(userId);
        model.addAttribute("collections", collections);
        return "collections/list";
    }

    // ✅ 2. View a single collection by ID (optional detail page)
    @GetMapping("/{id}")
    public String viewCollection(@PathVariable Long id, Model model) {
        Collection collection = collectionService.findById(id);
        model.addAttribute("collection", collection);
        return "collections/detail";
    }

    // ✅ 3. Show add collection form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("collection", new Collection());
        return "collections/add";
    }

    // ✅ 4. Handle create collection POST
    @PostMapping("/add")
    public String addCollection(@ModelAttribute Collection collection) {
        collection.setUserId(1L); // Dev mode default user
        collectionService.addCollection(collection);
        return "redirect:/collections/user/" + collection.getUserId();
    }

    // ✅ 5. Show edit collection form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Collection collection = collectionService.findById(id);
        model.addAttribute("collection", collection);
        return "collections/edit";
    }

    // ✅ 6. Handle edit form submission
    @PostMapping("/edit")
    public String updateCollection(@ModelAttribute Collection collection) {
        collectionService.updateCollection(collection);
        return "redirect:/collections/user/" + collection.getUserId();
    }

    // ✅ 7. Delete a collection
    @PostMapping("/delete/{id}")
    public String deleteCollection(@PathVariable Long id) {
        Collection collection = collectionService.findById(id);
        Long userId = collection.getUserId();
        collectionService.deleteCollection(id);
        return "redirect:/collections/user/" + userId;
    }

    // ✅ 8. View books in a specific collection (for collectionbook.html)
    @GetMapping("/{id}/books")
    public String viewBooksInCollection(@PathVariable Long id, Model model) {
        Collection collection = collectionService.findById(id);
        if (collection == null) {
            return "redirect:/collections/user/1";
        }

        model.addAttribute("collectionId", id);
        model.addAttribute("collectionName", collection.getName());
        model.addAttribute("books", bookService.findBooksByCollectionId(id));              // Existing books
        model.addAttribute("availableBooks", bookService.findBooksNotInCollection(id));    // Addable books

        return "collections/collectionbook";
    }
}
