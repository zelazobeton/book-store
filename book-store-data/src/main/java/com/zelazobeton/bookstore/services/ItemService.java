package com.zelazobeton.bookstore.services;

import com.sun.xml.bind.v2.model.core.ID;
import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.repository.ItemRepository;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemService implements IItemService {

    private ItemRepository repository;

    public ItemService(ItemRepository itemRepository) {
        this.repository = itemRepository;
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> object = repository.findById(id);
        if(object.isPresent()){
            return object.get();
        }
        return null;
    }

    @Override
    @Transactional
    public Item save(Item object) {
        if(object == null){
            throw new RuntimeException("Saving object: object is null");
        }
        return repository.save(object);
    }

    @Override
    public Set<Item> findAll() {
        Set<Item> set = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(set::add);
        return set;
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
}
