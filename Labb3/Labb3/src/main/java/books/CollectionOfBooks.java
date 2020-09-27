package books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionOfBooks {
    private List<Book> theBooks;

    /**
     * Constructor initiate a ArrayList called theBooks
     */
    public CollectionOfBooks() {
        theBooks = new ArrayList<>();
    }

    /**
     * Adds a book to a list of books(theBooks)
     * @param book is a book chosen by the user
     * @throws IllegalStateException if the book which is sent to the method is empty
     */
    public void addBook(Book book) throws IllegalStateException{
        if (book == null)
        {
            throw new IllegalStateException();
        }
        theBooks.add(book);
    }

    /**
     * Gets a book on a given index inside the list of books(theBooks)
     * @param index is the slot in the theBooks ArrayList which the user wants to access
     * @return the book on the given index
     */
    public Book getBook(int index){
        return theBooks.get(index);
    }

    /**
     * Removes a book on the given position from theBooks ArrayList
     * @param position is an int sent from the user which indicates what element to remove
     * @throws IndexOutOfBoundsException throws a exception when the given position does not exist
     */
    public void remove(int position) throws IndexOutOfBoundsException{
        theBooks.remove(position);
    }

    /**
     * removes a book in the theBooks arrayList if there is a identical book to the one the given in the method
     * @param book is used to check if a following book exist in the ArrayList
     */
    public void remove(Book book){
        if (theBooks.contains(book)){
            theBooks.remove(book);
        }
    }

    /**
     * Gets all the books in a copied ArrayList
     * @return a copy of theBooks
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
     * Sort the books by title
     * @param searchWord is the string/substring which we want to sort the books by
     * @return a copied ArrayList sorted by the order of the string/substring
     */
    public List<Book> getBooksByTitle(String searchWord){
        List<Book> booksByTitle = null;
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

    /**
     * Sorts the books by ISBN
     * @param searchWord is the isbn string/substring which the ArrayList gets sorted by
     * @return
     */
    public List<Book> getBookByIsbn(String searchWord){
        List<Book> booksByIsbn = null;
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
     * Sorts a list of books by genre and returns it
     * @return a copy of books in an arrayList containing the same genres as the user sent
     */
    public List<Book> getBooksByGenre(Genre genre){
        List<Book> booksByGenre = new ArrayList<>(); // jag ändrade från null till new ArrayList<>
        for (Book book: theBooks){
            if (book.getGenre().getValue() == genre.getValue()){
                booksByGenre.add(book);
            }
        }
        return booksByGenre;
    }

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
     * Sorts a list of books by rating
     * @return a sorted copy of a collection of books in a ArrayList
     */
    public List<Book> sortByRating(){
        List<Book> copyOfBooks = new ArrayList<>();

        copyOfBooks.addAll(theBooks);

        Collections.sort(copyOfBooks,Book::compareByRating);
        return copyOfBooks;
    }

    /**
     * adding author to a book if it exist
     * @param book is a specified book sent by the user
     * @param author is the name of the author we want to add
     */
    public void addAuthorToBook(Book book, String author){
        for (Book b: theBooks){
            if (b.equals(book)){
                b.addAuthor(new Author(author));
            }
        }
    }

    /**
     * Searching for a book from a collection of books
     * @param search is the string which the user uses to search for a specified author
     * @return the book by the specified author
     */
    public List<Book> getBooksByAuthor(String search){
        List<Book> booksByAuthor = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        for (Book book: theBooks){
            authors.addAll(book.getAuthors());
            for (var bookAuthors : authors){
                if (bookAuthors.getName().toLowerCase().contains(search.toLowerCase())){
                    booksByAuthor.add(book);
                }
            }
            authors.clear();
        }
        Collections.sort(booksByAuthor);
        return booksByAuthor;
    }

}
