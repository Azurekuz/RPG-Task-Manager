/**
 * Used when adding a Task obj to a tasklist (or creating one) and it is the same as one that already exists.
 */
public class DuplicateTaskException extends Exception  {
    public DuplicateTaskException(String message){
        super(message);
    }
}
