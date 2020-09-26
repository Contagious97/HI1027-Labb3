package books;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.*;
import java.util.Comparator;

/**
 * Represents a book in a library
 * A book can vary alot depending on the author, title, ISBN and ratings
 */
public class Book implements Comparable<Book>, Serializable {
        private String title;
        private int rating;
        private ArrayList<Author> authors;
        private Isbn isbn;
        private Genre genre;
    /**
     * Constructor Initialize the title, rating, isbn and a new list of authors
     * @param title this is the given title to a book
     * @param rating this is also given to the book by the user when the constructor is called
     * @param isbn  is sent from the user to a book.
     */
        public Book(String title, int rating, Isbn isbn, Genre genre) {
            this.title = title;
            this.rating = rating;
            this.authors = new ArrayList<>();
            this.isbn = isbn;
            this.genre = genre;
        }
    /**
     * addAuthor gets a author as a parameter which contains the authors name and date of birth
     * @param author is chosen by the user
     */
        public void addAuthor(Author author){
            authors.add(author);
        }

    /**
     * addAuthors adds a list of authors to the authors list.
     * @param authors a list of authors containing day of birth and name
     */
        public void addAuthors(List<Author> authors){
            this.authors.addAll(authors);
        }
    /**
     * getAuthors makes a copy of the list authors and returns it
     * This is made for access purposes in the userInterface
     * @return a copy of all the authors
     */
        public List<Author> getAuthors() {
            List<Author> copyAuthors = new ArrayList<>();
            for (Author author: authors){
                copyAuthors.add(new Author(author.getName()));
            }
            return copyAuthors;
        }
    /**
     * Simple getter method returns rating.
     * @return rating from a book
     */
        public int getRating(){
            return rating;
        }

    /**
     * sets a chosen rating for a book
     * @param rating is an int value sent from the user
     * @throws IllegalArgumentException gets used when out of the legal bounds 1-5
     */
        public void setRating(int rating) throws IllegalArgumentException{
            if (rating<0 || rating >5){
                throw new IllegalArgumentException();
            }
            else this.rating = rating;

        }
    /**
     * Getter method returns the ISBN from a book
     * @return isbn
     */
        public String getIsbn(){
            return isbn.getIsbnStr();
        }

    /**
     * getGenre returns a genre
     * @return genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * compares a title from a book to a title of a user given book
     * @param book is a chosen book by the user
     * @return 0 if the title comparison is the same, else returns a positive or negative value
     */
    @Override
        public int compareTo(Book book){
            return title.compareTo(book.getTitle());
        }

    /**
     *
     * @param book is a chosen book containing ratings,isbn,title,genre and a list of authors
     * @return
     */
        public int compareByRating(Book book){
            return book.rating - rating;
        }

    /**
     * When called, returns a title,author name,day of birth,isbn and genre.
     * @return title,authors names and day of birth, ratings,genres
     */
        @Override
        public String toString() {
            return "Title: " + title + " Authors:" + authors.toString() + " Rating:" + rating + " Isbn:" + isbn.toString() + " Genre: " + genre.toString();
        }

    /**
     *  Returns a string title
     * @return a title
     */
        public String getTitle() {
            return title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return Objects.equals(title, book.title) &&
                    (rating == book.rating);
        }
    }