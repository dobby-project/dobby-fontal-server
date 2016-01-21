package dobby.core.app;

import dobby.core.Repository;
import dobby.core.comold.communication.InternalMessage;
import dobby.core.comold.communication.Message;
import dobby.core.comold.communication.RawMessage;
import dobby.core.stakeholder.App;
import dobby.core.stakeholder.Stakeholder;

import java.util.Optional;
import java.util.UUID;



/**
 * Created by gautierc on 10/12/15.
 */
public class BasicApp implements App {

    private UUID uuid;
    private String name;

    public BasicApp(String uuid, String gameName) {
        this.uuid = UUID.fromString(uuid);
        this.name = gameName;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void send(Message msg) {

    }

    @Override
    public Message handleMessage(RawMessage msg) {
        return null;
    }

    @Override
    public InternalMessage handleInternalMessage(RawMessage msg) {
        return null;
    }

    @Override
    public boolean hasQuit() {
        return false;
    }

    @Override
    public Repository getOpposedRepository() {
        return null;
    }

    @Override
    public Optional<Stakeholder> resolveDest(String name) {
        return null;
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public Configuration getConfiguration() {
        return BasicConfiguration.NONE;
    }

    @Override
    public boolean equals(App g) {
        return g.getFullName().equals(this.getFullName());
    }
}
