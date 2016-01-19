package dobby.websocket;

import dobby.auth.TokenSolver;
import dobby.auth.token.Token;
import dobby.core.communication.Message;
import dobby.core.communication.ParsedMessage;
import dobby.core.communication.RawMessage;
import dobby.core.user.User;
import dobby.core.user.UserRepository;

import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by gautierc on 16/01/16.
 */
public class MyMessageHandler implements MessageHandler.Whole<String> {

    private User user;
    private UserRepository userRepo;
    private Session session;

    public MyMessageHandler(UserRepository userRepo, Session session) {
        this.userRepo = userRepo;
        this.session = session;
    }

    public MyMessageHandler(UserRepository userRepo, User<Session> user) {
        this(userRepo, user.getSession());
        this.user = user;
    }

    @Override
    public void onMessage(String message) {
        RawMessage raw = ParsedMessage.Factory.parseFromUser(message);

        if (isIdentified())
            user.send(raw.resolve(user));
        else
            identify(raw);
    }

    private boolean isIdentified() {
        return user != null;
    }

    private void identify(RawMessage raw) {
        // User not recognized, we're looking for a token
        Optional<String> tokenStr = raw.getField("token");
        if (!tokenStr.isPresent())
        {
            // Token found, we check it
            Optional<Token> token = TokenSolver.retrieveData(tokenStr.get());
            if (token.isPresent())
            {
                user = new WebSocketUser(token.get(), session);
                userRepo.accept(user);
            }
            else
            {
                try {
                    session.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
