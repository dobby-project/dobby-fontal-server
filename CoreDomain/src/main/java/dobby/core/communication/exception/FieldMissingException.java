package dobby.core.communication.exception;

/**
 * Created by gautierc on 21/01/16.
 */
public class FieldMissingException extends Exception {
    public FieldMissingException() {}
    public FieldMissingException(String msg) {
        super(msg);
    }
}
