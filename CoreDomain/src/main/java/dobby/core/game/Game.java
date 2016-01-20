package dobby.core.game;

import java.util.UUID;

/**
 * Created by gautierc on 10/12/15.
 */
public interface Game {
    /**
     * Give the UUID of that game (couple Game+Conf)
     * @return
     */
    public UUID getUUID();

    /**
     * Tells the name of the game
     * @return
     */
    public String getName();

    /**
     * Tells the full name of the game (concatenated with the chosen configuration)
     * @return
     */
    public String getFullName();

    /**
     * Return the current configuration of that game
     * @return
     */
    public Configuration getConfiguration();

    /**
     * Tells if two games are equivalents
     * @param g
     * @return
     */
    boolean equals(Game g);
}
