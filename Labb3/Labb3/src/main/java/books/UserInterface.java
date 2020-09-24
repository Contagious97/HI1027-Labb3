package books;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private CollectionOfBooks theCollection;
    private ArrayList<Book> theBooks;
    private ArrayList<Author>theAuthors;

    public UserInterface(CollectionOfBooks theCollection, ArrayList<Book> theBooks, ArrayList<Author> theAuthors) {
        this.theCollection = theCollection;
        this.theBooks = theBooks;
        this.theAuthors = theAuthors;
        scan = new Scanner(System.in);
    }
    Scanner scan;

    public void menu() {
        boolean quit = false;
        int choice;
        String title;
        while(!quit)
        {
            System.out.println("(1) Add books \n(2) Remove books \n(3) Search for title, author or ISBN\n(4) List all books\n(5) Quit and write all information to a file");
            choice = scan.nextInt();

            switch (choice)
            {

                case 1: addBook();
                    break;
                case 2:
                    System.out.println("Which book would you like to remove (index) : ");
                    int index = scan.nextInt();
                    theCollection.remove(index);
                    break;
                case 3:getBookBy();
                    break;
                case 4:
                    System.out.println(toString());
                    break;
                case 5: quit = true;
                    break;
                default:
                    System.out.println("Faulty value, try again");
                    break;
            }
        }
    }
   
    public void addBook(){
        System.out.print("Authors name : ");
        String auth = scan.nextLine(); // choosing author
        Author author = new Author(auth);

        System.out.print("Choose title : ");
        String title = scan.nextLine(); // choosing title
        
        System.out.print("Add ISBN : ");
        String isbnStr = scan.nextLine(); // choosing isbn

        System.out.print("What would you rate it? : ");
        int rating = scan.nextInt(); // rating the book
        
        Book book = new Book(title,rating,Isbn.createIsbn(isbnStr));
        book.addAuthor(author); // adding an author to the book
        theCollection.addBook(book); // adding the book to the collection
    }

    public void getBookBy()
    {
        System.out.println("(1) title\n(2) author\n(3) ISBN");
        int choice = scan.nextInt();
        switch (choice)
        {
            case 1: getBookByTitle();
                break;
            case 2: getBookByAuthor();
                break;
            case 3: getBookByIsbn();
                break;
            default: throw new IllegalArgumentException("Something went wrong!");
        }
    }
    
    public void getBookByTitle(){
        String title = scan.nextLine();
        if(theCollection.getBooksByTitle(title) == null){
            return;
        }
        System.out.println(theBooks.get);
        
        
    }

    public void getBookByAuthor(){
        String author = scan.nextLine();

    }

    public void getBookByIsbn(){
        String isbn = scan.nextLine();
        
    }
//        String isbn = scan.nextLine();
////        String newIsbn = new Isbn(isbn); // hur fan ska ma
//
//        CollectionOfBooks<String> isbnList = new CollectionOfBooks<String>(isbn);
//    }
//
//    public void removeBook(){
//        int position = scan.nextInt();
//
//        theCollection.remove(position);
//    }


    public String toString(){
        StringBuilder info = new StringBuilder();

        for (var book : theBooks)
        info.append(theBooks.toString() +"\n");

        return info.toString();
    }
}
