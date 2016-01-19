package dobby.core.game;

import java.util.UUID;

/**
 * Created by gautierc on 10/12/15.
 */
public class BasicGame implements Game {

    private UUID uuid;
    private String name;

    public BasicGame(String uuid, String gameName) {
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
    public String getFullName() {
        return name;
    }

    @Override
    public Configuration getConfiguration() {
        return BasicConfiguration.NONE;
    }

    @Override
    public boolean equals(Game g) {
        return g.getFullName().equals(this.getFullName());
    }
}
