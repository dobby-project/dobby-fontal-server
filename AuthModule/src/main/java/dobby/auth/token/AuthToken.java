package dobby.auth.token;

import dobby.core.user.Kind;
import dobby.core.user.Token;

/**
 * Created by gautierc on 19/12/15.
 */
public class AuthToken implements Token {

    private String name;
    private Kind kind;

    public AuthToken(String username, Kind kind) {
        this.name = username;
        this.kind = kind;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Kind getKind() {
        return kind;
    }

    @Override
    public boolean equals(Token token) {
        // TODO: Name HAS TO BE unique, we don't want to manage several time the same member (for now...)
        return getName().equals(token.getName());
    }
}
