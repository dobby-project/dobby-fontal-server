package dobby.websocket;


import dobby.core.user.Token;
import dobby.core.Repository;
import dobby.core.comold.communication.InternalMessage;
import dobby.core.comold.communication.Message;
import dobby.core.comold.communication.RawMessage;
import dobby.core.comold.communication.TransferableMessage;
import dobby.core.stakeholder.Stakeholder;
import dobby.core.stakeholder.User;
import dobby.core.user.UserRepository;

import javax.websocket.Session;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by gautierc on 16/01/16.
 */
public class WebSocketUser implements User<Session> {

    // TODO: User×Session identifier
    private UserRepository userRepo;
    private Token token;
    private Session session;
    private Date pendingTime;
    private boolean hasQuit;

    public WebSocketUser(UserRepository userRepo, Token token, Session session) {
        this.userRepo = userRepo;
        this.token = token;
        this.session = session;
        this.hasQuit = false;
    }

    /**
     * Proper disconnection of the user (at reception of the intention of closing by the client or timeLimit reached)
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
    public Message handleMessage(RawMessage msg) {
        return new TransferableMessage(this, msg);
    }

    @Override
    public InternalMessage handleInternalMessage(RawMessage msg) {
        return new InternalMessage(this, msg);
    }

    @Override
    public boolean hasQuit() {
        return hasQuit;
    }

    @Override
    public Repository getOpposedRepository() {
        return userRepo.getAppRepository();
    }

    @Override
    public Optional<Stakeholder> resolveDest(String name) {
        UUID uuid = UUID.fromString(name);
        return getOpposedRepository().getItemByKey(uuid);
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

    @Override
    public Token getToken() {
        return token;
    }
}
