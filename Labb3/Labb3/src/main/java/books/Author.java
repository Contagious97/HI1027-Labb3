package books;

import java.time.LocalDate;

public class Author {
    private String name;
    private LocalDate dateOfBirth;

    public Author(String name) {
        this.name = name;
        this.dateOfBirth = LocalDate.now();
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return name + ": " + dateOfBirth;
    }
}
