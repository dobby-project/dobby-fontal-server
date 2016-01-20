package dobby.core.exceptions;

/**
 * Created by gautierc on 14/12/15.
 */
public class CannotDestroyMainRoom extends Throwable {
    public CannotDestroyMainRoom() {}

    public CannotDestroyMainRoom(String message)
    {
        super(message);
    }
}
