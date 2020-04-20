/**
 * For use with location-based commands in RPGUI or RPGManager when a location does not exist (based on given name/info)
 */
public class NonExistentLocationException extends Exception {
    public NonExistentLocationException(String message){
        super(message);
    }
}
