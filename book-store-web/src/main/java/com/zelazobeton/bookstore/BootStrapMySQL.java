package com.zelazobeton.bookstore;

import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.repository.CategoryRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;

    public BootStrapMySQL(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Category cat1 = new Category();
        cat1.setName("American");
        categoryRepository.save(cat1);
    }
}
