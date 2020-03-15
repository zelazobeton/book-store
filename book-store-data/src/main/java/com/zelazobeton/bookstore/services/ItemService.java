package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.exceptions.ResourceNotFoundException;
import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.repository.ItemRepository;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ItemService implements IItemService {

    private ItemRepository repository;

    public ItemService(ItemRepository itemRepository) {
        this.repository = itemRepository;
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> object = repository.findById(id);
        try{
            return object.get();
        }
        catch (NoSuchElementException ex){
            throw new ResourceNotFoundException("Item with id: " + id + " does not exist");
        }

    }

    @Override
    @Transactional
    public Item save(Item object) {
        if(object != null){
            return repository.save(object);
        }
        throw new RuntimeException("Saving object: object is null");
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(items::add);
        return items;
    }

    @Override
    @Transactional
    public void delete(Item object) {
        repository.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Item> findByCategory(Category category) {
        return repository.findAllByCategories(category);
    }

    @Override
    public List<Item> findByName(String name) {
        if(name == ""){
            return findAll();
        }
        System.out.println("@@@ findByName");
        return repository.findByName(name);
    }
}
