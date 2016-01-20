package dobby.core.communication;

import dobby.core.Stakeholder;
import dobby.core.communication.Channel;
import dobby.core.communication.Message;
import dobby.core.communication.ParsedMessage;
import dobby.core.user.User;

import java.util.Optional;

/**
 * Created by gautierc on 16/01/16.
 */
public class TransferableMessage implements Message {

    private RawMessage raw;
    private Stakeholder origin;

    public TransferableMessage(Stakeholder origin, RawMessage msg) {
        this.raw = msg;
        this.origin = origin;
    }

    @Override
    public Stakeholder getOrigin() {
        return origin;
    }

    @Override
    public String getDestinationName() {
        return raw.getDestinationName();
    }

    @Override
    public Optional<String> getField(String token) {
        return null;
    }

    @Override
    public Message resolve(Stakeholder stakeholder) {
        return this;
    }

    @Override
    public Channel getChannel() {
        return null;
    }

    @Override
    public void setChannel(Channel c) {

    }
}
