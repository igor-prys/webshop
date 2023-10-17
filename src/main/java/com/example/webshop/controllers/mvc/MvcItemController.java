package com.example.webshop.controllers.mvc;

import com.example.webshop.entities.Item;
import com.example.webshop.exceptions.NoSuchItemException;
import com.example.webshop.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class MvcItemController {
    private final ItemService itemService;

    public MvcItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    private Item getItem(long id) {
        Optional<Item> optionalItem = itemService.getItem(id);
        if (optionalItem.isEmpty()) {
            throw new NoSuchItemException(String.format("Item with %d id does not exist", id));
        }
        return optionalItem.get();
    }

    @GetMapping
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.getAll());
        return "itemView/items";
    }

    @GetMapping("/{id}")
    public String getItem(Model model, @PathVariable Long id) {
        model.addAttribute("item", getItem(id));
        return "itemView/item";
    }

    @GetMapping("/new")
    public String getFormForNewItem(Model model) {
        Item item = new Item();
        // item.setPresent(false);
        model.addAttribute("newItem", item);
        // model.addAttribute("flag", false);
        return "itemView/newItem";
    }

    @PostMapping
    public String createItem(@ModelAttribute("newItem") Item item) {
        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping("/edit/{id}")
    public String getEditFormForItem(@PathVariable("id") long id, Model model) {
        Item item = getItem(id);
        model.addAttribute("editItem", item);
        return "itemView/editItem";
    }

    @PatchMapping("/{id}")
    public String editItem(@PathVariable("id") long id, @ModelAttribute("editItem") Item item) {
        item.setId(id);
        this.itemService.save(item);
        return "redirect:/items";
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") long id) {
        itemService.delete(id);
        return "redirect:/items";
    }
}

