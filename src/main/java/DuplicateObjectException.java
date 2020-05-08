/**
 * Used when adding an object (or creating one) and it is the same as one that already exists.
 */
public class DuplicateObjectException extends Exception  {
    public DuplicateObjectException(String message){
        super(message);
    }
}
