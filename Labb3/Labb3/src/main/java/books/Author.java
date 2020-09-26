package books;

import java.io.Serializable;
import java.time.LocalDate;

public class Author implements Serializable {
    private String name;
    private LocalDate dateOfBirth;

    /**
     * A author has a name and a day of birth which gets initialized
     * @param name is a user given string which contains the name of the author
     */
    public Author(String name) {
        this.name = name;
        this.dateOfBirth = LocalDate.now();
    }

    /**
     * returns the name
     * @return the name of an author in a string name
     */
    public String getName(){
        return this.name;
    }

    /**
     * returns the name and date of birth
     * @return  the name and date of birth of a person
     */
    @Override
    public String toString() {
        return name + ": " + dateOfBirth;
    }
}
