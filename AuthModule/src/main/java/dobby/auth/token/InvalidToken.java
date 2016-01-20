package dobby.auth.token;

import dobby.auth.Kind;

/**
 * Created by gautierc on 19/12/15.
 */
public class InvalidToken implements Token {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Kind getKind() {
        return null;
    }

    @Override
    public boolean equals(Token token) {
        return false;
    }
}
