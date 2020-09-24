package books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionOfBooks {
    private List<Book> theBooks;

    public CollectionOfBooks() {
        theBooks = new ArrayList<>();

    }
    public void addBook(Book book) throws IllegalStateException{
        if (book == null)
        {
            throw new IllegalStateException();
        }
        theBooks.add(book);


    }

    public Book getBook(int index){

        return theBooks.get(index);

    }

    public Book remove(int position){
        return theBooks.remove(position);
    }


    public List<Book> getBooks(){

        List<Book> copyOfBooks = new ArrayList<>();
        for (Book book: theBooks){
            copyOfBooks.add(new Book(book.getTitle(),book.getRating(),book.getAuthors()));
        }

        return copyOfBooks;
    }

    public List<Book> getBooksByTitle(String searchWord){
        List<Book> booksByTitle = new ArrayList<>();
        searchWord = searchWord.toLowerCase();
        String bookTitle;
        for (Book book: theBooks){
            bookTitle = book.getTitle().toLowerCase();
            if (bookTitle.contains(searchWord)){
                booksByTitle.add(book);
            }
        }
        Collections.sort(booksByTitle);
        return booksByTitle;
    }

    @Override
    public String toString() {
        String info = new String();
        int i = 0;
        for (Book book: theBooks){
            info += "Book " + (i+1) + ": " + book.getTitle() + " Authors: " + book.getAuthors().toString() + " Rating: " + book.getRating();
            info+= "\n";
            i++;
        }
        return info;
    }
    public List<Book> sortByRating(){
        List<Book> copyOfBooks = new ArrayList<>();

        copyOfBooks.addAll(theBooks);

        Collections.sort(copyOfBooks,Book::compareByRating);
        return copyOfBooks;
    }

    public List<Book> getBooksByAuthor(String search){
        List<Book> booksByAuthor = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        for (Book book: theBooks){
            authors.addAll(book.getAuthors());
            for (int i = 0; i<book.getAuthors().size();i++){
                if (authors.get(i).getName().contains(search)){
                    booksByAuthor.add(book);
                }
            }
            authors.clear();
        }
        Collections.sort(booksByAuthor);
        return booksByAuthor;
    }

}
