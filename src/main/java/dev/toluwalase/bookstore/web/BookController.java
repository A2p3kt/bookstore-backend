package dev.toluwalase.bookstore.web;

import dev.toluwalase.bookstore.model.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller 
@RequestMapping 
public class BookController {

    private final BookRepository bookRepository;

    BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/index")
    public String index() {
        return "Welcome to the bookstore";
    }

    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }
    
    
}
