package dev.toluwalase.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import dev.toluwalase.bookstore.model.Book;
import dev.toluwalase.bookstore.model.BookRepository;
import dev.toluwalase.bookstore.model.Category;
import dev.toluwalase.bookstore.model.CategoryRepository;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository cRepository;

    @Test
    public void saveNewBook() {
        Category category = new Category("Fan-Fiction");
        cRepository.save(category);

        Book book = new Book("The Silent Echo", "Elara Vance", 2021, "978-3-16-148410-0", 19.99f);
        book.setCategory(category);

        Book savedBook = repository.save(book);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("The Silent Echo");
        assertThat(savedBook.getCategory().getName()).isEqualTo("Fan-Fiction");
    }

    @SuppressWarnings("null")
    @Test
    void findByIdReturnsSavedBook() {
        Book savedBook = repository.save(
                new Book("1984", "George Orwell", 1949, "978-0451524935", 9.99f));

        assertThat(repository.findById(savedBook.getId()))
                .isPresent()
                .get()
                .extracting(Book::getTitle, Book::getAuthor)
                .containsExactly("1984", "George Orwell");
    }

    @Test
    void findByIdReturnsEmptyForUnknownId() {
        assertThat(repository.findById(999999L)).isEmpty();
    }

    @SuppressWarnings("null")
    @Test
    void findAllReturnsAllSavedBooks() {
        repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937,
                "978-0547928227", 14.99f));
        repository.save(new Book("Pride and Prejudice", "Jane Austen", 1813,
                "978-0141439518", 8.99f));

        assertThat(repository.findAll())
                .extracting(Book::getTitle)
                .containsExactlyInAnyOrder("The Hobbit", "Pride and Prejudice");
    }

    @Test
    void findAllReturnsEmptyWhenNoBooksAreSaved() {
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void updateChangesSavedBook() {
        Book savedBook = repository.save(
                new Book("Old Title", "Author", 2020, "isbn-1", 10.0f));

        savedBook.setTitle("New Title");
        savedBook.setPrice(12.5f);
        Book updatedBook = repository.save(savedBook);

        assertThat(updatedBook.getId()).isEqualTo(savedBook.getId());
        assertThat(repository.findById(savedBook.getId()))
                .get()
                .satisfies(book -> {
                    assertThat(book.getTitle()).isEqualTo("New Title");
                    assertThat(book.getPrice()).isEqualTo(12.5f);
                });
    }

    @Test
    void deleteRemovesBook() {
        Book savedBook = repository.save(
                new Book("Book to Delete", "Author", 2020, "isbn-2", 10.0f));

        repository.deleteById(savedBook.getId());

        assertThat(repository.findById(savedBook.getId())).isEmpty();
    }
}