package dobby.core.room;

import dobby.core.stakeholder.User;

import java.util.List;

/**
 * Created by gautierc on 10/12/15.
 */
public interface Room {

    int count();
    int recursiveCount();

    /**
     * 3 levels, MAIN / GAME / INSTANCE
      * @return
     */
    int getLevel();
    String getName();
    List<User> getUsers();
    List<Room> getSubRooms();

    boolean isTop();

    void addRoom(Room room);
    void removeRoom(Room room);

    void addUser(User user);
    void removeUser(User user);

    void moveUser(User user, Room room);

    void destroy();
}
