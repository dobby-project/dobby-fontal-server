package dobby.auth.token;

import dobby.auth.Kind;

/**
 * Created by gautierc on 19/12/15.
 */
public interface Token {
    boolean isValid();
    String getName();
    Kind getKind();
    boolean equals(Token token);
}
