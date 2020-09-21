package books;

import java.util.ArrayList;
import java.util.List;

public class CollectionOfBooks {
    private ArrayList<Book> theBooks;
    Book book;

    public CollectionOfBooks(ArrayList<Book> theBooks, Book book) {
        this.theBooks = theBooks;
        this.book = book;
    }
    public void addBook(Book book) throws IllegalStateException{
        if (book == null)
        {
            throw new IllegalStateException();
        }
        theBooks.add(book);
    }

    public Book remove(int position){
        return theBooks.remove(position);
    }

    public List<Book> getBooksByTitle(String title){ // search for title in theBooks arrayList
        List<Book> titleList = new ArrayList<>();

        for (int i = 0;i < theBooks.size(); i++)
        {
            if(theBooks.get(i).getTitle().compareTo(title) == 0)
            {
//                titleList.add(titleList.);
            }
        }
        return titleList;
    }
}
