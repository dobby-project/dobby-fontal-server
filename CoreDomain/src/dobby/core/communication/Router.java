package dobby.core.communication;

import dobby.core.Stakeholder;

/**
 * Created by gautierc on 19/01/16.
 */
public interface Router {
    void transfer(Message msg);
    void notify(Stakeholder stakeholder);
}
