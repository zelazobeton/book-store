package com.zelazobeton.bookstore;

import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.repository.CategoryRepository;
import com.zelazobeton.bookstore.repository.ItemRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    public BootStrapMySQL(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        categoryRepository.save(new Category("American"));
        categoryRepository.save(new Category("Philosophy"));
        categoryRepository.save(new Category("Programming languages"));

        for(int idx = 1; idx < 10; idx++){
            itemRepository.save((new Item.ItemBuilder())
                    .name("Effective Java vol. " + idx)
                    .descriptionShort("Great book containing best practices of software development in Java.")
                    .descriptionFull("Blablabla Great book containing best practices of software development in Java.")
                    .price(9.99 * idx)
                    .addCategory(categoryRepository.findByName("Programming languages").get())
                    .build());
        }
    }
}
