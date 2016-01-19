package dobby.core.communication;

import dobby.core.Repository;
import dobby.core.Stakeholder;

import java.util.Optional;

/**
 * Created by gautierc on 16/01/16.
 */
public interface RawMessage {
    Optional<String> getField(String fieldName);
    Message resolve(Stakeholder stakeholder);
    String getDestinationName();
}
