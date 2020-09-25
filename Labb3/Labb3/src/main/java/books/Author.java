package books;

import java.io.Serializable;
import java.time.LocalDate;

public class Author implements Serializable {
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
