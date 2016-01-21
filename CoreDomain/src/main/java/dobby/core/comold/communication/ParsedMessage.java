package dobby.core.comold.communication;

import dobby.core.stakeholder.Stakeholder;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by gautierc on 16/01/16.
 */
public class ParsedMessage implements RawMessage {

    private boolean forInternal;
    private String destName;
    private HashMap<String, String> fields;

    private ParsedMessage(boolean forInternal, String destName, HashMap<String, String> fields) {
        this.forInternal = forInternal;
        this.destName = destName;
        this.fields = fields;
    }

    @Override
    public Optional<String> getField(String fieldName) {
        return Optional.ofNullable(fields.get(fieldName));
    }

    @Override
    public Message resolve(Stakeholder origin) {
        if (forInternal)
            origin.handleInternalMessage(this);

        return origin.handleMessage(this);
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
            return new ParsedMessage(false, "", fields);
        }
    }
}
