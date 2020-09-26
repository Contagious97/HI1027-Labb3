package books;

import java.util.ArrayList;
import java.util.Collections;
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

    public void remove(int position) throws IndexOutOfBoundsException{
        theBooks.remove(position);
    }

    public void remove(Book book){
        if (theBooks.contains(book)){
            theBooks.remove(book);
        }
    }

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
    public List<Book> getBooksByGenre(Genre genre){
        List<Book> booksByGenre = null;
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
    public List<Book> sortByRating(){
        List<Book> copyOfBooks = new ArrayList<>();

        copyOfBooks.addAll(theBooks);

        Collections.sort(copyOfBooks,Book::compareByRating);
        return copyOfBooks;
    }

    public void addAuthorToBook(Book book, String author){
        for (Book b: theBooks){
            if (b.equals(book)){
                b.addAuthor(new Author(author));
            }
        }
    }


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
