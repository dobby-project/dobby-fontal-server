package dobby.auth;

import dobby.core.user.Token;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by gautierc on 19/12/15.
 */
public class TokenSolverTest {

    @Test
    public void testInvalidTokenRetrieveData() throws Exception {
        Optional<Token> token = TokenSolver.retrieveData("invalidToken");
        assertFalse(token.isPresent());
    }

    @Test
    public void testValidTokenRetrieveData() throws Exception {
        String tokenString = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEb2JieV9KV1QiLCJqaXQiOiJiNmU5YzRjMS0xYzBjLTRmNWMtODkwZi0wNDk5MTFkNjBmNDYiLCJpYXQiOjE0NTA1MzY3NzUyNTQsImV4cCI6MTQ1MzIxNTE3NTI1NSwiZGF0YSI6eyJraW5kIjoiYWRtaW4iLCJ1c2VybmFtZSI6ImdhdXRpZXIifX0.z8m-P-DRnlHgpRWgrwiz4lD_8Qlsh6vC1gTl5DDykW8";
        Optional<Token> token = TokenSolver.retrieveData(tokenString);
        assertTrue(token.isPresent());
    }
}