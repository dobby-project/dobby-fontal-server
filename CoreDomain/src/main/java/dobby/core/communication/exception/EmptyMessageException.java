package dobby.core.communication.exception;

/**
 * Created by gautierc on 21/01/16.
 */
public class EmptyMessageException extends Exception {

    public EmptyMessageException() {}
    public EmptyMessageException(String msg) {
        super(msg);
    }
}
