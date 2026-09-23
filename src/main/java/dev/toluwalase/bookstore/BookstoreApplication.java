package dev.toluwalase.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.toluwalase.bookstore.model.Book;
import dev.toluwalase.bookstore.model.BookRepository;
import dev.toluwalase.bookstore.model.Category;
import dev.toluwalase.bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository categoryRepository) {
		return (args) -> {
			Category fiction = categoryRepository.save(new Category("Fiction"));
			Category classic = categoryRepository.save(new Category("Classic"));
			Category fantasy = categoryRepository.save(new Category("Fantasy"));

			Book mockingbird = new Book("To Kill a Mockingbird", "Harper Lee", 1960, "978-0061120084", 12.99f);
			mockingbird.setCategory(classic);
			repository.save(mockingbird);

			Book nineteenEightyFour = new Book("1984", "George Orwell", 1949, "978-0451524935", 9.99f);
			nineteenEightyFour.setCategory(fiction);
			repository.save(nineteenEightyFour);

			Book greatGatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "978-0743273565", 10.50f);
			greatGatsby.setCategory(classic);
			repository.save(greatGatsby);

			Book prideAndPrejudice = new Book("Pride and Prejudice", "Jane Austen", 1813, "978-0141439518", 8.99f);
			prideAndPrejudice.setCategory(classic);
			repository.save(prideAndPrejudice);

			Book catcherInTheRye = new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "978-0316769488", 11.25f);
			catcherInTheRye.setCategory(fiction);
			repository.save(catcherInTheRye);

			Book hobbit = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "978-0547928227", 14.99f);
			hobbit.setCategory(fantasy);
			repository.save(hobbit);

			Book braveNewWorld = new Book("Brave New World", "Aldous Huxley", 1932, "978-0060850524", 13.50f);
			braveNewWorld.setCategory(fiction);
			repository.save(braveNewWorld);

			Book fahrenheit451 = new Book("Fahrenheit 451", "Ray Bradbury", 1953, "978-1451673319", 10.99f);
			fahrenheit451.setCategory(fiction);
			repository.save(fahrenheit451);

			Book crimeAndPunishment = new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866, "978-0140449136",
					15.00f);
			crimeAndPunishment.setCategory(classic);
			repository.save(crimeAndPunishment);

			Book alchemist = new Book("The Alchemist", "Paulo Coelho", 1988, "978-0062315007", 12.49f);
			alchemist.setCategory(fiction);
			repository.save(alchemist);
		};
	}

}
