package dobby.core.stakeholder;

/**
 * Created by gautierc on 20/01/16.
 */
public enum Activity {
    /**
     * None describe communication without level (internal, auth...)
     */
    NONE,
    /**
     * Represents a communication on the first level of interaction: choose of the app
     */
    LOGGED,
    /**
     * Represents a communication when the choice of the room is negociated
     */
    ROOM,
    /**
     * Represents communications during the game.
     */
    GAME;
}
