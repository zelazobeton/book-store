package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.exceptions.ResourceNotFoundException;
import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.repository.CategoryRepository;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CategoryService implements ICategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> object = repository.findById(id);
        try{
            return object.get();
        }
        catch(NoSuchElementException ex){
            throw new ResourceNotFoundException("Category with id: " + id + " does not exist");
        }
    }

    @Override
    public Category findByName(String name) {
        Optional<Category> object = repository.findByName(name);
        try{
            return object.get();
        }
        catch(NoSuchElementException ex){
            throw new ResourceNotFoundException("Category with name: " + name + " does not exist");
        }
    }

    @Override
    @Transactional
    public Category save(Category object) {
        if(object == null){
            throw new RuntimeException("Saving category: object is null");
        }
        return repository.save(object);
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    @Transactional
    public void delete(Category object) {
        repository.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
