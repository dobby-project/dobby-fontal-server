package dobby.core.communication;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

import dobby.core.communication.exception.EmptyMessageException;
import dobby.core.communication.exception.FieldMissingException;
import dobby.core.communication.exception.UnreachableDestinationException;
import dobby.core.stakeholder.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by gautierc on 20/01/16.
 */
public class Message {

    private final Stakeholder origin;
    private final Stakeholder dest;
    private final String rawMsg;
    private final Map<String, String> fields;

    private Message(Stakeholder origin, Stakeholder dest, String rawMsg, Map<String, String> fields) {
        this.origin = origin;
        this.dest = dest;
        this.rawMsg = rawMsg;
        this.fields = fields;
    }

    public Stakeholder getOrigin() {
        return origin;
    }

    public Stakeholder getDestination() {
        return dest;
    }

    public Optional<String> getField(String fieldName) {
        return Optional.ofNullable(fields.get(fieldName));
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public String getRawContent() {
        return rawMsg;
    }


    public static class Builder {
        public static Message parse(Stakeholder origin, String str) throws Exception {
            Optional<Map<String, String>> json = toJson(str);
            if (!json.isPresent())
                throw new EmptyMessageException("Message can\'t be read or contains nothing.");

            if (!json.get().containsKey("to"))
                throw new FieldMissingException("Unable to find the field: to.");

            Optional<Stakeholder> dest = origin.resolveDest(json.get().get("to"));
            if (!dest.isPresent())
                throw new UnreachableDestinationException("Destination can't be resolved during message parsing.");

            return new Message(origin, dest.get(), str, json.get());
        }

        public static Optional<String> parseToken(String str) {
            Optional<Map<String, String>> json = toJson(str);
            return Optional.ofNullable(json.get().get("token"));
        }

        private static Optional<Map<String, String>> toJson(String json) {
            Map<String, String> fields = null;

            JsonFactory factory = new JsonFactory();
            //factory.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            ObjectMapper om = new ObjectMapper(factory);
            MapType type = om.getTypeFactory().constructMapType(HashMap.class, String.class, String.class);

            try {
                JsonParser jp = factory.createParser(json);
                fields = om.readValue(jp, type);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return Optional.ofNullable(fields);
        }
    }
}
