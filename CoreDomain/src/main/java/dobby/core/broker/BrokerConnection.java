package dobby.core.broker;

import dobby.core.comold.communication.Message;

/**
 * Created by gautierc on 10/12/15.
 */
public interface BrokerConnection {

    public Message ask(Message req);

    void startConsume();
}
