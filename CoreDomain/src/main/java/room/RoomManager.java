package room;

import dobby.core.exceptions.CannotDestroyMainRoom;
import dobby.core.user.User;
import dobby.core.exceptions.RoomAndSubRoomsNotEmptyException;

import java.util.Optional;

/**
 * Created by gautierc on 10/12/15.
 */
public class RoomManager {

    private static final BasicRoom mainRoom = new BasicRoom();

    public RoomManager() {}

    public Room getMainRoom() {
        return mainRoom;
    }

    public void addUser(Room room, User user) {
        room.addUser(user);
    }

    public void removeUser(Room room, User user) {
        room.removeUser(user);
    }

    public Room createSubRoom(Room root, String name) {
        Optional<Room> existingRoom = root.getSubRooms().stream()
                .filter(r -> r.getName().equals(name))
                .findFirst();

        if (existingRoom.isPresent())
            return existingRoom.get();

        Room room = new BasicRoom(root, name);
        root.addRoom(room);
        return room;
    }

    public void removeRoom(Room room) throws RoomAndSubRoomsNotEmptyException, CannotDestroyMainRoom {
        if (room.recursiveCount() > 0)
            throw new RoomAndSubRoomsNotEmptyException();

        if (room.isTop())
            throw new CannotDestroyMainRoom();

        room.destroy();
    }

    public int countUsers() {
        return mainRoom.recursiveCount();
    }
}
