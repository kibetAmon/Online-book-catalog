package com.amon.book_catalog.controllers;

import com.amon.book_catalog.entities.Collection;
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

    // ✅ List all collections for a user
    @GetMapping("/user/{userId}")
    public String listCollectionsByUser(@PathVariable Long userId, Model model) {
        List<Collection> collections = collectionService.findByUserId(userId);
        model.addAttribute("collections", collections);
        return "collections/list";
    }

    // ✅ View a single collection by ID
    @GetMapping("/{id}")
    public String viewCollection(@PathVariable Long id, Model model) {
        Collection collection = collectionService.findById(id);
        model.addAttribute("collection", collection);
        return "collections/detail";
    }

    // ✅ Show form to add new collection
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("collection", new Collection());
        return "collections/add";
    }

    // ✅ Handle form submission to add a new collection
    @PostMapping("/add")
    public String addCollection(@ModelAttribute Collection collection) {
        // Development mode: hardcoded user ID
        collection.setUserId(1L); // Replace with authenticated user's ID in production
        collectionService.addCollection(collection);
        return "redirect:/collections/user/" + collection.getUserId();
    }

    // ✅ Show form to edit existing collection
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Collection collection = collectionService.findById(id);
        model.addAttribute("collection", collection);
        return "collections/edit";
    }

    // ✅ Handle form submission to update a collection
    @PostMapping("/edit")
    public String updateCollection(@ModelAttribute Collection collection) {
        collectionService.updateCollection(collection);
        return "redirect:/collections/user/" + collection.getUserId();
    }

    // ✅ Handle request to delete a collection
    @PostMapping("/delete/{id}")
    public String deleteCollection(@PathVariable Long id) {
        Collection collection = collectionService.findById(id);
        Long userId = collection.getUserId();
        collectionService.deleteCollection(id);
        return "redirect:/collections/user/" + userId;
    }
}
