package books;

import java.time.LocalDate;

public class Author {
    private String name;
    private LocalDate dateOfBirth;
    Book book;

    public Author(String name, LocalDate dateOfBirth, Book book) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.book = book;
    }

}
