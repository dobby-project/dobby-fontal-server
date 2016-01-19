package dobby.core.user;

import dobby.core.Stakeholder;
import dobby.core.communication.Message;
import dobby.core.communication.RawMessage;

/**
 * Created by gautierc on 10/12/15.
 */
public interface User<T> extends Stakeholder {
    void logout();
    T getSession();
    void restore(User user);
    void pending();
    long getPendingTime();
}
