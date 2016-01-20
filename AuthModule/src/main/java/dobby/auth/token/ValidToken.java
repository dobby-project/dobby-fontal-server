package dobby.auth.token;

import dobby.auth.Kind;

/**
 * Created by gautierc on 19/12/15.
 */
public class ValidToken implements Token {

    private String name;
    private Kind kind;

    public ValidToken(String username, Kind kind) {
        this.name = username;
        this.kind = kind;
    }

    @Override
    public boolean isValid() {
        return true;
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
        System.out.println("Equals on ValidToken not implemented!");
        return false;
    }


}
