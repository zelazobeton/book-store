package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.repository.ItemRepository;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ItemService extends AbstractServiceJPA<Item, Long>
                         implements IItemService {

    public ItemService(ItemRepository categoryRepository) {
        super.repository = categoryRepository;
    }

    @Override
    public Item findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Item save(Item object) {
        return super.save(object);
    }

    @Override
    public Set<Item> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Item object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
