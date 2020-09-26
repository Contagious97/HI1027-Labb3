package books;

import java.io.Serializable;

public class Isbn implements Serializable {
    private static String isbnPattern = "[0-9]{13}";
    private String isbnStr;

    private Isbn() {
        isbnStr = new String();
    }

    /**
     * creates a valid isbn by replacing "-" and checks if the given isbn matches the isbnPattern
     * @param isbn
     * @return theIsbn which is now a valid pattern to return
     * @throws IllegalStateException if the pattern do not match the given isbn
     */
    public static Isbn createIsbn(String isbn)throws IllegalStateException{
        Isbn theIsbn = new Isbn();
        isbn = isbn.replace("-","");
        boolean match = isbn.matches(isbnPattern);
        if (!match)
        {
            throw new IllegalArgumentException("illegal Isbn: " + isbn);
        }
        theIsbn.isbnStr = isbn;
        return theIsbn;
    }

    /**
     *
     * @return current isbn string (but in numbers)
     */

    public String getIsbnStr() {
        return isbnStr;
    }

    @Override
    public String toString() {
        return getIsbnStr();
    }
}
