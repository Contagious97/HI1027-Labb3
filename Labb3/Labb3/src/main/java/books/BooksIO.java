package books;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class BooksIO {

    /**
     * Call this method before the application exits, to store the books,
     * in serialized form, on file the specified file.
     */
    public void serializeToFile(String filename, List<Book> books) throws IOException {

        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(
                    new FileOutputStream(filename));
            out.writeObject(books);
        }
        catch (Exception IOException){
            System.out.println("Error reading from file");
        }
        finally {
            try {
                if(out != null)	out.close();
            } catch(Exception e) {
                System.out.println("An error occured");;
            }
        }
    }

    /**
     * Call this method at startup of the application, to deserialize the books
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public List<Book> deSerializeFromFile(String filename) throws IOException, ClassNotFoundException, NullPointerException {

        ObjectInputStream in = null;
        File file = new File(filename);
        if (!file.createNewFile()){
            System.out.println("File already exists");
        }
        else System.out.println("Creating new file...");

        try {
            in = new ObjectInputStream(
                    new FileInputStream(file));

            // readObject returns a reference of type Object, hence the down-cast
            List<Book> books = (List<Book>) in.readObject();
            return books;
        }
        catch (Exception exception){

            System.out.println("Error reading from file");
        }

        finally {
            try {
                if(in != null)	in.close();
            } catch(NullPointerException e) {
                return null;
            }
        }
        return null;
    }

}
