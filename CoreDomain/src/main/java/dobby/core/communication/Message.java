package dobby.core.communication;

//import javax.json.JsonObject;

import dobby.core.Repository;
import dobby.core.Stakeholder;

import java.util.Optional;

/**
 * Created by gautierc on 10/12/15.
 */
public interface Message extends RawMessage {

    /*public enum Direction {
        INCOMMING, OUTGOING, INTERNAL;
    }*/

    Stakeholder getOrigin();
    Channel getChannel();
    void setChannel(Channel c);

    /*JsonObject getContent();
    void setContent(JsonObject content);*/

}
