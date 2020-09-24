package books;

import java.util.IllegalFormatException;

public class Isbn {
    private static final String isbnPattern = "[0-9]{13}";
    private String isbnStr;

    private Isbn() {

    }

    public static Isbn createIsbn(String isbnStr) throws IllegalFormatException {

        Isbn newIsbn = new Isbn();
        String isbn = isbnPattern;

        isbn = isbn.replace("-","");
        boolean match = isbnStr.matches(isbn);

        if (!isbnStr.matches(isbn))
        {
            throw new IllegalArgumentException("illegal isbn: " + isbn);
        }
        newIsbn.isbnStr = isbn;
        return newIsbn;
    }

    public String getIsbnStr() {
        return isbnStr;
    }
}
