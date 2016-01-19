package dobby.core.user.room;

import dobby.core.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gautierc on 10/12/15.
 */
public class BasicRoom implements Room {

    private String name;
    private Room parent;
    private List<User> users;
    private List<Room> subRooms;

    public BasicRoom(Room parent, String name) {
        this.name = name;
        this.parent = parent;
        users = new ArrayList<>();
        subRooms = new ArrayList<>();
    }

    public BasicRoom() {
        this(null, "");
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public int recursiveCount() {
        return getSubRooms().stream()
                .map(Room::recursiveCount)
                .reduce(
                        count(),
                        (a, b) -> a + b
                );
    }

    @Override
    public int getLevel() {
        return isTop() ? 1 : 1 + parent.getLevel();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public List<Room> getSubRooms() {
        return subRooms;
    }


    public boolean isTop() {
        return (parent == null);
    }

    @Override
    public void addRoom(Room room) {
        subRooms.add(room);
    }

    @Override
    public void removeRoom(Room room) {
        subRooms.remove(room);
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public void moveUser(User user, Room room) {
        removeUser(user);
        room.addUser(user);
    }

    @Override
    public void destroy() {
        parent.removeRoom(this);
    }
}