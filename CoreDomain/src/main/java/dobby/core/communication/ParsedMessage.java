package dobby.core.communication;

import dobby.core.Repository;
import dobby.core.Stakeholder;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by gautierc on 16/01/16.
 */
public class ParsedMessage implements RawMessage {

    private String destName;
    private HashMap<String, String> fields;

    private ParsedMessage(String destName, HashMap<String, String> fields) {
        this.destName = destName;
        this.fields = fields;
    }

    @Override
    public Optional<String> getField(String fieldName) {
        return Optional.ofNullable(fields.get(fieldName));
    }

    @Override
    public Message resolve(Stakeholder stakeholder) {
        return stakeholder.receive(this);
    }

    @Override
    public String getDestinationName() {
        return destName;
    }

    public static class Factory {
        public static ParsedMessage parseFromUser(String str) {
            return parse(str);
        }

        public static ParsedMessage parseFromApp(String str) {
            return parse(str);
        }

        private static ParsedMessage parse(String str) {
            HashMap<String, String> fields = new HashMap<>();
            return new ParsedMessage("", fields);
        }
    }
}
