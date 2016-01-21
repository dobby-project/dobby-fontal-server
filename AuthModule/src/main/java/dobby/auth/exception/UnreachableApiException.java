package dobby.auth.exception;

/**
 * Created by gautierc on 21/01/16.
 */
public class UnreachableApiException extends Exception {
    public UnreachableApiException() {}
    public UnreachableApiException(String msg) {
        super(msg);
    }
}
