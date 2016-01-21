package dobby.core.stakeholder;

import dobby.core.Repository;
import dobby.core.comold.communication.InternalMessage;
import dobby.core.comold.communication.Message;
import dobby.core.comold.communication.RawMessage;

import java.util.Map;
import java.util.Optional;

/**
 * Created by gautierc on 16/01/16.
 */
public interface Stakeholder {
    String getName();
    void send(Message msg);
    Message handleMessage(RawMessage msg);
    InternalMessage handleInternalMessage(RawMessage msg);
    boolean hasQuit();

    /**
     * Return the opposed repository (User return AppRepo, App return UserRepo)
     * @return
     */
    Repository getOpposedRepository();

    Optional<Stakeholder> resolveDest(String name);
}
