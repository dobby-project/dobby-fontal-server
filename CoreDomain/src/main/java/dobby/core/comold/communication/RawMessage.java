package dobby.core.comold.communication;

import dobby.core.stakeholder.Stakeholder;

import java.util.Optional;

/**
 * Created by gautierc on 16/01/16.
 */
public interface RawMessage {
    Optional<String> getField(String fieldName);
    Message resolve(Stakeholder origin);
    String getDestinationName();
}
