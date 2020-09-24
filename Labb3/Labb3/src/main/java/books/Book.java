package books;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.*;
import java.util.Comparator;

public class Book<T> implements Comparable<Book>, Serializable {
        private String title;
        private int rating;
        private ArrayList<Author> authors;
        private Isbn isbn;
        public T type;
        public Book(String title, int rating, List<Author> authors) {
            this.title = title;
            this.rating = rating;
            this.authors = new ArrayList<>();
            if (authors != null){
                authors.addAll(authors);
            }
        }

        public void addAuthor(Author author){
            authors.add(author);
        }

        public List<Author> getAuthors() {
            List<Author> copyAuthors = new ArrayList<>();

            copyAuthors.addAll(authors);
            return copyAuthors;
        }

        public int getRating(){
            return rating;
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
            return "Book{" +
                    "title='" + title + '\'' +
                    ", rating=" + rating +
                    ", authors=" + authors.toString() +
                    ", isbn=" + isbn +
                    '}';
        }

        public String getTitle() {
            return title;
        }



//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Book book = (Book) o;
//            return Objects.equals(title, book.title) &&
//                    Arrays.equals(rating, book.rating);
//        }
    }