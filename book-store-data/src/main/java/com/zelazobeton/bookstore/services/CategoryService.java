package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.repository.CategoryRepository;
import com.zelazobeton.bookstore.services.interfaces.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryService extends AbstractServiceJPA<Category, Long>
                             implements ICategoryService {

    public CategoryService(CategoryRepository categoryRepository) {
        super.repository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Category save(Category object) {
        return super.save(object);
    }

    @Override
    public Set<Category> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Category object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
