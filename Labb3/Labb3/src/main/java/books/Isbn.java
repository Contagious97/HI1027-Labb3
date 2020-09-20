package books;

public class Isbn {
    private String isbnStr;
    private isbnPattern;
    private Book isbn;

    public Isbn(String isbnStr, Book isbn) {
        this.isbnStr = isbnStr;
        this.isbn = isbn;
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
