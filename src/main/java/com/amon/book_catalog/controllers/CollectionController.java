package com.amon.book_catalog.controllers;

// Local libraries
import com.amon.book_catalog.entities.Collection;
import com.amon.book_catalog.service.CollectionService;

// Global libraries
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/collections")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    // List collections by user
    @GetMapping("/user/{userId}")
    public String listCollectionsByUser(@PathVariable Long userId, Model model) {
        List<Collection> collections = collectionService.findByUserId(userId);
        model.addAttribute("collections", collections);
        return "collections/list";
    }

    // View a single collection
    @GetMapping("/{id}")
    public String viewCollection(@PathVariable Long id, Model model) {
        Collection collection = collectionService.findById(id);
        model.addAttribute("collection", collection);
        return "collections/detail";
    }

    // Form to create a new collection
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("collection", new Collection());
        return "collections/add";
    }

    // Handle POST to create a collection
    @PostMapping("/add")
    public String addCollection(@ModelAttribute Collection collection) {
        collectionService.addCollection(collection);
        return "redirect:/collections/user/" + collection.getUserId();
    }

    // Form to edit a collection
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Collection collection = collectionService.findById(id);
        model.addAttribute("collection", collection);
        return "collections/edit";
    }

    // Handle POST to update collection
    @PostMapping("/edit")
    public String updateCollection(@ModelAttribute Collection collection) {
        collectionService.updateCollection(collection);
        return "redirect:/collections/user/" + collection.getUserId();
    }

    // Delete a collection
    @PostMapping("/delete/{id}")
    public String deleteCollection(@PathVariable Long id) {
        Collection collection = collectionService.findById(id);
        Long userId = collection.getUserId();
        collectionService.deleteCollection(id);
        return "redirect:/collections/user/" + userId;
    }
}