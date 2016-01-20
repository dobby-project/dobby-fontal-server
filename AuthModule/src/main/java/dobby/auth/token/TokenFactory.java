package dobby.auth.token;

import dobby.auth.Kind;

import java.util.Map;
import java.util.Optional;

/**
 * Created by gautierc on 19/12/15.
 */
public class TokenFactory {
    public static Optional<Token> fromMap(Map<String, String> map) {

        if (map.containsKey("kind") && map.containsKey("username"))
        {
            Kind kind = Kind.valueOf(map.get("kind").toUpperCase());
            return Optional.of(new ValidToken(map.get("username"), kind));
        }

        return Optional.empty();
    }
}
