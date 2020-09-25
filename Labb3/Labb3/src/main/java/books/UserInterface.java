package books;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private CollectionOfBooks theCollection;
    private Scanner scan;

    public UserInterface() {
        this.theCollection = new CollectionOfBooks();
        scan = new Scanner(System.in);
    }

    public void readFromFile() throws IOException, ClassNotFoundException {
        BooksIO booksIO = new BooksIO();
        List<Book> theBooks = booksIO.deSerializeFromFile("CollectionOfBooks.ser");
        if (theBooks != null){
            for (Book book: theBooks){
                theCollection.addBook(book);
            }
        }
    }
    public void readToFile() throws IOException {
        BooksIO booksIO = new BooksIO();
        booksIO.serializeToFile("CollectionOfBooks.ser",theCollection.getBooks());
    }


    public void menu() throws IOException, ClassNotFoundException {
        boolean quit = false;
        int choice;
        String title;
        readFromFile();
        while(!quit)
        {
            System.out.println("(1) Add books \n(2) Remove books \n(3) Search for title, author or ISBN\n(4) List all books\n(5) Quit and write all information to a file");
            choice = Integer.parseInt(scan.nextLine());
            switch (choice)
            {
                case 1: addBook();
                    break;
                case 2:
                    System.out.println("Which book would you like to remove (index) : ");
                    int index = Integer.parseInt(scan.nextLine());
                    theCollection.remove(index);
                    break;
                case 3:getBookBy();
                    break;
                case 4:
                    System.out.println(theCollection.toString());
                    break;
                case 5: quit = true;
                    readToFile();
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
        String isbnStr;
        Isbn isbn = null;
        do {
            System.out.print("Add ISBN : ");
            isbnStr = scan.nextLine(); // choosing isbn
            try {
                isbn = Isbn.createIsbn(isbnStr);
            }catch (IllegalArgumentException e){
                System.out.println("Enter a valid isbn");
            }
        }while (isbn == null);

        int rating;
        do {
            System.out.print("What would you rate it? (1-5): ");
            rating = Integer.parseInt(scan.nextLine()); // rating the book
            if (rating<0 || rating > 5){
                System.out.println("Enter a valid rating");
            }
        }while (rating<0 || rating > 5);


        Book book = new Book(title,rating,isbn);
        book.addAuthor(author); // adding an author to the book
        theCollection.addBook(book); // adding the book to the collection
    }

    public void getBookBy()
    {
        System.out.println("What do you want to search by? (1) title\n(2) author\n(3) ISBN\n (4) Go back");
        int choice = Integer.parseInt(scan.nextLine());
        switch (choice)
        {
            case 1: getBookByTitle();
                break;
            case 2: getBookByAuthor();
                break;
            case 3: getBookByIsbn();
                break;
            case 4: return;
            default: throw new IllegalArgumentException("Something went wrong!");
        }
    }

    public void getBookByTitle(){
        String title = scan.nextLine();
        if(theCollection.getBooksByTitle(title) == null){
            System.out.println("No titles found!");
            return;
        }

    }

    public void getBookByAuthor(){
        String author = scan.nextLine();
        if (theCollection.getBooksByAuthor(author) == null){
            System.out.println("No authors found!");
            return;
        }
    }

    public void getBookByIsbn(){
        String isbn = scan.nextLine();
        if(theCollection.getBookByIsbn(isbn) == null){
            System.out.println("No book found with isbn: " + isbn);
            return;
        }
    }

    public String printBooks(List<Book> theBooks){
        StringBuilder info = new StringBuilder();

        for (var book : theBooks)
        info.append(theBooks.toString() +"\n");

        return info.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserInterface ui = new UserInterface();
        ui.menu();

    }
}
