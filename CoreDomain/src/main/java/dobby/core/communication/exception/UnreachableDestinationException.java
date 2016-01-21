package dobby.core.communication.exception;

/**
 * Created by gautierc on 21/01/16.
 */
public class UnreachableDestinationException extends Exception {
    public UnreachableDestinationException() {}
    public UnreachableDestinationException(String msg) {
        super(msg);
    }
}
