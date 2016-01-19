package dobby.core;

import dobby.core.communication.Message;
import dobby.core.communication.RawMessage;

/**
 * Created by gautierc on 16/01/16.
 */
public interface Stakeholder {
    String getName();
    void send(Message msg);
    Message receive(RawMessage msg);
    boolean hasQuit();

    /**
     * Return the opposed repository (User return AppRepo, App return UserRepo)
     * @return
     */
    Repository getOpposedRepository();
}
