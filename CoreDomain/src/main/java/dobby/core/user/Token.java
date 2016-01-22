package dobby.core.user;

/**
 * Created by gautierc on 19/12/15.
 */
public interface Token {
    String getName();
    Kind getKind();
    boolean equals(Token token);
}
