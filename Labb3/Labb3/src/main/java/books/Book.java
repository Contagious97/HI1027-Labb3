package books;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book<T> implements Comparable, Serializable {
        T obj;
        private final String title;
        private int rating;
        private ArrayList<Author> authors;
        private Isbn isbn;

        public Book(String title, int rating, Isbn isbn) {
            this.title = title;
            this.rating = rating;
            this.isbn = isbn;
            this.authors = new ArrayList<>();
        }

        public void addAuthor(Author author){
            authors.add(author);
        }

        public List<Author> getAuthors() {
            List<Author> copyAuthors = new ArrayList<>();

            copyAuthors.addAll(authors);
            return copyAuthors;
        }

        public String getTitle() {
            return title;
        }

//        @Override
//        public boolean equals(Object o) {
//            if (obj.equals(o)) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Book book = (Book) o;
//            return Objects.equals(title, book.title) && rating == book.rating;
//        }

        @Override
        public int compareTo(Object o) {

            return 0;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "obj=" + obj +
                    ", title='" + title + '\'' +
                    ", rating=" + rating +
                    ", authors=" + authors +
                    ", isbn=" + isbn +
                    '}';
        }
    }