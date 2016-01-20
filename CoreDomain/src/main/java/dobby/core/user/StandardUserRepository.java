package dobby.core.user;

import dobby.core.Stakeholder;

import java.util.*;

/**
 * Created by gautierc on 13/12/15.
 */
public class StandardUserRepository implements UserRepository {

    private final Map<Object, User> entries = Collections.synchronizedMap(new HashMap<Object, User>());
    private final List<User> pending = Collections.synchronizedList(new ArrayList<User>());
    private static final int PENDING_MINUTES = 2;
    private Thread garbageCollection;

    private StandardUserRepository() {
        garbageCollection = new Thread() {
            public void run() {
                while (true)
                {
                    // If pending list is empty, waiting...
                    if (pending.size() == 0)
                    {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Freshly notified (or new iteration), we wait 1s before doing anything
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Looking for some old user to remove
                    long timeLimit = new Date(new Date().getTime() - (PENDING_MINUTES * 60000)).getTime();
                    pending.stream().filter(user -> user.getPendingTime() < timeLimit).forEach(user -> {
                        user.logout();
                        pending.remove(user);
                    });
                }
            }
        };
        garbageCollection.start();
    }

    @Override
    public void accept(User user) {
        // We will try to restore the previous User (if we had one) => only one connection to the front (depend on token implem)!!
        Optional<User> pendingUser = getPendingUser(user);
        if (pendingUser.isPresent()) {
            pending.remove(pendingUser.get());
            pendingUser.get().restore(user);
            user = pendingUser.get();
        }

        entries.put(user.getSession(), user);
    }

    @Override
    public void close(User user) {
        entries.remove(user.getSession());

        if (!user.hasQuit())
        {
            user.pending();
            pending.add(user);
            garbageCollection.notify();
        }
    }

    private Optional<User> getPendingUser(User newUser) {
        return pending.stream().filter(u -> u.equals(newUser)).findFirst();
    }

    public Optional<User> getUser(Object session) {
        return Optional.ofNullable(entries.get(session));
    }

    @Override
    public Optional getItemByName(String name) {
        Optional<User> item;

        item = entries.values().parallelStream().filter(u -> u.getName() == name).findFirst();
        if (!item.isPresent())
            item = pending.stream().filter(u -> u.getName() == name).findFirst();

        return item;
    }

    private static class UserRepositoryHolder {
        private final static StandardUserRepository instance = new StandardUserRepository();
    }

    public static StandardUserRepository getInstance() {
        return UserRepositoryHolder.instance;
    }
}