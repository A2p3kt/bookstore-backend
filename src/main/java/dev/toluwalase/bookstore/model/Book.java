package dev.toluwalase.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Book {
    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;
    private String isbn;
    private Float price;

    public Book() {}


    public Book(String title, String author, Integer publicationYear, String isbn, Float price) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public Integer getPublicationYear() {
        return publicationYear;
    }


    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public Float getPrice() {
        return price;
    }


    public void setPrice(Float price) {
        this.price = price;
    }

    
    
}
