package dobby.websocket;

import dobby.auth.TokenSolver;
import dobby.auth.token.Token;
import dobby.core.communication.Message;
import dobby.core.communication.Router;
import dobby.core.communication.StandardRouter;
import dobby.core.stakeholder.User;
import dobby.core.user.UserRepository;

import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by gautierc on 16/01/16.
 */
public class MyMessageHandler implements MessageHandler.Whole<String> {

    private final static Logger LOGGER = Logger.getLogger(StandardRouter.class.getName());

    public static Router router;
    public static UserRepository userRepo;

    private User user;
    private Session session;

    public MyMessageHandler(Session session) throws Exception {
        if (userRepo == null)
            throw new Exception("MyMessageHandler.userRepo has to be set");

        this.session = session;
    }

    public void closing() {
        if (isIdentified()) 
            userRepo.close(user);
    }

    @Override
    public void onMessage(String message) {
        if (isIdentified())
        {
            try {
                router.accept(Message.Builder.parse(user, message));
            } catch (Exception e) {
                router.logError(e);
                e.printStackTrace();
            }
        }
        else
        {
            Optional<String> token = Message.Builder.parseToken(message);
            if (!token.isPresent())
            {
                try {
                    session.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            identify(token.get());
        }
    }

    private boolean isIdentified() {
        return user != null;
    }

    private void identify(String tokenStr) {
        // Token found, we check it
        Optional<Token> token = TokenSolver.retrieveData(tokenStr);
        if (token.isPresent())
        {
            user = new WebSocketUser(userRepo, token.get(), session);
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
