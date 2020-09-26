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
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserInterface ui = new UserInterface();
        ui.menu();
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
        readFromFile();
        while(!quit)
        {
            System.out.println("(1) Add book \n(2) Remove book \n(3) Search for title, author or ISBN\n(4) List all books\n(5) Quit and write all information to a file");
            choice = Integer.parseInt(scan.nextLine());
            switch (choice)
            {
                case 1: addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:getsBookBy();
                    break;
                case 4:
                    printBooks(theCollection.getBooks());
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

    public void removeBook(){
        System.out.println("Which book would you like to remove (index)? : ");
        System.out.println(theCollection.toString());

        boolean removed = false;
        while (!removed){
            int index = Integer.parseInt(scan.nextLine());
            try {
                theCollection.remove(index-1);
                removed = true;
            } catch (IndexOutOfBoundsException e){
                System.out.println("Enter a valid index");
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

        Genre genre = null;
        System.out.println("What genre is the book? (0) Drama (1) Romance (2) Crime (3) Horror (4) Comedy");
        int theGenre;
        do {
            theGenre = Integer.parseInt(scan.nextLine());
            if (theGenre == 0){
                genre = Genre.DRAMA;
            }
            else if (theGenre == 1){
                genre = Genre.ROMANCE;
            }
            else if (theGenre == 2){
                genre = Genre.CRIME;
            }
            else if (theGenre == 3){
                genre = Genre.HORROR;
            }
            else if (theGenre == 4){
                genre = Genre.COMEDY;
            }
            else System.out.println("Enter a valid value");
        } while (genre==null);

        Book book = new Book(title,rating,isbn, genre);
        book.addAuthor(author); // adding an author to the book
        theCollection.addBook(book); // adding the book to the collection
    }

    public void getsBookBy()
    {
        System.out.println("What do you want to search by? (1) title\n(2) author\n(3) ISBN\n(4) Genre\n(5) Go back");
        int choice = Integer.parseInt(scan.nextLine());
        switch (choice)
        {
            case 1: getBooksByTitle();
                break;
            case 2: getBooksByAuthor();
                break;
            case 3: getBooksByIsbn();
                break;
            case 4: getBooksByGenre();
                break;
            case 5: return;
            default:
                System.out.println("Enter a valid value");;
        }
    }

    public void getBooksByTitle(){
        System.out.println("Enter a title:");
        String title = scan.nextLine();
        if(theCollection.getBooksByTitle(title) == null){
            System.out.println("No titles found!");
            return;
        }
        else printBooks(theCollection.getBooksByTitle(title));
    }

    public void getBooksByAuthor(){
        System.out.println("Enter an author");
        String author = scan.nextLine();
        if (theCollection.getBooksByAuthor(author) == null){
            System.out.println("No authors found!");
            return;
        }
        else printBooks(theCollection.getBooksByAuthor(author));
    }

    public void getBooksByIsbn(){
        System.out.println("Enter an ISBN");
        String isbn = scan.nextLine();
        if(theCollection.getBookByIsbn(isbn) == null){
            System.out.println("No book found with isbn: " + isbn);
            return;
        }
        else printBooks(theCollection.getBookByIsbn(isbn));
    }

    public void getBooksByGenre(){
        System.out.println("Enter a genre");
        int choice = 0;
        Genre genre = null;
        do {
            choice = Integer.parseInt(scan.nextLine());
            switch (choice){
                case 0:
                    genre = Genre.DRAMA;
                    break;
                case 1:
                    genre = Genre.ROMANCE;
                    break;
                case 2:
                    genre = Genre.CRIME;
                    break;
                case 3:
                    genre = Genre.HORROR;
                    break;
                case 4:
                    genre = Genre.COMEDY;
                    break;
                default:
                    System.out.println("Enter a valid value");
            }
        } while (genre == null);
        printBooks(theCollection.getBooksByGenre(genre));
    }

    public void printBooks(List<Book> theBooks){
        String info = "";
        int i = 0;
        for (Book book: theBooks){
            info += "Book " + (i+1) + ": "+ book.toString();
            info+= "\n";
            i++;
        }
        System.out.println(info);
    }

}
