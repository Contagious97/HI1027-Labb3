package books;

public class Isbn {
    private static String isbnPattern = "[0-9]{13}";
    private String isbnStr;

    private Isbn() {
        isbnStr = new String();
    }

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



    public String getIsbnStr() {
        return isbnStr;
    }

    @Override
    public String toString() {
        return "Isbn{" +
                "isbnStr='" + isbnStr + '\'' +
                ", isbn=" +  +
                '}';
    }
}
