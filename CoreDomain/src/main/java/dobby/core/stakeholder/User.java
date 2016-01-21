package dobby.core.stakeholder;

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
