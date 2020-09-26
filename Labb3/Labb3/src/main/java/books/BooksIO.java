package books;

import java.io.*;
import java.util.List;


/**
 * This class is in charge of serializing and deserializing a List of Books
 */
public class BooksIO {

    /**
     * Call this method before the application exits, to store the books,
     * in serialized form, on file the specified file.
     * @param filename  the name of the file to serialize from
     * @param books  the List of books to read to the file
     * @throws IOException
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
     * @param filename - the file name to deserialize from
     * @throws IOException,NullPointerException
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
