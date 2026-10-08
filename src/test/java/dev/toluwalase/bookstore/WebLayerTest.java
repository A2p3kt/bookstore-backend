package dev.toluwalase.bookstore;

import dev.toluwalase.bookstore.model.Book;
import dev.toluwalase.bookstore.model.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest 
@AutoConfigureMockMvc 
public class WebLayerTest {
    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test 
    public void testIndexRoute() throws Exception {
        this.mockMvc.perform(get("/index"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Welcome to the bookstore")));
    }

    @Test
    void booksRouteReturnsBooksAsJson() throws Exception {
        bookRepository.save(new Book(
                "The Hobbit",
                "J.R.R. Tolkien",
                1937,
                "978-0547928227",
                14.99f));

        mockMvc.perform(get("/books").with(user("user")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("The Hobbit"))
                .andExpect(jsonPath("$[0].author").value("J.R.R. Tolkien"))
                .andExpect(jsonPath("$[0].publicationYear").value(1937))
                .andExpect(jsonPath("$[0].isbn").value("978-0547928227"))
                .andExpect(jsonPath("$[0].price").value(14.99));
    }

    @Test
    void booksRouteReturnsEmptyArrayWhenThereAreNoBooks() throws Exception {
        mockMvc.perform(get("/books").with(user("user")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void bookRouteReturnsBookAsJson() throws Exception {
        Book savedBook = bookRepository.save(new Book(
                "1984",
                "George Orwell",
                1949,
                "978-0451524935",
                9.99f));

        mockMvc.perform(get("/book/{id}", savedBook.getId()).with(user("user")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.id").value(savedBook.getId()))
                .andExpect(jsonPath("$.title").value("1984"))
                .andExpect(jsonPath("$.author").value("George Orwell"))
                .andExpect(jsonPath("$.publicationYear").value(1949))
                .andExpect(jsonPath("$.isbn").value("978-0451524935"))
                .andExpect(jsonPath("$.price").value(9.99));
    }

    @Test
    void bookRouteReturnsEmptyBodyWhenBookDoesNotExist() throws Exception {
        mockMvc.perform(get("/book/{id}", 999999L).with(user("user")))
                .andExpect(status().isOk())
                .andExpect(content().string("null"));
    }
}
