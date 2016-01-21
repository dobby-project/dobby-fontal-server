package dobby.core.stakeholder;

import dobby.core.app.Configuration;

import java.util.UUID;

/**
 * Created by gautierc on 10/12/15.
 */
public interface App extends Stakeholder {
    /**
     * Give the UUID of that App (couple App+Conf)
     * @return
     */
    UUID getUUID();

    /**
     * Tells the full name of the App (concatenated with the chosen configuration)
     * @return
     */
    String getFullName();

    /**
     * Return the current configuration of that App
     * @return
     */
    Configuration getConfiguration();

    /**
     * Tells if two App are equivalents
     * @param g
     * @return
     */
    boolean equals(App g);
}
