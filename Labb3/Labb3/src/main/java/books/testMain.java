package books;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class testMain {
    public static void main(String[] args) {
        CollectionOfBooks theBooks = new CollectionOfBooks();
        Book book1 = new Book("Momo",5,null);
        Book book2 = new Book("Robin",1,null);
        Book book3 = new Book("Arvin",2,null);
        book1.addAuthor(new Author("Mohamed Osman"));
        book2.addAuthor(new Author("Robin Jamsahar"));
        book3.addAuthor(new Author("Ahmed "));

        theBooks.addBook(book1);
        theBooks.addBook(book2);
        theBooks.addBook(book3);
        printBooks(theBooks.getBooks());
        List<Book> booksByAuthor;
        List<Book> booksByTitle;
        booksByTitle = theBooks.getBooksByTitle("in");
        booksByAuthor = theBooks.getBooksByAuthor("in");
        //Collections.sort(theBooks.getBooks(),Book::compareByRating);
        //System.out.println(theBooks.sortByRating().toString());
        printBooks(booksByAuthor);
    }

    public static void printBooks(List<Book> theBooks){
        String info = new String();
        int i = 0;
        for (Book book: theBooks){
            info += "Book " + (i+1) + ": " + book.getTitle() + " Authors: " + book.getAuthors().toString() + " Rating: " + book.getRating();
            info+= "\n";
            i++;
        }
        System.out.println(info);
    }


}
