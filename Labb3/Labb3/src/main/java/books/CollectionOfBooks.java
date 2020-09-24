package books;

import java.util.ArrayList;
import java.util.List;

public class CollectionOfBooks<T> {
    private ArrayList<Book> theBooks;


    public CollectionOfBooks() {
        theBooks = new ArrayList<Book>();
    }

    public void addBook(Book book) throws IllegalArgumentException {
        if (book == null) {
            throw new IllegalArgumentException("Book is null, meaning the book you added is empty");
        }
        theBooks.add(book);
    }

    public Book remove(int position) {
        return theBooks.remove(position);
    }

    public List<Book> getBooksByTitle(String title) {
        List<Book> titleList = new ArrayList<>();
        boolean match = false;
        title.toLowerCase();
        for (var book : theBooks) {
            if (book.getTitle().contains(title)) {
                titleList.add(book);
            }
        }
        return null;
    }

    public List<Book> getBooksByAuthor(String author) { // search for title in theBooks arrayList
        List<Book> authorList = new ArrayList<>();
        boolean match = false;
        author.toLowerCase();
        for (var book :theBooks){
            if (book.getAuthors().contains(author))
            {
                authorList.add(book);
            }
        }
        if (match){
            return authorList;
        }
        return null;
    }

    public List<Book> getBooksByIsbn(String isbn) { // search for title in theBooks arrayList
        List<Book> isbnList = new ArrayList<>();
        boolean match = false;

        for (var book : theBooks){
            if ()
        }
        return null;
    }
}
