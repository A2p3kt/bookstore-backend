package dev.toluwalase.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.toluwalase.bookstore.model.Book;
import dev.toluwalase.bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean 
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "978-0061120084", 12.99f));
			repository.save(new Book("1984", "George Orwell", 1949, "978-0451524935", 9.99f));
			repository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "978-0743273565", 10.50f));
			repository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "978-0141439518", 8.99f));
			repository.save(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "978-0316769488", 11.25f));
			repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "978-0547928227", 14.99f));
			repository.save(new Book("Brave New World", "Aldous Huxley", 1932, "978-0060850524", 13.50f));
			repository.save(new Book("Fahrenheit 451", "Ray Bradbury", 1953, "978-1451673319", 10.99f));
			repository.save(new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866, "978-0140449136", 15.00f));
			repository.save(new Book("The Alchemist", "Paulo Coelho", 1988, "978-0062315007", 12.49f));
		};
	}

}
