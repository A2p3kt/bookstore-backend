package dev.toluwalase.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import dev.toluwalase.bookstore.model.Category;
import dev.toluwalase.bookstore.model.CategoryRepository;

@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository repository;

    @Test
    void saveNewCategory() {
        Category savedCategory = repository.save(new Category("Fantasy"));

        assertThat(savedCategory.getId()).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("Fantasy");
    }

    @SuppressWarnings("null")
    @Test
    void findByIdReturnsSavedCategory() {
        Category savedCategory = repository.save(new Category("Classic"));

        assertThat(repository.findById(savedCategory.getId()))
                .isPresent()
                .get()
                .extracting(Category::getName)
                .isEqualTo("Classic");
    }

    @Test
    void findByIdReturnsEmptyForUnknownId() {
        assertThat(repository.findById(999999L)).isEmpty();
    }

    @SuppressWarnings("null")
    @Test
    void findAllReturnsAllSavedCategories() {
        repository.save(new Category("Fiction"));
        repository.save(new Category("Biography"));

        assertThat(repository.findAll())
                .extracting(Category::getName)
                .containsExactlyInAnyOrder("Fiction", "Biography");
    }

    @Test
    void findAllReturnsEmptyWhenNoCategoriesAreSaved() {
        assertThat(repository.findAll()).isEmpty();
    }

    @SuppressWarnings("null")
    @Test
    void updateChangesSavedCategory() {
        Category savedCategory = repository.save(new Category("Science"));

        savedCategory.setName("Science Fiction");
        Category updatedCategory = repository.save(savedCategory);

        assertThat(updatedCategory.getId()).isEqualTo(savedCategory.getId());
        assertThat(repository.findById(savedCategory.getId()))
                .get()
                .extracting(Category::getName)
                .isEqualTo("Science Fiction");
    }

    @Test
    void deleteRemovesCategory() {
        Category savedCategory = repository.save(new Category("Temporary"));

        repository.deleteById(savedCategory.getId());

        assertThat(repository.findById(savedCategory.getId())).isEmpty();
    }
}
