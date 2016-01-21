package dobby.core.comold.communication;

import dobby.core.stakeholder.Stakeholder;

/**
 * Created by gautierc on 20/01/16.
 */
public class InternalMessage extends TransferableMessage {
    public InternalMessage(Stakeholder origin, RawMessage msg) {
        super(origin, msg);
    }
}
