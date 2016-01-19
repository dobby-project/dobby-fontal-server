package dobby.websocket;

import dobby.core.user.StandardUserRepository;
import dobby.core.user.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


/**
 * Created by gautierc on 10/12/15.
 */

@ServerEndpoint("/ws")
public class MyEndPoint {

    private static final UserRepository userRepo = StandardUserRepository.getInstance();
    private static final List<Session> list = Collections.synchronizedList(new ArrayList<Session>());
    @OnOpen
    public void open(Session session) {
        session.addMessageHandler(new MyMessageHandler(userRepo, session));
        list.add(session);
    }

    @OnClose
    public void close(Session session) {
        list.remove(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(MyEndPoint.class.getName()).log(Level.SEVERE, null, error);
    }

    /*
    @OnMessage
    public void handleMessage(Session session, String message) { // Handle the first message

        // Reading the message
        RawMessage raw = new ParsedMessage(message);

        // Checking if the session has an associated user
        Optional<User> user = userRepo.getUser(session);
        if (user.isPresent())
        {
            // Solving src+dest
            Message msg = raw.resolve(user.get());

            // Forwarding
            user.get().send(msg);
        }
        else
        {
            // User not recognized, we're looking for a token
            Optional<String> tokenStr = raw.getField("token");
            if (!tokenStr.isPresent())
            {
                // Token found, we check it
                Optional<Token> token = TokenSolver.retrieveData(tokenStr.get());
                if (token.isPresent())
                {
                    // Connection accepted, we notify the user and keep the association
                    session.addMessageHandler();
                    send(session, "ok");
                    userRepo.accept(new WebSocketUser(token.get(), session));
                }
                else
                    close(session, "invalid token");
            }
        }
    }*/
}
