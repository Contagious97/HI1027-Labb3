package books;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

    public class Book extends Object implements Comparable {
        private String title;
        private int[] rating = new int[5];
        private Author author;
        private ArrayList<Author> authors;

        public Book(String title, int[] rating, Author author) {
            this.title = title;
            this.rating = rating;
            this.author = author;
        }
        public void addAuthor(Author author){
            authors.add(author);
        }

        public List<Author> getAuthor() {
            List<Author> copyAuthors = new ArrayList<>();

            copyAuthors.addAll(authors);
            return copyAuthors;

        }
        @Override
        public int compareTo(Object o){
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return Objects.equals(title, book.title) &&
                    Arrays.equals(rating, book.rating);
        }
    } 