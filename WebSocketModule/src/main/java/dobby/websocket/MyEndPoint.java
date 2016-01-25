package dobby.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


/**
 * Created by gautierc on 10/12/15.
 */

@ServerEndpoint("/ws")
public class MyEndPoint {

    private static final int MAX_CONNEXION = 1000;
    private static final List<Session> list = Collections.synchronizedList(new ArrayList<>());
    @OnOpen
    public void open(Session session) {
        if (list.size() > MAX_CONNEXION) {
            Logger.getLogger(MyEndPoint.class.getName()).warning("Number of active session reached.");
            try {
                session.getBasicRemote().sendText("{\"error\": \"Too many connection on server.\"}");
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            session.addMessageHandler(new MyMessageHandler(session));
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add(session);
    }

    @OnClose
    public void close(Session session) {
        list.remove(session);

        // Getting the first handler that is mine.
        Optional<MyMessageHandler> handler = (Optional) session.getMessageHandlers()
                .stream()
                .filter(h -> h.getClass().getName() == MyMessageHandler.class.getName())
                .findFirst();

        // If there is a handler, I notify about the connection is closing
        if (handler.isPresent())
            handler.get().closing();
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(MyEndPoint.class.getName()).log(Level.SEVERE, null, error);
    }
}
