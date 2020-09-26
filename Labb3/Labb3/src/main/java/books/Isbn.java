package books;

import java.io.Serializable;

public class Isbn implements Serializable {
    private static String isbnPattern = "[0-9]{13}";
    private String isbnStr;

    private Isbn() {
        isbnStr = new String();
    }

    /**
     * Constructs an Isbn by replacing "-" and checks if the given Isbn matches the isbnPattern
     * @param isbn a string containing an Isbn number
     * @return a new constructed Isbn
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
     * Returns a String containing the Isbn number
     * @return current isbn string
     */

    public String getIsbnStr() {
        return isbnStr;
    }

    @Override
    public String toString() {
        return getIsbnStr();
    }
}
