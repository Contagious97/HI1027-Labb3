package books;

public class Isbn {
    private static String isbnPattern;
    private String isbnStr;
    private Book isbn;

    public Isbn(String isbnStr, Book isbn) {
        this.isbnStr = isbnStr;
        this.isbn = isbn;
    }

    public static Isbn isbn()throws IllegalStateException{
        String isbn = isbn().getIsbnStr();
        isbn = isbn.replace("-","");
        boolean match = isbn.matches("[0-9]{13}");

        if (!isbn.matches(isbnPattern))
        {
            throw new IllegalArgumentException("illegal pnr: " + isbn().isbnStr);
        }
        isbnPattern = isbn().isbnStr;
        return isbnPattern;
    }



    public Isbn createIsbn(String isbnStr){
        var str = getIsbnStr();
        isbnStr = str;
        return Isbn.this;
    }

    public String getIsbnStr() {
        return isbnStr;
    }

    @Override
    public String toString() {
        return "Isbn{" +
                "isbnStr='" + isbnStr + '\'' +
                ", isbn=" + isbn +
                '}';
    }
}
