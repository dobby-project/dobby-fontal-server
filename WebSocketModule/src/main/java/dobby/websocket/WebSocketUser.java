package dobby.websocket;


import dobby.auth.token.Token;
import dobby.core.Repository;
import dobby.core.communication.Message;
import dobby.core.communication.RawMessage;
import dobby.core.communication.TransferableMessage;
import dobby.core.user.User;

import javax.websocket.Session;
import java.util.Date;

/**
 * Created by gautierc on 16/01/16.
 */
public class WebSocketUser implements User<Session> {

    //private static final Repository appRepo = StandardAppRepository.getInstance();

    private Token token;
    private Session session;
    private Date pendingTime;
    private boolean hasQuit;

    public WebSocketUser(Token token, Session session) {
        this.token = token;
        this.session = session;
        this.hasQuit = false;
    }

    /**
     * Proper deconnection of the user (at reception of the intention of closing by the client or timeLimit reached)
     */
    @Override
    public void logout() {
        hasQuit = true;
        //TODO: prevent app
    }

    @Override
    public String getName() {
        return token.getName();
    }

    @Override
    public void send(Message msg) {
        session.getAsyncRemote().sendText("NO");
    }

    @Override
    public Message receive(RawMessage msg) {
        return new TransferableMessage(this, msg);
    }

    @Override
    public boolean hasQuit() {
        return hasQuit;
    }

    @Override
    public Repository getOpposedRepository() {
        return null;
    }

    @Override
    public Session getSession() {
        return this.session;
    }

    @Override
    public void restore(User user) {
        this.pendingTime = null;
        this.session = (Session) user.getSession();
    }

    @Override
    public void pending() {
        this.pendingTime = new Date();
    }

    @Override
    public long getPendingTime() {
        return this.pendingTime.getTime();
    }

    public boolean equals(WebSocketUser u) {
        return (token.equals(u.token));
    }
}
