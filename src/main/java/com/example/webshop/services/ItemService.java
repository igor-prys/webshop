package com.example.webshop.services;

import com.example.webshop.entities.Item;
import com.example.webshop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public Optional<Item> getItem(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> getAll(){
       return itemRepository.findAll();
    }
    public Item update(Item item){
        return itemRepository.save(item);
    }
    public void delete(Long id){
        itemRepository.deleteById(id);
    }
}
