package dobby.core.user;


import dobby.core.Repository;

import java.util.Optional;

/**
 * Created by gautierc on 10/12/15.
 */
public interface UserRepository extends Repository {
    void accept(User user);
    void close(User user);
    Optional<User> getUser(Object session);
}
