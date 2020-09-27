package books;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println("(1) Add book \n(2) Remove book \n(3) Search for title, " +
                    "author or ISBN\n(4) List all books\n(5) Modify a book\n(6) Quit and write all information to a file");
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
                    System.out.println(theCollection.toString());
                    break;
                case 5:
                    modifyBook();
                    break;
                case 6: quit = true;
                    readToFile();
                    break;
                default:
                    System.out.println("Faulty value, try again");
                    break;
            }
        }
    }

    public void modifyBook(){
        System.out.println("Choose a book to modify");
        printBooks(theCollection.getBooks());
        Book book = null;
        int choice;
        boolean bookChosen = false;
        do {
            choice = Integer.parseInt(scan.nextLine());
            try {
                book = theCollection.getBook(choice-1);
                bookChosen = true;
            } catch (IndexOutOfBoundsException e){
                System.out.println("Book is not in the list");
            }
        }while (bookChosen == false);

        System.out.println("Do you want to add an author (0) or change the rating of the book (1)? " +
                "Type anything else to return to the menu");
        choice = Integer.parseInt(scan.nextLine());
        if (choice == 0){
            addAuthorToBook(book);
        }
        else if(choice == 1){
            changeRatingOfBook(book);
        }
        else return;
    }

    public void addAuthorToBook(Book book){
        String author;
        System.out.println("Write the name of the author to add");
        author = scan.nextLine();
        book.addAuthor(new Author(author));
    }

    public void changeRatingOfBook(Book book){
        int rating;
        boolean ratingChanged = false;
        System.out.println("Enter a new rating");

        do {
            rating = Integer.parseInt(scan.nextLine());
            try {
                book.setRating(rating);
                ratingChanged = true;
            }
            catch (IllegalArgumentException e){
                System.out.println("Enter a valid rating (1-5)");
            }
        }while (ratingChanged == false);

    }

    public void removeBook(){
        System.out.println("Which book would you like to remove (index)? : ");
        printBooks(theCollection.getBooks());

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
        System.out.println("What do you want to search by?\n(1) title\n(2) author\n(3) ISBN\n(4) Genre\n(5) Go back");
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
        try {
            System.out.println("Books found sorted alphabetically: ");
            printBooks(theCollection.getBooksByTitle(title));
        }
        catch (IllegalStateException e){
            System.out.println("No titles found");
        }

    }

    public void getBooksByAuthor(){
        System.out.println("Enter an author");
        String author = scan.nextLine();
        List<Book> books;
        try {
            books = theCollection.getBooksByAuthor(author);
            System.out.println("Books found sorted alphabetically: ");
            printBooks(books);
        }
        catch (IllegalStateException e){
            System.out.println("No authors found");
        }

    }

    public void getBooksByIsbn(){
        System.out.println("Enter an ISBN");
        String isbn = scan.nextLine();
        List<Book> booksByIsbn;


        try {
            booksByIsbn = theCollection.getBookByIsbn(isbn);
            System.out.println("Books found: ");
            Collections.sort(booksByIsbn);
            printBooks(booksByIsbn);
        } catch (IllegalStateException e){
            System.out.println("No book found with isbn: " + isbn);
            return;
        }

    }

    public void getBooksByGenre(){
        System.out.println("Search for:\n(0)Drama \n(1)Romance\n(2)Crime\n(3)Horror\n(4)Comedy");
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

        List<Book> booksByGenre;

        try {
            booksByGenre = theCollection.getBooksByGenre(genre);
            Collections.sort(booksByGenre);
            System.out.println("Books found:");
            printBooks(booksByGenre);
        } catch (IllegalStateException e){
            System.out.println("No books found");
        }

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
