package dobby.core.exceptions;

/**
 * Created by gautierc on 14/12/15.
 */
public class RoomAndSubRoomsNotEmptyException extends Throwable {
    public RoomAndSubRoomsNotEmptyException() {}

    public RoomAndSubRoomsNotEmptyException(String message)
    {
        super(message);
    }
}