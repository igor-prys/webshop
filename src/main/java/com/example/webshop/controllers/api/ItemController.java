package com.example.webshop.controllers.api;

import com.example.webshop.entities.Item;
import com.example.webshop.exceptions.NoSuchItemException;
import com.example.webshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable long id) {
        Optional<Item> item = itemService.getItem(id);
        if (item.isEmpty()) {
            throw new NoSuchItemException(String.format("Item with id %d was not found", id));
        }
        return item.get();
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.getAll();
    }

    @PostMapping
    public Item save(@RequestBody Item item) {
        return itemService.save(item);
    }

    @PutMapping
    public Item updateItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    @DeleteMapping("{id}")
    public String deleteItem(@PathVariable long id) {
        Optional<Item> item = itemService.getItem(id);
        if (item.isEmpty()) {
            throw new NoSuchItemException(String.format("Item with id %d was not found", id));
        }
        itemService.delete(id);
        return "Item with id " + id + "was deleted";
    }

}
