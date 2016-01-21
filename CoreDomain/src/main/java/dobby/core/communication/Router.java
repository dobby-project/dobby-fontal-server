package dobby.core.communication;

import dobby.core.stakeholder.Stakeholder;

/**
 * Created by gautierc on 19/01/16.
 */
public interface Router {
    void accept(Message msg);
    void notify(Stakeholder stakeholder);
    void logError(Exception e);
}
