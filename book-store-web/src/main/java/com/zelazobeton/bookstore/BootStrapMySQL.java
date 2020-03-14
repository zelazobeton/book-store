package com.zelazobeton.bookstore;

import com.zelazobeton.bookstore.commands.ItemCommand;
import com.zelazobeton.bookstore.model.Category;
import com.zelazobeton.bookstore.repository.CategoryRepository;
import com.zelazobeton.bookstore.repository.ItemRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    public BootStrapMySQL(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Category albums = new Category("Albums");
        Category for_kids = new Category("For kids");
        Category foreign_languages = new Category("Foreign languages");
        Category sciHum = new Category("Science and humanities");
        Category literature = new Category("Literature");

        foreign_languages.addSubcategory(new Category("Spanish"));
        foreign_languages.addSubcategory(new Category("Chinese"));
        foreign_languages.addSubcategory(new Category("French"));
        foreign_languages.addSubcategory(new Category("German"));
        foreign_languages.addSubcategory(new Category("Polish"));

        sciHum.addSubcategory(new Category("Social sciences"));
        sciHum.addSubcategory(new Category("Humanities"));
        sciHum.addSubcategory(new Category("Economy"));
        sciHum.addSubcategory(new Category("Science"));
        sciHum.addSubcategory(new Category("Medicine"));
        sciHum.addSubcategory(new Category("Biology"));
        sciHum.addSubcategory(new Category("Technical science"));

        categoryRepository.save(albums);
        categoryRepository.save(for_kids);
        categoryRepository.save(foreign_languages);
        categoryRepository.save(sciHum);
        categoryRepository.save(literature);

        for(int idx = 1; idx < 10; idx++){
            itemRepository.save((new ItemCommand())
                    .name("Effective Java vol. " + idx)
                    .descriptionShort("Great book containing best practices of software development in Java.")
                    .descriptionFull("Blablabla Great book containing best practices of software development in Java.")
                    .price(9.99 * idx)
                    .addCategory(categoryRepository.findByName("Technical science").get())
                    .buildItem());
        }
    }
}
