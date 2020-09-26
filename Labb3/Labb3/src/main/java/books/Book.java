package books;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.*;
import java.util.Comparator;

public class Book implements Comparable<Book>, Serializable {
        private String title;
        private int rating;
        private ArrayList<Author> authors;
        private Isbn isbn;
        private Genre genre;
        public Book(String title, int rating, Isbn isbn, Genre genre) {
            this.title = title;
            this.rating = rating;
            this.authors = new ArrayList<>();
            this.isbn = isbn;
            this.genre = genre;
        }

        public void addAuthor(Author author){
            authors.add(author);
        }

        public void addAuthors(List<Author> authors){
            this.authors.addAll(authors);
        }
        public List<Author> getAuthors() {
            List<Author> copyAuthors = new ArrayList<>();
            for (Author author: authors){
                copyAuthors.add(new Author(author.getName()));
            }
            return copyAuthors;
        }

        public int getRating(){
            return rating;
        }

        public void setRating(int rating) throws IllegalArgumentException{
            if (rating<0 || rating >5){
                throw new IllegalArgumentException();
            }
            else this.rating = rating;

        }

        public String getIsbn(){
            return isbn.getIsbnStr();
        }

    public Genre getGenre() {
        return genre;
    }

    @Override
        public int compareTo(Book book){
            return title.compareTo(book.getTitle());
        }

        public int compareByRating(Book book){
            return book.rating-rating;
        }

        @Override
        public String toString() {
            return "Title: " + title + " Authors:" + authors.toString() + " Rating:" + rating + " Isbn:" + isbn.toString() + " Genre: " + genre.toString();
        }

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