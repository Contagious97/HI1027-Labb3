package books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a collection of Books. You can search a book by title, author, Isbn or rating. You can remove and add
 * a book to the collection.
 * @author Mohamed Osman and Robin Jamsahar
 */
public class CollectionOfBooks {
    private List<Book> theBooks;
    /**
     *Constructs a new (empty) collection by initializing the List of books
     */
    public CollectionOfBooks() {
        theBooks = new ArrayList<>();

    }
    /**
     *Adds a book to the collection
     * @param book the book to be added
     * @throws IllegalStateException if the book is null
     */
    public void addBook(Book book) throws IllegalStateException{
        if (book == null)
        {
            throw new IllegalStateException();
        }
        theBooks.add(book);
    }
    /**
     * Returns a reference to a book in the collection
     * @param index the index to look for
     */
    public Book getBook(int index){
        return theBooks.get(index);
    }
    /**
     *Romoves a book in the specified index
     * @param index the index of the book
     */
    public void remove(int index) throws IndexOutOfBoundsException{
        theBooks.remove(index);
    }
    /**
     *Removes a specific book from the collection
     * @param book the book to be removed
     */
    public void remove(Book book){
        if (theBooks.contains(book)){
            theBooks.remove(book);
        }
    }
    /**
     * Creates a copy of the books in the collections
     * @return a copy of the books
     */
    public List<Book> getBooks(){
        List<Book> copyOfBooks = new ArrayList<>();

        for (Book book: theBooks){
            copyOfBooks.add(new Book(book.getTitle(), book.getRating(),Isbn.createIsbn(book.getIsbn()),book.getGenre()));
            for (int i = 0; i<copyOfBooks.size(); i++){
                copyOfBooks.get(i).addAuthors(book.getAuthors());
            }
        }

        return copyOfBooks;
    }
    /**
     *Checks if any of the books' titles in the collection contain a given searchword. It's possible to
     * search for part of a string.
     * @param searchWord the title to search for
     * @throws IllegalStateException if the searchword is not found
     * @return A list of books whose titles contained the searchword
     */
    public List<Book> getBooksByTitle(String searchWord){
        List<Book> booksByTitle = new ArrayList<>();
        boolean contains = false;
        for (Book book: theBooks){
            contains = book.getTitle().indexOf(searchWord) !=-1? true: false;
            if (contains){
                booksByTitle.add(book);
            }
            else throw new IllegalStateException();
        }
        Collections.sort(booksByTitle);
        return booksByTitle;
    }
    /**
     *Checks if any of the books' Isbn corresponds to the given searchword.
     * @param searchWord the Isbn to search for
     * @return A list of books whose Isbn matches the given isbn
     */
    public List<Book> getBookByIsbn(String searchWord){
        List<Book> booksByIsbn = new ArrayList<>();
        searchWord = searchWord.replace("-","");
        for (Book book: theBooks){
            for (int i = 0; i<book.getIsbn().length(); i++){
                if (searchWord.charAt(i) == book.getIsbn().charAt(i)){
                    booksByIsbn.add(book);
                }
            }
        }
        return booksByIsbn;
    }
    /**
     *Checks if any of the books' genre in the collection corresponds to the given genre.
     * @param genre the genre to search for
     * @return A list of books whose genre matches the given genre
     */
    public List<Book> getBooksByGenre(Genre genre){
        List<Book> booksByGenre = new ArrayList<>();
        for (Book book: theBooks){
            if (book.getGenre().getValue() == genre.getValue()){
                booksByGenre.add(book);
            }
        }
        return booksByGenre;
    }
    /**
     *Creates a string containing all the books in the collection and their information
     * @return a string containing all the information
     */
    @Override
    public String toString() {
        String info = "";
        int i = 0;
        for (Book book: theBooks){
            info += "Book " + (i+1) + ": "+ book.toString();
            info+= "\n";
            i++;
        }
        return info;
    }
    /**
     *Sorts the books in the collection based on their rating
     * @return a sorted list of books
     */
    public List<Book> sortByRating(){
        List<Book> copyOfBooks = new ArrayList<>();

        copyOfBooks.addAll(theBooks);

        Collections.sort(copyOfBooks,Book::compareByRating);
        return copyOfBooks;
    }
    /**
     *Adds an author to a given book
     */
    public void addAuthorToBook(Book book, String author){
        for (Book b: theBooks){
            if (b.equals(book)){
                b.addAuthor(new Author(author));
            }
        }
    }
    /**
     *Checks if any of the books' authors in the collection contain a given searchword. It's possible to
     * search for part of a string
     * @param search the author to search for
     * @return A list of books whose authors contained the searchword
     */
    public List<Book> getBooksByAuthor(String search){
        List<Book> booksByAuthor = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        boolean contains = false;
        for (Book book: theBooks){
            authors.addAll(book.getAuthors());
            for (var bookAuthors : authors){
                contains = bookAuthors.getName().indexOf(search) !=-1? true: false;
                if (contains){
                    booksByAuthor.add(book);
                }
                else throw new IllegalStateException();
            }
            authors.clear();
        }
        Collections.sort(booksByAuthor);
        return booksByAuthor;
    }

}
